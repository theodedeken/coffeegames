package voide.render.postprocess;

import voide.graphics.geometry.Quad;
import voide.render.buffer.FBO;
import voide.render.shaders.ShaderProgram;

/**
 * Represents a postprocessing step
 *
 * Created by theod on 25-7-2017.
 */
public abstract class PostProcessor {

    protected ShaderProgram program;
    protected Quad fboQuad;
    protected int screenWidth;
    protected int screenHeight;

    /**
     * Create a new PostProcessor executing the specified program
     *
     * @param program the postprocessor program
     */
    public PostProcessor(
        ShaderProgram program,
        int screenWidth,
        int screenHeight
    ) {
        this.program = program;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.fboQuad = new Quad();
    }

    /**
     * Execute the postprocessing step on the in data writing the result to the out
     * data
     *
     * @param in  framebuffer object with the inputdata
     * @param out framebuffer object with the outputdata
     */
    public abstract void postProcess(FBO in, FBO out);
}
