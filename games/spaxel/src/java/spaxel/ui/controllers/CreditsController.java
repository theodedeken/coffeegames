package spaxel.ui.controllers;

import spaxel.engine.Engine;
import spaxel.ui.UIType;
import voide.input.Key;
import voide.input.Keyboard;
import voide.ui.UI;
import voide.ui.elements.Element;

/**
 * Callbacks for elements of the credits UI
 * 
 * Created by theo on 8-6-2016.
 */
public final class CreditsController {

    private CreditsController() {
    }

    /**
     * Checks whether the esc key is pressed and goes to the previous screen if that
     * is the case
     * 
     * @param element the calling element
     */
    public static void escCheck(Element element) {
        // TODO: is this dump doing something?
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
