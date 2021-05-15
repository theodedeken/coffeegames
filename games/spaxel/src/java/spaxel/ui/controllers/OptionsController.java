package spaxel.ui.controllers;

import spaxel.engine.Engine;
import spaxel.ui.UIType;
import voide.input.Key;
import voide.input.Keyboard;
import voide.ui.UI;
import voide.ui.elements.Element;

/**
 * Callbacks for elements in the options UI
 * 
 * Created by theod on 19-9-2017.
 */
public final class OptionsController {
    private OptionsController() {

    }

    /**
     * Opens the game settings
     */
    public static void gameSettings() {
        // TODO
    }

    /**
     * Opens the controls settings
     */
    public static void controlsSettings() {
        // TODO
    }

    /**
     * Opens the graphics settings
     */
    public static void graphicsSettings() {
        // TODO
    }

    /**
     * Opens the sound settings
     */
    public static void soundSettings() {
        // TODO
    }

    /**
     * Checks whether the esc key is pressed and goes to the previous screen if that
     * is the case
     * 
     * @param element the calling element
     */
    public static void escCheck(Element element) {
        element.dump("nothing", "nothing");
        Keyboard k = Engine.get().getKeyboard();
        if (k.get(Key.ESC).isRelease()) {
            back();
        }
    }

    /**
     * Go to the previous screen
     */
    public static void back() {
        Engine.get().setCurrentUI(voide.resources.Resources.get().getResource(UIType.MAIN.key(), UI.class));
    }
}
