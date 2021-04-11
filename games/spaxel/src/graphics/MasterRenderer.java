package spaxel.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL31.glDrawElementsInstanced;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.Map;
import spaxel.graphics.postprocess.BlurPostProcessor;
import spaxel.graphics.postprocess.PostProcessor;
import spaxel.graphics.postprocess.FBO;
import spaxel.graphics.shaders.BlurShaderProgram;
import spaxel.graphics.shaders.InstancedShaderProgram;
import spaxel.graphics.shaders.LastPassShaderProgram;
import spaxel.graphics.buffer.LayerFBO;
import spaxel.graphics.buffer.RenderLayer;
import spaxel.graphics.buffer.MasterBuffer;
import spaxel.graphics.buffer.RenderJob;
import spaxel.graphics.buffer.RenderBuffer;
import spaxel.graphics.geometry.Quad;
import spaxel.graphics.geometry.InstancedQuad;
import spaxel.math.MatrixD;
import spaxel.util.MatrixUtil;
import spaxel.math.VectorD;
import spaxel.Constants;

/**
 * holds all the spritesheets and data of shaders, vertices, can switch between spritesheets, load
 * the buffers and render instanced.
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

    public MasterRenderer() {
        initialize();
    }

    private void initialize() {
        MatrixD projectionMatrix =
                MatrixUtil.orthographic(-Constants.GAME_WIDTH / TWO, Constants.GAME_WIDTH / TWO,
                        -Constants.GAME_HEIGHT / TWO, Constants.GAME_HEIGHT / TWO, -1.0, 1.0);

        BlurShaderProgram blurPassProgram =
                new BlurShaderProgram("/shaders/blur_pass.vert", "/shaders/blur_pass.frag");
        blurPassProgram.enable();
        blurPassProgram.setTexSampler(1);
        blurPassProgram.setRadius(1);
        blurPassProgram.setResolution(BLUR_RESOLUTION);
        blurPassProgram.setSize(BLUR_SIZE);
        blurPostProcessor = new BlurPostProcessor(blurPassProgram);

        lastPassProgram =
                new LastPassShaderProgram("/shaders/last_pass.vert", "/shaders/last_pass.frag");
        lastPassProgram.enable();
        lastPassProgram.setTexSampler(1);
        lastPassProgram.setProjectionMatrix(projectionMatrix);
        lastPassProgram.setTranslationMatrix(MatrixUtil.getTransformationMatrix(new VectorD(0, 0),
                0, new VectorD(Constants.GAME_WIDTH, Constants.GAME_HEIGHT)));

        instancedShader =
                new InstancedShaderProgram("/shaders/2Dsprite.vert", "/shaders/2Dsprite.frag");
        instancedShader.enable();
        instancedShader.setTexSampler(1);
        instancedShader.setProjectionMatrix(projectionMatrix);

        allQuad = new InstancedQuad();
        fboQuad = new Quad();
        blurred = new FBO();
        layerFBO = new LayerFBO();
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

        blurPostProcessor.postProcess(layerFBO.getFbo(RenderLayer.GAME), blurred);

        combine();
    }

    public void combine() {
        fboQuad.bind();
        lastPassProgram.enable();
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        blurred.bindTexture();

        glDrawElements(GL_TRIANGLES, fboQuad.getVertexCount(), GL_UNSIGNED_BYTE, 0);

        layerFBO.getFbo(RenderLayer.GAME).bindTexture();

        glDrawElements(GL_TRIANGLES, fboQuad.getVertexCount(), GL_UNSIGNED_BYTE, 0);

        layerFBO.getFbo(RenderLayer.UI).bindTexture();

        glDrawElements(GL_TRIANGLES, fboQuad.getVertexCount(), GL_UNSIGNED_BYTE, 0);

        layerFBO.getFbo(RenderLayer.DEBUG).bindTexture();

        glDrawElements(GL_TRIANGLES, fboQuad.getVertexCount(), GL_UNSIGNED_BYTE, 0);
    }

    public void render(RenderBuffer buffer, int spritesheet) {
        if (buffer.size() > 0) {
            loadBuffer(allQuad.getTransScale(), buffer.getTrscBuffer());
            loadBuffer(allQuad.getSinCosAlphaColor(), buffer.getSinCosBuffer());
            loadBuffer(allQuad.getTexOffsetScale(), buffer.getTexOffsetBuffer());
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            glBindTexture(GL_TEXTURE_2D, spritesheet);

            glDrawElementsInstanced(GL_TRIANGLES, allQuad.getVertexCount(), GL_UNSIGNED_BYTE, 0,
                    buffer.size());
        }
    }

    private void loadBuffer(int bufferID, FloatBuffer buffer) {
        glBindBuffer(GL_ARRAY_BUFFER, bufferID);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
    }

}
