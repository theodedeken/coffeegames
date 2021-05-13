package spaxel.entity.behaviour.render.renderers;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.renderable.RenderableStorage;
import voide.render.RenderJob;
import spaxel.entity.Entity;

/**
 * Created by theo on 5/06/17.
 */
public class SpriteSheetRenderer extends Renderer {
    public void apply(RenderJob data, Entity entity) {
        RenderableStorage sc = (RenderableStorage) entity.getComponent(ComponentType.RENDERABLE);

        sc.getRenderable().apply(data);
    }
}
