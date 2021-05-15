package spaxel.ui.render;

import spaxel.ui.styles.Style;
import voide.graphics.animation.Animation;
import voide.graphics.renderable.Renderable;
import voide.math.VectorD;
import voide.render.buffer.MasterBuffer;
import voide.render.buffer.RenderJob;
import voide.render.buffer.RenderLayer;
import spaxel.ui.state.State;

import java.util.Map;

/**
 * Provides methods for rendering style configurations of UI Elements
 */
public class StyleRenderer {
    private TextRenderer textRenderer;

    public StyleRenderer() {
        textRenderer = new TextRenderer();
    }

    /**
     * Render a style configuration
     * 
     * @param style  the style of the element
     * @param buffer the masterbuffer of the rendersystem
     */
    public void renderStyle(Style style, State currentState, int index, MasterBuffer buffer) {
        if ("true".equals(style.getProperty("visible", currentState))) {
            VectorD position = derivePosition(style, currentState, index);
            double rot = deriveRotation(style, currentState);
            double scale = deriveScale(style, currentState);
            if (style.contains("sprite", currentState)) {
                renderSprite(position, rot, scale, style, currentState, buffer);
            }
            if (style.contains("text", currentState)) {
                textRenderer.renderText(position, style, currentState, buffer);
            }
            if (style.contains("animation", currentState)) {
                renderAnimation(position, rot, scale, style, currentState, buffer);
            }
        }
    }

    private VectorD derivePosition(Style style, State currentState, int index) {
        switch (style.getProperty("position", currentState)) {
            case "relative":
                return new VectorD(Double.parseDouble(style.getProperty("x", currentState)),
                        Double.parseDouble(style.getProperty("y", currentState)))
                                .sum(derivePosition(style.getParent(), currentState, index));
            case "flow":
                return deriveFlowPosition(style, currentState, index);
            case "absolute":
            default:
                return new VectorD(Double.parseDouble(style.getProperty("x", currentState)),
                        Double.parseDouble(style.getProperty("y", currentState)));

        }
    }

    private VectorD deriveFlowPosition(Style style, State currentState, int index) {
        VectorD parentPos = derivePosition(style.getParent(), currentState, index);
        int cols = Integer.parseInt(style.getProperty("cols", currentState));
        int rowOffset = Integer.parseInt(style.getProperty("row-offset", currentState));
        int colOffset = Integer.parseInt(style.getProperty("col-offset", currentState));
        int x = index % cols;
        int y = index / cols;
        return parentPos.sum(new VectorD(x * colOffset, y * rowOffset));
    }

    private double deriveRotation(Style style, State currentState) {
        switch (style.getProperty("rotation", currentState)) {
            case "relative":
                return deriveRotation(style.getParent(), currentState)
                        + Double.parseDouble(style.getProperty("rot", currentState));
            case "absolute":
            default:
                return Double.parseDouble(style.getProperty("rot", currentState));
        }
    }

    private double deriveScale(Style style, State currentState) {
        return Double.parseDouble(style.getProperty("scale", currentState));
    }

    private void renderAnimation(VectorD position, double rot, double scale, Style style, State currentState,
            MasterBuffer buffer) {
        double completion = Double.parseDouble(style.getProperty("completion", currentState));
        RenderJob data = voide.resources.Resources.get()
                .getResource(style.getProperty("animation", currentState), Animation.class).getDataAt(completion);
        data.applyTranslation(position);
        data.applyRot(rot);
        data.applyScale(scale);

        buffer.addNewRenderJob(RenderLayer.UI, data);
    }

    private void renderSprite(VectorD position, double rot, double scale, Style style, State currentState,
            MasterBuffer buffer) {
        RenderJob data = new RenderJob();
        Renderable sprite = voide.resources.Resources.get().getResource(style.getProperty("sprite", currentState),
                Renderable.class);
        System.out.println(style.getProperty("sprite", currentState));
        sprite.apply(data);
        data.applyTranslation(position);
        data.applyScale(scale);
        data.applyRot(rot);

        buffer.addNewRenderJob(RenderLayer.UI, data);
    }
}
