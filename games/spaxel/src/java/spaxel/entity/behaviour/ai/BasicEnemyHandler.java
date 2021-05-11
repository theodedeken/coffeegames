package spaxel.entity.behaviour.ai;

import spaxel.Constants;
import spaxel.entity.ComponentType;
import spaxel.entity.behaviour.event.Event;
import spaxel.entity.storage.status.StatusStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.storage.move.MoveStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.event.EventStorage;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import voide.math.VectorD;
import spaxel.entity.EntityUtil;

/**
 * Created by theo on 7/06/17.
 */
public class BasicEnemyHandler extends AIHandler {
    private static final int DISTANCE_THRESHOLD = 250;
    private static final int SPEED_MULTIPLIER = 2;

    public BasicEnemyHandler() {
        super(AIType.BASIC_ENEMY);
    }

    public void execute(Entity entity) {
        TransformationStorage playerPos = (TransformationStorage) entity.getStream().getPlayer()
                .getComponent(ComponentType.TRANSFORMATION);

        StatusStorage ac = (StatusStorage) entity.getComponent(ComponentType.STATUS);
        TransformationStorage entityPos = (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
        MoveStorage entityMov = (MoveStorage) entity.getComponent(ComponentType.MOVE);
        ChangeStorage entityVel = (ChangeStorage) entity.getComponent(ComponentType.CHANGE);
        VectorD diff = playerPos.getPosition().sum(entityPos.getPosition().multiplicate(-1));
        if (ac.canMove()) {
            double rotToGet = Math.atan2(diff.getValue(0), diff.getValue(1));
            if (rotToGet < 0) {
                rotToGet += Constants.FULL_CIRCLE;
            }
            double rotChange = rotToGet - entityPos.getRotation();
            entityVel.setRotationChange(EntityUtil.calculateDeltaRot(rotChange, entityMov.getTurnRate()));

            VectorD velChange = new VectorD(Math.sin(entityPos.getRotation()), Math.cos(entityPos.getRotation()))
                    .multiplicate(entityMov.getAcceleration());

            double dist = playerPos.getPosition().sum(entityPos.getPosition().multiplicate(-1)).length();
            if (dist > DISTANCE_THRESHOLD) {
                if (entityVel.getPositionChange().sum(velChange).length() < entityMov.getSpeed()) {
                    entityVel.setPositionChange(entityVel.getPositionChange().sum(velChange));
                } else {
                    // TODO needs a rewrite
                    entityVel
                            .setPositionChange(entityVel.getPositionChange()
                                    .sum(entityVel.getPositionChange()
                                            .multiplicate(-1 / entityMov.getSpeed() * SPEED_MULTIPLIER))
                                    .sum(velChange));
                }
            } else {
                entityVel.setPositionChange(entityVel.getPositionChange().sum(velChange.multiplicate(-1)));
            }
        }
        if (ac.canShoot()) {
            EventStorage evStore = (EventStorage) entity.getComponent(ComponentType.EVENT_STORE);
            evStore.addEvent(Event.PRIMARY_SHOOT);
            evStore.addEvent(Event.SECONDARY_SHOOT);
        }
    }
}
