package spaxel;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import spaxel.engine.Engine;
import voide.input.MouseWrapper;
import spaxel.system.RenderSystem;
import org.lwjgl.glfw.GLFWErrorCallback;
import java.util.logging.Level;
import java.util.logging.Logger;
import spaxel.util.GLUtil;
import spaxel.engine.Resources;

/**
 * Runnable for the thread that renders all the display frames
 */
public class DisplayRunner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(DisplayRunner.class.getName());
    // Status of the runnable
    private volatile boolean running = true;

    private long window = NULL;
    private RenderSystem renderSystem;

    /**
     * Create a new DisplayRunner
     */
    public DisplayRunner() {
        super();
    }

    /**
     * Initialize the OpenGL window and context. Setup the input callback.
     */
    public void initialize() {
        GLFWErrorCallback.createPrint().set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Initialize window
        try {
            window = GLUtil.initGLWindow();
        } catch (GLUtil.WindowCreateException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Game.exit();
        }

        // Initialize GL context
        GLUtil.initGLRendering();
        // Log the GL version
        LOGGER.log(Level.INFO, "OpenGL: {0}", glGetString(GL_VERSION));

        // Setup mouse callback
        MouseWrapper mouseWrapper = new MouseWrapper(window, Constants.GAME_HEIGHT);
        glfwSetCursorPosCallback(window, mouseWrapper);
        // Set mouse and window in engine
        Engine.get().setMouseWrapper(mouseWrapper);
        Engine.get().setWindow(window);
    }

    public void run() {
        // initialize GL and stuff
        initialize();
        // load the resources needed to show the loading screen
        Resources.get().initLoadingResources();
        // create a new rendersystem
        renderSystem = new RenderSystem();
        // create a new thread to load the rest of the resources
        Thread load = new Thread(() -> Resources.get().startLoading());
        load.start();

        long start;
        long deltatime;
        while (running) {
            if (glfwWindowShouldClose(window) || Game.shouldClose()) {
                exit();
            }
            start = System.nanoTime();
            render();
            deltatime = System.nanoTime() - start;
            Engine.get().getGameState().setUpdateTime((double) deltatime / Constants.NS_PER_TICK);

        }
    }

    /**
     * Render a new frame and swap the buffer to show it in the window.
     */
    public void render() {
        // clear the framebuffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        // render everything
        renderSystem.update();
        // swap the window buffers
        glfwSwapBuffers(window);
        // mouse callback
        glfwPollEvents();
    }

    /**
     * Exit this thread and destroy the window
     */
    public void exit() {
        running = false;
        try {
            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);
        } finally {
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }
}
