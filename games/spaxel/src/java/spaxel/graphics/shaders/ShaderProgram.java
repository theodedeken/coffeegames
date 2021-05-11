package spaxel.graphics.shaders;

import spaxel.util.ShaderUtils;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Represents a shader program on the GPU
 * 
 * Created by theod on 16-7-2017.
 */
public class ShaderProgram {
    protected int program;

    /**
     * Create a new Shaderprogram using the code of the given shaders
     * 
     * @param vertexShader   the path to the vertexShader
     * @param fragmentShader the path to the fragmentShader
     */
    public ShaderProgram(String vertexShader, String fragmentShader) {
        program = ShaderUtils.load(vertexShader, fragmentShader);
    }

    /**
     * Enable this program on the GPU
     */
    public void enable() {
        glUseProgram(program);
    }

    /**
     * Disable this program on the GPU
     */
    public void disable() {
        glUseProgram(0);
    }
}
