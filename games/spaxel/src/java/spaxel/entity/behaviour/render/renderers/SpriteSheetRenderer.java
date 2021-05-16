package spaxel.entity.behaviour.render.renderers;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.renderable.RenderableStorage;
import voide.entity.Entity;
import voide.render.buffer.RenderJob;

/**
 * Created by theo on 5/06/17.
 */
public class SpriteSheetRenderer extends Renderer {
    public void apply(RenderJob data, Entity entity) {
        RenderableStorage sc = (RenderableStorage) entity.getComponent(SpaxelComponent.RENDERABLE);

        sc.getRenderable().apply(data);
    }
}
