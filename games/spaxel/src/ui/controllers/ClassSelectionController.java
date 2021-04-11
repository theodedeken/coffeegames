package spaxel.ui.controllers;

import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.engine.Resources;
import spaxel.input.Key;
import spaxel.input.Keyboard;
import spaxel.ui.elements.UIType;
import spaxel.ui.elements.Element;

/**
 * Callbacks for element of the class selection UI
 * 
 * Created by theo on 21-6-2016.
 */
public final class ClassSelectionController {
    private ClassSelectionController() {
    }

    /**
     * Select the white ship.
     */
    public static void selectWhite() {
        Entity player = Resources.get().getIndustryMap().get("player_white_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Select the red ship.
     */
    public static void selectRed() {
        Entity player = Resources.get().getIndustryMap().get("player_red_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Select the green ship.
     */
    public static void selectGreen() {
        Entity player = Resources.get().getIndustryMap().get("player_green_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Select the blue ship.
     */
    public static void selectBlue() {
        Entity player = Resources.get().getIndustryMap().get("player_blue_industry").produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PLAY));
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Go to the previous screen
     */
    public static void back() {
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.MAIN));
    }

    /**
     * Checks whether the esc key is pressed and goes to the previous screen if that
     * is the case.
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

}
