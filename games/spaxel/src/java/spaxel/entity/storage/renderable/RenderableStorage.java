package spaxel.entity.storage.renderable;

import spaxel.entity.SpaxelComponent;
import voide.entity.Storage;
import voide.graphics.renderable.Renderable;

public class RenderableStorage extends Storage {

    private Renderable renderable;

    public RenderableStorage() {
        super(SpaxelComponent.RENDERABLE);
    }

    public RenderableStorage(Renderable renderable) {
        super(SpaxelComponent.RENDERABLE);
        this.renderable = renderable;
    }

    /**
     * @return the renderable
     */
    public Renderable getRenderable() {
        return renderable;
    }

    /**
     * @param renderable the renderable to set
     */
    public void setRenderable(Renderable renderable) {
        this.renderable = renderable;
    }

    public void setRenderable(String renderable) {
        this.renderable =
            voide.resources.Resources
                .get()
                .getResource(renderable, Renderable.class);
    }

    public RenderableStorage copy() {
        return new RenderableStorage(renderable);
    }
}
