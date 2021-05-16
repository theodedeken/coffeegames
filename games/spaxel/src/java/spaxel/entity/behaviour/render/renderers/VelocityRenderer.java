package spaxel.entity.behaviour.render.renderers;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.engine.Engine;
import voide.entity.Entity;
import voide.math.VectorD;
import voide.render.buffer.RenderJob;

/**
 * Created by theo on 5/06/17.
 */
public class VelocityRenderer extends Renderer {
        public void apply(RenderJob data, Entity entity) {
                TransformationStorage pc = (TransformationStorage) entity.getComponent(SpaxelComponent.TRANSFORMATION);
                ChangeStorage vc = (ChangeStorage) entity.getComponent(SpaxelComponent.CHANGE);
                VectorD pos = pc.getPosition().sum(Engine.get().getGameState().getScreenOffset())
                                .sum(vc.getPositionChange().multiplicate(Engine.get().getGameState().getUpdateTime()));
                double rot = pc.getRotation()
                                + vc.getRotationationChange() * Engine.get().getGameState().getUpdateTime();

                data.applyTranslation(pos);
                data.applyScale(pc.getScale());
                data.applyRot(rot);
        }
}
