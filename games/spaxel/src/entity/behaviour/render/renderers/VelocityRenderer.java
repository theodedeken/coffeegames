package spaxel.entity.behaviour.render.renderers;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.graphics.buffer.RenderJob;
import voide.math.VectorD;

/**
 * Created by theo on 5/06/17.
 */
public class VelocityRenderer extends Renderer {
        public void apply(RenderJob data, Entity entity) {
                TransformationStorage pc = (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
                ChangeStorage vc = (ChangeStorage) entity.getComponent(ComponentType.CHANGE);
                VectorD pos = pc.getPosition().sum(Engine.get().getGameState().getScreenOffset())
                                .sum(vc.getPositionChange().multiplicate(Engine.get().getGameState().getUpdateTime()));
                double rot = pc.getRotation()
                                + vc.getRotationationChange() * Engine.get().getGameState().getUpdateTime();

                data.applyTranslation(pos);
                data.applyScale(pc.getScale());
                data.applyRot(rot);
        }
}
