package spaxel.graphics.postprocess;

import spaxel.graphics.geometry.Quad;
import spaxel.graphics.shaders.ShaderProgram;

/**
 * Represents a postprocessing step
 * 
 * Created by theod on 25-7-2017.
 */
public abstract class PostProcessor {
    protected ShaderProgram program;
    protected Quad fboQuad;

    /**
     * Create a new PostProcessor executing the specified program
     * 
     * @param program the postprocessor program
     */
    public PostProcessor(ShaderProgram program) {
        this.program = program;
        this.fboQuad = new Quad();
    }

    /**
     * Execute the postprocessing step on the in data writing the result to the out data
     * 
     * @param in  framebuffer object with the inputdata
     * @param out framebuffer object with the outputdata
     */
    public abstract void postProcess(FBO in, FBO out);
}
