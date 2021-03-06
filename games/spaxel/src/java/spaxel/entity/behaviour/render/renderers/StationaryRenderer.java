package spaxel.entity.behaviour.render.renderers;

import spaxel.engine.Engine;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;
import voide.math.VectorD;
import voide.render.buffer.RenderJob;

/**
 * Created by theo on 5/06/17.
 */
public class StationaryRenderer extends Renderer {

    public void apply(RenderJob data, Entity entity) {
        TransformationStorage pc = (TransformationStorage) entity.getComponent(
            SpaxelComponent.TRANSFORMATION
        );
        VectorD pos = pc
            .getPosition()
            .sum(Engine.get().getGameState().getScreenOffset());

        data.applyTranslation(pos);
        data.applyScale(pc.getScale());
        data.applyRot(pc.getRotation());
    }
}
