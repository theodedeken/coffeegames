package spaxel.ui;

/**
 * The type of the ui.
 */
public enum UIType {
    CLASS_SELECTION,
    CREDITS,
    GAME_OVER,
    MAIN,
    PAUSE,
    PLAY,
    OPTIONS,
    GAME_SETTINGS,
    CONTROLS_SETTINGS,
    GRAPHICS_SETTINGS,
    SOUND_SETTINGS,
    LOAD;

    public String key() {
        return "ui." + name();
    }
}
