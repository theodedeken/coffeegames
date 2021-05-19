package voide.render.postprocess;

import static org.lwjgl.opengl.GL11.*;

import voide.math.VectorD;
import voide.render.buffer.FBO;
import voide.render.shaders.BlurShaderProgram;
import voide.render.shaders.ShaderProgram;

/**
 * Created by theod on 25-7-2017.
 */
public class BlurPostProcessor extends PostProcessor {

    private static final double BLUR_STRENGTH = 2.0;
    private FBO middle;

    /**
     * Creates a new PostProcessor that executes a blurring postprocess step
     *
     * @param program the blurring program
     */
    public BlurPostProcessor(
        ShaderProgram program,
        int screenWidth,
        int screenHeight
    ) {
        super(program, screenWidth, screenHeight);
        middle = new FBO(screenWidth, screenHeight);
    }

    @Override
    public void postProcess(FBO in, FBO out) {
        fboQuad.bind();
        program.enable();

        middle.bindBuffer();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        in.bindTexture();
        ((BlurShaderProgram) program).setDir(
                new VectorD(0, BLUR_STRENGTH / screenHeight)
            );
        glDrawElements(
            GL_TRIANGLES,
            fboQuad.getVertexCount(),
            GL_UNSIGNED_BYTE,
            0
        );
        middle.unbindBuffer();

        out.bindBuffer();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        middle.bindTexture();
        ((BlurShaderProgram) program).setDir(
                new VectorD(BLUR_STRENGTH / screenWidth, 0)
            );
        glDrawElements(
            GL_TRIANGLES,
            fboQuad.getVertexCount(),
            GL_UNSIGNED_BYTE,
            0
        );
        out.unbindBuffer();
    }
}
