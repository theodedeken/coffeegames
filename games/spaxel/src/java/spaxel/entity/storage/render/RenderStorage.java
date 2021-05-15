package spaxel.entity.storage.render;

import spaxel.entity.ComponentType;
import spaxel.entity.Storage;
import voide.render.buffer.RenderLayer;

public class RenderStorage extends Storage {
    private boolean visible;
    private RenderLayer layer;

    public RenderStorage() {
        super(ComponentType.RENDER_STORE);
    }

    public RenderStorage(boolean visible, RenderLayer layer) {
        super(ComponentType.RENDER_STORE);
        this.visible = visible;
        this.layer = layer;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the layer
     */
    public RenderLayer getLayer() {
        return layer;
    }

    /**
     * @param layer the layer to set
     */
    public void setLayer(RenderLayer layer) {
        this.layer = layer;
    }

    public RenderStorage copy() {
        return new RenderStorage(visible, layer);
    }
}