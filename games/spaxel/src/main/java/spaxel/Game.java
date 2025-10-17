package spaxel;

/**
 * Main class of the game, manages the different threads
 */
public final class Game {

    // the display runnable
    public static final DisplayRunner displayRunner = new DisplayRunner();
    // the update runnable
    public static final UpdateRunner updateRunner = new UpdateRunner();
    private static boolean shouldClose = false;

    private Game() {}

    /**
     * Entry point of the program, starts up the display thread.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        Thread display = new Thread(displayRunner, "Display");

        display.start();
    }

    /**
     * Starts up the update thread
     */
    public static void startUpdating() {
        Thread update = new Thread(updateRunner, "Update");

        update.start();
    }

    /**
     * Exits the game.
     */
    public static void exit() {
        shouldClose = true;
    }

    public static boolean shouldClose() {
        return shouldClose;
    }
}
