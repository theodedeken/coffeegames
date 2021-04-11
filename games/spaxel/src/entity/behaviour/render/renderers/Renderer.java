package spaxel.entity.behaviour.render.renderers;

import spaxel.entity.Entity;
import spaxel.graphics.buffer.RenderJob;

/**
 * Created by theo on 5/06/17.
 */
public abstract class Renderer {
    private RendererType type;

    public abstract void apply(RenderJob data, Entity entity);

    public static Renderer createRenderer(String tag) {
        switch (tag) {
            case "TEXTURE_PART":
                return new SpriteSheetRenderer();
            case "VELOCITY":
                return new VelocityRenderer();
            case "STATIONARY":
                return new StationaryRenderer();
            case "FADE":
                return new FadeRenderer();
            case "LINK_LINK_VELOCITY":
                return new LinkLinkVelocityRenderer();
            case "SHIELD":
                return new ShieldRenderer();
            case "SHIP_FRAGMENT":
                return new ShipFragmentRenderer();
            case "ABSOLUTE":
                return new AbsoluteRenderer();
            case "MARKER":
                return new MarkerRenderer();
            default:
                throw new RuntimeException("no factory for value " + tag);
        }
    }

    public RendererType getType() {
        return type;
    }

    public void setType(RendererType type) {
        this.type = type;
    }
}
