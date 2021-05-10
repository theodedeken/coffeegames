package spaxel.ui.controllers;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.experience.ExperienceStorage;
import spaxel.entity.storage.health.HealthStorage;
import spaxel.entity.storage.item.ItemContainer;
import spaxel.entity.storage.item.ItemStorage;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.engine.Resources;
import spaxel.system.SystemType;
import spaxel.factories.elements.ElementCreator;
import voide.input.Keyboard;
import spaxel.util.DebugRenderer;
import spaxel.ui.elements.Element;
import spaxel.ui.elements.UIType;
import java.util.Set;
import spaxel.Constants;
import spaxel.logger.Logger;
import voide.input.Key;

/**
 * Callbacks for the elements in the play UI.
 */
public final class PlayController {
    private static final int SECONDS_INA_MIN = 60;
    private static final int TWO_FIGURE = 10;
    private static final String VISIBLE = "visible";

    private PlayController() {
    }

    /**
     * Update the time label while playing the game
     * 
     * @param element the label element
     */
    public static void updateTime(Element element) {
        int gt = Engine.get().getGameState().getGameTime();
        int min = gt / SECONDS_INA_MIN;
        int sec = gt % SECONDS_INA_MIN;
        String mintext = min < TWO_FIGURE ? "0" + min : "" + min;
        String sectext = sec < TWO_FIGURE ? "0" + sec : "" + sec;
        element.getStyle().setProperty("text", mintext + "\\" + sectext);
    }

    /**
     * Update the score label while playing the game
     * 
     * @param element the score label
     */
    public static void updateScore(Element element) {
        element.getStyle().setProperty("text", String.valueOf(Engine.get().getGameState().getScore()));
    }

    /**
     * Update the HP bar element while playing the game
     * 
     * @param element the hp bar element
     */
    public static void updateHpBar(Element element) {
        HealthStorage hc = (HealthStorage) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.HEALTH);

        element.getStyle().setProperty("completion",
                String.valueOf((double) hc.getCurrentHealth() / hc.getMaxHealth()));
        element.findById("hp_label").getStyle().setProperty("text", hc.getCurrentHealth() + " / " + hc.getMaxHealth());
    }

    /**
     * Update the XP bar element while playing the game
     * 
     * @param element the xp bar element
     */
    public static void updateXpBar(Element element) {
        ExperienceStorage ec = (ExperienceStorage) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.EXPERIENCE);
        element.getStyle().setProperty("completion", String.valueOf((double) ec.getCurrentXp() / ec.getMaxXp()));

        element.findById("xp_label").getStyle().setProperty("text", ec.getCurrentXp() + " / " + ec.getMaxXp());
    }

    private static void updateContainer(Element element, Set<Entity> items) {
        element.clearChildren();
        for (Entity item : items) {
            element.addElement(ElementCreator.createItemView(item));
        }
        element.initStyle(element.getStylesheets());
    }

    /**
     * Update the container showing all Primary items
     * 
     * @param element the container
     */
    public static void updatePrimaryContainer(Element element) {
        updateContainer(element, Engine.get().getNEntityStream().getPlayer().getLinks(
                (e) -> ((ItemStorage) e.getComponent(ComponentType.ITEM)).getContainer() == ItemContainer.PRIMARY));
    }

    /**
     * Update the container showing all Secondary items
     * 
     * @param element the container
     */
    public static void updateSecondaryContainer(Element element) {
        updateContainer(element, Engine.get().getNEntityStream().getPlayer().getLinks(
                (e) -> ((ItemStorage) e.getComponent(ComponentType.ITEM)).getContainer() == ItemContainer.SECONDARY));
    }

    /**
     * Update the container showing all Ship items
     * 
     * @param element the container
     */
    public static void updateShipContainer(Element element) {
        updateContainer(element, Engine.get().getNEntityStream().getPlayer().getLinks(
                (e) -> ((ItemStorage) e.getComponent(ComponentType.ITEM)).getContainer() == ItemContainer.SHIP));
    }

    /**
     * Checks whether the log key is pressed and makes the logger visible if that is
     * the case.
     * 
     * @param element the logging ui element
     */
    public static void logCheck(Element element) {
        Keyboard k = Engine.get().getKeyboard();

        if (k.get(Key.LOG).isRelease()) {
            Engine.get().getGameState().toggleLogging();
            element.getStyle().setProperty(VISIBLE, String.valueOf(Engine.get().getGameState().isLogging()));
        }
    }

    /**
     * Checks whether the debug key is pressed and makes the debug screen visible if
     * that is the case
     * 
     * @param element the debug ui element
     */
    public static void debugCheck(Element element) {
        Keyboard k = Engine.get().getKeyboard();

        if (k.get(Key.DEBUG).isRelease()) {
            Engine.get().getGameState().toggleDebug();
            element.getStyle().setProperty(VISIBLE, String.valueOf(Engine.get().getGameState().isDebug()));
        }
    }

    /**
     * Resume playing the game
     */
    public static void resume() {
        Engine.get().setEngineState(Engine.EngineState.PLAY);
        Engine.get().getCurrentUI().findById("pause_controls").getStyle().setProperty(VISIBLE, "false");
    }

    /**
     * Quit playing the game
     */
    public static void quit() {
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.MAIN));
        Engine.get().setEngineState(Engine.EngineState.MENU);
        Engine.get().stopGame();
    }

    /**
     * Checks if the esc key is pressed and toggles the pause menu if that is the
     * case
     * 
     * @param element the pause controls element
     */
    public static void escCheck(Element element) {
        Keyboard k = Engine.get().getKeyboard();
        if (k.get(Key.ESC).isRelease()) {
            if (Engine.get().getEngineState() == Engine.EngineState.PAUSE) {
                resume();
            } else {
                Engine.get().setEngineState(Engine.EngineState.PAUSE);
                element.getStyle().setProperty(VISIBLE, "true");
            }
        }
    }

    /**
     * Inits the debug element
     * 
     * @return the debug element
     */
    public static Element initDebug() {
        return DebugRenderer.createDebugElement();
    }

    /**
     * Inits the debug element
     * 
     * @return the debug element
     */
    public static Element initLogging() {
        return DebugRenderer.createLoggerElement();
    }

    /**
     * Updates the debug label
     * 
     * @param element the debug label
     */
    public static void updateDebugLabel(Element element) {
        ComponentType type = ComponentType.valueOf(element.getId().toUpperCase());
        int size = Engine.get().getNEntityStream().getEntities(type).size();
        element.getStyle().setProperty("text", type.getName() + ": " + size);
    }

    /**
     * Updates the logging label
     * 
     * @param element the logging label
     */
    public static void updateLoggingLabel(Element element) {
        Logger logger = Engine.get().getLogger();
        SystemType type = SystemType.valueOf(element.getId().toUpperCase());

        long dif = logger.getHistory().get(type).getLast().getDifference() / Constants.NS_PER_US;
        long sum = logger.getRollingSum().get(type);
        long avg = (sum / logger.getCurrentAvg()) / Constants.NS_PER_US;

        element.getStyle().setProperty("text", type.getName() + ": " + avg + "(" + dif + ")");
    }

}
