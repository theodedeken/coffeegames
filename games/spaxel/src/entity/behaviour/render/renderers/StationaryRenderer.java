package spaxel.entity.behaviour.render.renderers;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.graphics.buffer.RenderJob;
import voide.math.VectorD;

/**
 * Created by theo on 5/06/17.
 */
public class StationaryRenderer extends Renderer {
    public void apply(RenderJob data, Entity entity) {
        TransformationStorage pc = (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
        VectorD pos = pc.getPosition().sum(Engine.get().getGameState().getScreenOffset());

        data.applyTranslation(pos);
        data.applyScale(pc.getScale());
        data.applyRot(pc.getRotation());
    }
}
