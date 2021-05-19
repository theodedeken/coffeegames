package spaxel.entity.behaviour.render.renderers;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;
import voide.render.buffer.RenderJob;

/**
 * Created by theo on 5/01/18.
 */
public class AbsoluteRenderer extends Renderer {

    public void apply(RenderJob data, Entity entity) {
        TransformationStorage pc = (TransformationStorage) entity.getComponent(
            SpaxelComponent.TRANSFORMATION
        );

        data.applyTranslation(pc.getPosition());
        data.applyScale(pc.getScale());
        data.applyRot(pc.getRotation());
    }
}
