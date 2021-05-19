package spaxel.ui.controllers;

import spaxel.engine.Engine;
import spaxel.ui.UIType;
import voide.ui.UI;

/**
 * Callbacks for elements of the game over UI
 *
 * Created by theo on 26-6-2016.
 */
public final class GameOverController {

    private GameOverController() {}

    /**
     * Play again
     */
    public static void play() {
        Engine
            .get()
            .setCurrentUI(
                voide.resources.Resources
                    .get()
                    .getResource(UIType.CLASS_SELECTION.key(), UI.class)
            );
    }

    /**
     * Go to the main menu
     */
    public static void main() {
        Engine
            .get()
            .setCurrentUI(
                voide.resources.Resources
                    .get()
                    .getResource(UIType.MAIN.key(), UI.class)
            );
    }
}
