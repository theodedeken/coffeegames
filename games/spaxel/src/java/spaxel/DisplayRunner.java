package spaxel;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.glfw.GLFWErrorCallback;
import spaxel.engine.Engine;
import spaxel.resources.Resources;
import spaxel.system.RenderSystem;
import spaxel.ui.UIType;
import spaxel.util.GLUtil;
import voide.input.MouseWrapper;
import voide.ui.UI;

/**
 * Runnable for the thread that renders all the display frames
 */
public class DisplayRunner implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(
        DisplayRunner.class.getName()
    );
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
            Engine.get().exit();
        }

        // Initialize GL context
        GLUtil.initGLRendering();
        // Log the GL version
        LOGGER.log(Level.INFO, "OpenGL: {0}", glGetString(GL_VERSION));

        // Setup mouse callback
        MouseWrapper mouseWrapper = new MouseWrapper(
            window,
            Constants.GAME_HEIGHT
        );
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
        Engine
            .get()
            .setCurrentUI(
                voide.resources.Resources
                    .get()
                    .getResource(UIType.LOAD.key(), UI.class)
            );
        // create a new rendersystem
        renderSystem = new RenderSystem();
        // create a new thread to load the rest of the resources
        Thread load = new Thread(
            () -> {
                Resources.get().startLoading();
                Engine.get().finishLoading();
                Game.startUpdating();
            }
        );
        load.start();

        long start;
        long deltatime;
        while (running) {
            if (glfwWindowShouldClose(window) || Engine.get().shouldClose()) {
                exit();
            }
            start = System.nanoTime();
            render();
            deltatime = System.nanoTime() - start;
            Engine
                .get()
                .getGameState()
                .setUpdateTime((double) deltatime / Constants.NS_PER_TICK);
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
