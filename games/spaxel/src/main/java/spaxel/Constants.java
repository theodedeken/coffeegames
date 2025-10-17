package spaxel;

/** Groups all the Game constants. */
public final class Constants {

    // Math constants
    public static final double FULL_CIRCLE = 2 * Math.PI;
    public static final double HALF_CIRLCE = Math.PI / 2;
    // Timing constants
    public static final long NS_PER_SECOND = 1_000_000_000;
    public static final int TICKS_PER_SECOND = 50;
    public static final long NS_PER_TICK = NS_PER_SECOND / TICKS_PER_SECOND;
    public static final long NS_PER_MS = 1_000_000;
    public static final long NS_PER_US = 1_000;
    // Mouse follow constants
    public static final double MOUSE_FOLLOW_CUTOFF = .1;
    public static final double MOUSE_FOLLOW_MULTIPLIER = .15;
    // Game info constants
    public static final String GAME_NAME = "Spaxel";
    public static final int GAME_MAJOR_V = 0;
    public static final int GAME_MINOR_V = 5;
    public static final int GAME_PATCH_V = 0;
    public static final String DISPLAY_NAME = GAME_NAME + " - " + GAME_MAJOR_V + "." + GAME_MINOR_V + "."
            + GAME_PATCH_V;
    public static final int GAME_HEIGHT = 720;
    public static final int GAME_WIDTH = 1280;
    public static final int HALF_GAME_HEIGHT = GAME_HEIGHT / 2;
    public static final int HALF_GAME_WIDTH = GAME_WIDTH / 2;
    // GL constants
    public static final int GL_MAJOR_V = 4;
    public static final int GL_MINOR_V = 5;
    // Resources constants
    public static final String RESOURCE_PATH = "/resource_paths.yml";
    public static final String RESOURCE_MAP = "/resource_map.yml";
    public static final String LOAD_RESOURCE_MAP = "/load_resource_map.yml";
    // input constants
    public static final int SPEED_MULT = 2;
    // experience constants
    public static final int XP_FUNC_A = 25;
    public static final int XP_FUNC_B = 25;
    public static final int XP_FUNC_C = 50;

    private Constants() {
    }
}
