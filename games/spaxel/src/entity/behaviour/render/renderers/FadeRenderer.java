package spaxel.entity.behaviour.render.renderers;

import spaxel.entity.storage.age.AgeStorage;
import spaxel.entity.ComponentType;
import spaxel.entity.Entity;
import spaxel.graphics.buffer.RenderJob;

/**
 * Created by theo on 5/06/17.
 */
public class FadeRenderer extends Renderer {
    public void apply(RenderJob data, Entity entity) {
        AgeStorage ac = (AgeStorage) entity.getComponent(ComponentType.AGE);

        data.applyAlpha((double) ac.getLife() / ac.getMaxLife());
    }
}
