package spaxel.util;

import spaxel.Constants;
import spaxel.engine.Engine;
import spaxel.entity.SpaxelComponent;
import spaxel.system.SystemType;
import voide.graphics.renderable.Rectangle;
import voide.graphics.renderable.Renderable;
import voide.logger.Logger;
import voide.math.VectorD;
import voide.render.buffer.MasterBuffer;
import voide.render.buffer.RenderJob;
import voide.render.buffer.RenderLayer;
import voide.ui.elements.Element;

/**
 * Provides methods for the creation of debug ui elements
 *
 * Created by theo on 26/06/17.
 */
public final class DebugRenderer {

    private static final int DOT_SEPARATION = 64;

    private DebugRenderer() {}

    /**
     * Creates the debug ui element
     *
     * @return the debug ui element
     */
    public static Element createDebugElement() {
        Element debug = new Element();

        for (SpaxelComponent type : SpaxelComponent.values()) {
            Element label = new Element();
            label.setId(type.getName());
            label.setClasses("debug_label");
            int size = Engine.get().getEntityStream().getEntities(type).size();
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
                long dif =
                    logger
                        .getHistory()
                        .get(type.name())
                        .getLast()
                        .getDifference() /
                    Constants.NS_PER_US;
                long sum = logger.getRollingSum().get(type.name());
                long avg = (sum / logger.getCurrentAvg()) / Constants.NS_PER_US;
                Element label = new Element();
                label.setId(type.getName());
                label.setClasses("logging_label");
                label
                    .getStyle()
                    .setProperty(
                        "text",
                        type.getName() + ": " + avg + "(" + dif + ")"
                    );
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
        Renderable dot = voide.resources.Resources
            .get()
            .getResource("dot", Rectangle.class);
        VectorD origin = new VectorD(
            Engine.get().getGameState().getScreenOffset().getValue(0) %
            DOT_SEPARATION,
            Engine.get().getGameState().getScreenOffset().getValue(1) %
            DOT_SEPARATION
        );
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
