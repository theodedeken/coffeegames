package voide.render;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL31.glDrawElementsInstanced;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.Map;
import voide.graphics.geometry.InstancedQuad;
import voide.graphics.geometry.Quad;
import voide.math.MatrixD;
import voide.math.MatrixMaker;
import voide.math.VectorD;
import voide.render.buffer.FBO;
import voide.render.buffer.LayerFBO;
import voide.render.buffer.MasterBuffer;
import voide.render.buffer.RenderBuffer;
import voide.render.buffer.RenderJob;
import voide.render.buffer.RenderLayer;
import voide.render.postprocess.BlurPostProcessor;
import voide.render.postprocess.PostProcessor;
import voide.render.shaders.BlurShaderProgram;
import voide.render.shaders.InstancedShaderProgram;
import voide.render.shaders.LastPassShaderProgram;

/**
 * holds all the spritesheets and data of shaders, vertices, can switch between
 * spritesheets, load the buffers and render instanced.
 *
 *
 */
public class MasterRenderer {

    private static final double TWO = 2.0;
    private static final int BLUR_RESOLUTION = 2;
    private static final int BLUR_SIZE = 9;
    private InstancedQuad allQuad;
    private InstancedShaderProgram instancedShader;
    private PostProcessor blurPostProcessor;

    private LayerFBO layerFBO;

    private Quad fboQuad;
    private FBO blurred;
    private LastPassShaderProgram lastPassProgram;

    private int screenWidth;
    private int screenHeight;

    public MasterRenderer(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        initialize();
    }

    private void initialize() {
        MatrixD projectionMatrix = MatrixMaker.orthographic(
            -screenWidth / TWO,
            screenWidth / TWO,
            -screenHeight / TWO,
            screenHeight / TWO,
            -1.0,
            1.0
        );

        BlurShaderProgram blurPassProgram = new BlurShaderProgram(
            "/shaders/blur_pass.vert",
            "/shaders/blur_pass.frag"
        );
        blurPassProgram.enable();
        blurPassProgram.setTexSampler(1);
        blurPassProgram.setRadius(1);
        blurPassProgram.setResolution(BLUR_RESOLUTION);
        blurPassProgram.setSize(BLUR_SIZE);
        blurPostProcessor =
            new BlurPostProcessor(blurPassProgram, screenWidth, screenHeight);

        lastPassProgram =
            new LastPassShaderProgram(
                "/shaders/last_pass.vert",
                "/shaders/last_pass.frag"
            );
        lastPassProgram.enable();
        lastPassProgram.setTexSampler(1);
        lastPassProgram.setProjectionMatrix(projectionMatrix);
        lastPassProgram.setTranslationMatrix(
            MatrixMaker.getTransformationMatrix(
                new VectorD(0, 0),
                0,
                new VectorD(screenWidth, screenHeight)
            )
        );

        instancedShader =
            new InstancedShaderProgram(
                "/shaders/2Dsprite.vert",
                "/shaders/2Dsprite.frag"
            );
        instancedShader.enable();
        instancedShader.setTexSampler(1);
        instancedShader.setProjectionMatrix(projectionMatrix);

        allQuad = new InstancedQuad();
        fboQuad = new Quad();
        blurred = new FBO(screenWidth, screenHeight);
        layerFBO = new LayerFBO(screenWidth, screenHeight);
    }

    public void render(MasterBuffer masterBuffer) {
        allQuad.bind();
        instancedShader.enable();
        // render all layers
        for (RenderLayer layer : RenderLayer.values()) {
            layerFBO.getFbo(layer).bindBuffer();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            Map<Integer, List<RenderJob>> buffers = masterBuffer.getJobs(layer);
            for (Map.Entry<Integer, List<RenderJob>> entry : buffers.entrySet()) {
                render(new RenderBuffer(entry.getValue()), entry.getKey());
            }
            layerFBO.getFbo(layer).unbindBuffer();
        }

        blurPostProcessor.postProcess(
            layerFBO.getFbo(RenderLayer.GAME),
            blurred
        );

        combine();
    }

    public void combine() {
        fboQuad.bind();
        lastPassProgram.enable();
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        blurred.bindTexture();

        glDrawElements(
            GL_TRIANGLES,
            fboQuad.getVertexCount(),
            GL_UNSIGNED_BYTE,
            0
        );

        layerFBO.getFbo(RenderLayer.GAME).bindTexture();

        glDrawElements(
            GL_TRIANGLES,
            fboQuad.getVertexCount(),
            GL_UNSIGNED_BYTE,
            0
        );

        layerFBO.getFbo(RenderLayer.UI).bindTexture();

        glDrawElements(
            GL_TRIANGLES,
            fboQuad.getVertexCount(),
            GL_UNSIGNED_BYTE,
            0
        );

        layerFBO.getFbo(RenderLayer.DEBUG).bindTexture();

        glDrawElements(
            GL_TRIANGLES,
            fboQuad.getVertexCount(),
            GL_UNSIGNED_BYTE,
            0
        );
    }

    public void render(RenderBuffer buffer, int spritesheet) {
        if (buffer.size() > 0) {
            loadBuffer(allQuad.getTransScale(), buffer.getTrscBuffer());
            loadBuffer(allQuad.getSinCosAlphaColor(), buffer.getSinCosBuffer());
            loadBuffer(
                allQuad.getTexOffsetScale(),
                buffer.getTexOffsetBuffer()
            );
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            glBindTexture(GL_TEXTURE_2D, spritesheet);

            glDrawElementsInstanced(
                GL_TRIANGLES,
                allQuad.getVertexCount(),
                GL_UNSIGNED_BYTE,
                0,
                buffer.size()
            );
        }
    }

    private void loadBuffer(int bufferID, FloatBuffer buffer) {
        glBindBuffer(GL_ARRAY_BUFFER, bufferID);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
    }
}
