package spaxel.entity.behaviour.mouse;

import spaxel.Constants;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.behaviour.event.Event;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.event.EventStorage;
import spaxel.entity.storage.move.MoveStorage;
import spaxel.entity.storage.status.StatusStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.engine.Engine;
import voide.entity.Entity;
import voide.input.MouseWrapper;
import voide.math.VectorD;
import spaxel.entity.EntityUtil;

public class PlayerMouseHandler extends MouseHandler {
    public PlayerMouseHandler() {
        super(MouseHandlerType.PLAYER);
    }

    public void handle(Entity entity, MouseWrapper mouse) {
        StatusStorage statStore = (StatusStorage) entity.getComponent(SpaxelComponent.STATUS);

        TransformationStorage trnsStore = (TransformationStorage) entity.getComponent(SpaxelComponent.TRANSFORMATION);
        ChangeStorage chngStore = (ChangeStorage) entity.getComponent(SpaxelComponent.CHANGE);
        MoveStorage mvStore = (MoveStorage) entity.getComponent(SpaxelComponent.MOVE);

        VectorD mousePos = mouse.getPos();

        VectorD diff = mousePos
                .sum(trnsStore.getPosition().sum(Engine.get().getGameState().getScreenOffset()).multiplicate(-1));
        double rotToGet = diff.angle();

        if (rotToGet < 0) {
            rotToGet += Constants.FULL_CIRCLE;
        }
        double rotChange = rotToGet - trnsStore.getRotation();

        chngStore.setRotationChange(EntityUtil.calculateDeltaRot(rotChange, mvStore.getTurnRate()));

        if (statStore.canShoot()) {
            EventStorage evStore = (EventStorage) entity.getComponent(SpaxelComponent.EVENT_STORE);
            if (mouse.getMouse1().isDown()) {
                evStore.addEvent(Event.PRIMARY_SHOOT);
            }
            if (mouse.getMouse2().isDown()) {
                evStore.addEvent(Event.SECONDARY_SHOOT);
            }
        }
    }
}
