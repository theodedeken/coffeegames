package spaxel.entity.behaviour.render.renderers;

import spaxel.engine.Engine;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;
import voide.math.VectorD;
import voide.render.buffer.RenderJob;

/**
 * Created by theo on 8/07/17.
 */
public class LinkLinkVelocityRenderer extends Renderer {

    public void apply(RenderJob data, Entity entity) {
        Entity link = entity.getParent();
        Entity linkLink = link.getParent();
        TransformationStorage pc = (TransformationStorage) linkLink.getComponent(
            SpaxelComponent.TRANSFORMATION
        );
        ChangeStorage vc = (ChangeStorage) linkLink.getComponent(
            SpaxelComponent.CHANGE
        );

        VectorD pos = pc
            .getPosition()
            .sum(Engine.get().getGameState().getScreenOffset())
            .sum(
                vc
                    .getPositionChange()
                    .multiplicate(Engine.get().getGameState().getUpdateTime())
            );
        double rot =
            pc.getRotation() +
            vc.getRotationationChange() *
            Engine.get().getGameState().getUpdateTime();

        data.applyTranslation(pos);
        data.applyScale(pc.getScale());
        data.applyRot(rot);
    }
}
