package spaxel.entity.behaviour.render.renderers;

import spaxel.entity.Entity;
import voide.render.RenderJob;

/**
 * Created by theo on 5/01/18.
 */
public class MarkerRenderer extends Renderer {
    private static final double MARKER_ALPHA = 0.33D;

    public void apply(RenderJob data, Entity entity) {
        data.applyAlpha(MARKER_ALPHA);
    }
}
