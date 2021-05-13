package spaxel.util;

import spaxel.entity.ComponentType;
import spaxel.engine.Engine;
import spaxel.system.SystemType;
import spaxel.graphics.buffer.MasterBuffer;
import spaxel.graphics.buffer.RenderLayer;
import voide.graphics.renderable.Renderable;
import voide.math.VectorD;
import voide.render.RenderJob;
import spaxel.ui.elements.Element;
import spaxel.Constants;
import spaxel.engine.Resources;
import spaxel.logger.Logger;

/**
 * Provides methods for the creation of debug ui elements
 * 
 * Created by theo on 26/06/17.
 */
public final class DebugRenderer {
    private static final int DOT_SEPARATION = 64;

    private DebugRenderer() {
    }

    /**
     * Creates the debug ui element
     * 
     * @return the debug ui element
     */
    public static Element createDebugElement() {
        Element debug = new Element();

        for (ComponentType type : ComponentType.values()) {
            Element label = new Element();
            label.setId(type.getName());
            label.setClasses("debug_label");
            int size = Engine.get().getNEntityStream().getEntities(type).size();
            label.getStyle().setProperty("text", type.getName() + ": " + size);
            debug.addElement(label);
        }
        return debug;
    }

    /**
     * Creates the logging ui element
     * 
     * @return the logging ui element
     */
    public static Element createLoggerElement() {
        Element log = new Element();

        Logger logger = Engine.get().getLogger();

        for (SystemType type : SystemType.values()) {
            if (type != SystemType.RENDER) {
                long dif = logger.getHistory().get(type).getLast().getDifference() / Constants.NS_PER_US;
                long sum = logger.getRollingSum().get(type);
                long avg = (sum / logger.getCurrentAvg()) / Constants.NS_PER_US;
                Element label = new Element();
                label.setId(type.getName());
                label.setClasses("logging_label");
                label.getStyle().setProperty("text", type.getName() + ": " + avg + "(" + dif + ")");
                log.addElement(label);
            }
        }
        return log;
    }

    /**
     * Render dots on the screen
     * 
     * @param buffer the master buffer of the game
     */
    public void renderDots(MasterBuffer buffer) {
        Renderable dot = Resources.get().getRenderables().get("dot");
        VectorD origin = new VectorD(Engine.get().getGameState().getScreenOffset().getValue(0) % DOT_SEPARATION,
                Engine.get().getGameState().getScreenOffset().getValue(1) % DOT_SEPARATION);
        for (int i = 0; i < Constants.GAME_WIDTH; i += DOT_SEPARATION) {
            for (int j = 0; j < Constants.GAME_HEIGHT; j += DOT_SEPARATION) {
                RenderJob data = new RenderJob();
                data.applyTranslation(origin.sum(new VectorD(i, j)));
                data.applyRot(0);
                dot.apply(data);
                buffer.addNewRenderJob(RenderLayer.GAME, data);
            }
        }
    }
}
