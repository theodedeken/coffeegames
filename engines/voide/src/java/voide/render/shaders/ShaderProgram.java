package voide.render.shaders;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import java.util.logging.Level;
import java.util.logging.Logger;
import voide.io.FileReader;

/**
 * Represents a shader program on the GPU
 *
 * Created by theod on 16-7-2017.
 */
public class ShaderProgram {

    protected int program;
    private final Logger LOGGER = Logger.getLogger(
        ShaderProgram.class.getName()
    );

    /**
     * Create a new Shaderprogram using the code of the given shaders
     *
     * @param vertexShader   the path to the vertexShader
     * @param fragmentShader the path to the fragmentShader
     */
    public ShaderProgram(String vertexShader, String fragmentShader) {
        String vertexShaderContents = new FileReader(vertexShader).toString();
        String fragmentShaderContents = new FileReader(fragmentShader)
            .toString();
        program = createProgram(vertexShaderContents, fragmentShaderContents);
    }

    /**
     * Create a new Shader program
     *
     * @param vert The vertex shader as string
     * @param frag The fragment shader as string
     *
     * @return the identifier of the new shader program
     */
    private int createProgram(String vert, String frag) {
        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);
        // Set the shader code
        glShaderSource(vertID, vert);
        glShaderSource(fragID, frag);

        // Compile the shaders, log when it fails
        glCompileShader(vertID);
        if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
            LOGGER.log(Level.SEVERE, "Failed to compile vertex shader!");
            LOGGER.log(Level.SEVERE, glGetShaderInfoLog(vertID));
            return -1;
        }
        glCompileShader(fragID);
        if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
            LOGGER.log(Level.SEVERE, "Failed to compile fragment shader!");
            LOGGER.log(Level.SEVERE, glGetShaderInfoLog(vertID));
            return -1;
        }

        // create a new program
        int program = glCreateProgram();
        // attach the shaders
        glAttachShader(program, vertID);
        glAttachShader(program, fragID);
        // link and validate
        glLinkProgram(program);
        glValidateProgram(program);
        // cleanup the shaders
        glDeleteShader(vertID);
        glDeleteShader(fragID);

        return program;
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
