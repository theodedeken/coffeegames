package spaxel.ui.controllers;

import spaxel.engine.Engine;
import spaxel.input.SpaxelKey;
import spaxel.ui.UIType;
import voide.entity.Entity;
import voide.entity.EntityIndustry;
import voide.input.Keyboard;
import voide.ui.UI;
import voide.ui.elements.Element;

/**
 * Callbacks for element of the class selection UI
 *
 * Created by theo on 21-6-2016.
 */
public final class ClassSelectionController {

    private ClassSelectionController() {}

    /**
     * Select the white ship.
     */
    public static void selectWhite() {
        Entity player = voide.resources.Resources
            .get()
            .getResource("player_white_industry", EntityIndustry.class)
            .produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine
            .get()
            .setCurrentUI(
                voide.resources.Resources
                    .get()
                    .getResource(UIType.PLAY.key(), UI.class)
            );
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Select the red ship.
     */
    public static void selectRed() {
        Entity player = voide.resources.Resources
            .get()
            .getResource("player_red_industry", EntityIndustry.class)
            .produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine
            .get()
            .setCurrentUI(
                voide.resources.Resources
                    .get()
                    .getResource(UIType.PLAY.key(), UI.class)
            );
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Select the green ship.
     */
    public static void selectGreen() {
        Entity player = voide.resources.Resources
            .get()
            .getResource("player_green_industry", EntityIndustry.class)
            .produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine
            .get()
            .setCurrentUI(
                voide.resources.Resources
                    .get()
                    .getResource(UIType.PLAY.key(), UI.class)
            );
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Select the blue ship.
     */
    public static void selectBlue() {
        Entity player = voide.resources.Resources
            .get()
            .getResource("player_blue_industry", EntityIndustry.class)
            .produce();
        Engine.get().getNEntityStream().addEntity(player);
        Engine.get().getNEntityStream().cleanup();
        Engine
            .get()
            .setCurrentUI(
                voide.resources.Resources
                    .get()
                    .getResource(UIType.PLAY.key(), UI.class)
            );
        Engine.get().setEngineState(Engine.EngineState.PLAY);
    }

    /**
     * Go to the previous screen
     */
    public static void back() {
        Engine
            .get()
            .setCurrentUI(
                voide.resources.Resources
                    .get()
                    .getResource(UIType.MAIN.key(), UI.class)
            );
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
        if (k.get(SpaxelKey.ESC).isRelease()) {
            back();
        }
    }
}
