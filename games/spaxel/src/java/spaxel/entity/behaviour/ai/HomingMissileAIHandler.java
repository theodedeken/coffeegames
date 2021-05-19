package spaxel.entity.behaviour.ai;

import java.util.Set;
import spaxel.Constants;
import spaxel.entity.EntityUtil;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.move.MoveStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;
import voide.math.VectorD;

/**
 * Created by theo on 8/07/17.
 */
public class HomingMissileAIHandler extends AIHandler {

    private static final int DISTANCE_THRESHOLD = 1000;

    public HomingMissileAIHandler() {
        super(AIType.HOMING_MISSILE);
    }

    public void execute(Entity entity) {
        TransformationStorage pc = (TransformationStorage) entity.getComponent(
            SpaxelComponent.TRANSFORMATION
        );
        MoveStorage mc = (MoveStorage) entity.getComponent(
            SpaxelComponent.MOVE
        );
        ChangeStorage vc = (ChangeStorage) entity.getComponent(
            SpaxelComponent.CHANGE
        );

        Set<Entity> enemies = entity
            .getStream()
            .getEntities(SpaxelComponent.DAMAGE);

        double minDist = -1;
        Entity closest = null;
        for (Entity e : enemies) {
            if (e != entity.getParent()) {
                TransformationStorage epc = (TransformationStorage) e.getComponent(
                    SpaxelComponent.TRANSFORMATION
                );
                double dist = epc
                    .getPosition()
                    .sum(pc.getPosition().multiplicate(-1))
                    .length();
                if (minDist < 0 || dist < minDist) {
                    minDist = dist;
                    closest = e;
                }
            }
        }
        if (closest != null && minDist < DISTANCE_THRESHOLD) {
            TransformationStorage cpc = (TransformationStorage) closest.getComponent(
                SpaxelComponent.TRANSFORMATION
            );

            VectorD diff = cpc
                .getPosition()
                .sum(pc.getPosition().multiplicate(-1));
            double rotToGet = Math.atan2(diff.getValue(0), diff.getValue(1));
            if (rotToGet < 0) {
                rotToGet += Constants.FULL_CIRCLE;
            }
            double rotChange = rotToGet - pc.getRotation();
            vc.setRotationChange(
                EntityUtil.calculateDeltaRot(rotChange, mc.getTurnRate())
            );

            vc.setPositionChange(
                new VectorD(
                    Math.sin(pc.getRotation()),
                    Math.cos(pc.getRotation())
                )
                    .multiplicate(mc.getSpeed())
            );
        }
    }
}
