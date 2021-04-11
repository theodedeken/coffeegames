package spaxel.util;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import spaxel.Constants;

/**
 * Provides utility methods for GL operations
 */
public final class GLUtil {
    // to divide by two
    private static final int TWO = 2;

    private GLUtil() {

    }

    /**
     * Exception when creating a GL window
     */
    public static class WindowCreateException extends Exception {
        private static final long serialVersionUID = 1;

        /**
         * Create a new WindowCreateException with the specified message
         * 
         * @param message The exception message
         */
        public WindowCreateException(String message) {
            super(message);
        }

    }

    /**
     * Initialize a new OpenGL window
     * 
     * @return the identifier for the created window
     * @throws WindowCreateException when the window cannot be created
     */
    public static long initGLWindow() throws WindowCreateException {
        // setup opengl version
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, Constants.GL_MAJOR_V);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, Constants.GL_MINOR_V);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        // restrict the user from resizing the window
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

        long window = glfwCreateWindow(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,
                Constants.DISPLAY_NAME, NULL, NULL);
        if (window == NULL) {
            // throw an exception if the creation of the window fails
            throw new WindowCreateException("Could not create window");
        }
        // show the window on the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // position the window in the middle of the desktop
        glfwSetWindowPos(window, (vidmode.width() - Constants.GAME_WIDTH) / TWO,
                (vidmode.height() - Constants.GAME_HEIGHT) / TWO);

        // initialize the gl context for this window
        glfwMakeContextCurrent(window);
        // make the window visible
        glfwShowWindow(window);
        return window;
    }

    /**
     * Initialize the rendering properties of this OpenGL context
     */
    public static void initGLRendering() {
        GL.createCapabilities();
        // set the clear color to black
        glClearColor(0.0F, 0.0F, 0.0F, 1.0F);

        glActiveTexture(GL_TEXTURE1);

        // Setup blending such that alpha will be rendered correctly
        glEnable(GL_BLEND);
        glEnable(GL_TEXTURE_2D);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
}
