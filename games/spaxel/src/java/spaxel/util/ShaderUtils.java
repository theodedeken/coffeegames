package spaxel.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

/**
 * Provides utility functions for the creation of Shaders and Shaderprograms
 */
public final class ShaderUtils {
	// logger for logging errors happening during creation of shaders
	private static final Logger LOGGER = Logger.getLogger(ShaderUtils.class.getName());

	private static FileToStringLoader loader = new FileToStringLoader();

	private ShaderUtils() {
	}

	/**
	 * Load vertex and fragment shader from path and create a new program
	 * 
	 * @param vertPath path to the vertex shader
	 * @param fragPath path to the fragment shader
	 * 
	 * @return the identifier of the shader program
	 */
	public static int load(String vertPath, String fragPath) {
		String vert = loader.loadAsString(vertPath);
		String frag = loader.loadAsString(fragPath);
		return createProgram(vert, frag);
	}

	/**
	 * Create a new Shader program
	 * 
	 * @param vert The vertex shader as string
	 * @param frag The fragment shader as string
	 * 
	 * @return the identifier of the new shader program
	 */
	public static int createProgram(String vert, String frag) {
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

}
