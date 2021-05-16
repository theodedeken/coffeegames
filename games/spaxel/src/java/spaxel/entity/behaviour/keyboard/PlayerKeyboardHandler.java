package spaxel.entity.behaviour.keyboard;

import spaxel.Constants;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.move.MoveStorage;
import spaxel.entity.storage.status.StatusStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;
import spaxel.input.SpaxelKey;
import voide.input.Keyboard;
import voide.math.VectorD;

public class PlayerKeyboardHandler extends KeyboardHandler {
    public PlayerKeyboardHandler() {
        super(KeyboardHandlerType.PLAYER);
    }

    public void handle(Entity entity, Keyboard keyboard) {
        StatusStorage statStore = (StatusStorage) entity.getComponent(SpaxelComponent.STATUS);

        if (statStore.canMove()) {
            ChangeStorage chngStore = (ChangeStorage) entity.getComponent(SpaxelComponent.CHANGE);
            MoveStorage mvStore = (MoveStorage) entity.getComponent(SpaxelComponent.MOVE);
            TransformationStorage trnsStore = (TransformationStorage) entity
                    .getComponent(SpaxelComponent.TRANSFORMATION);

            VectorD velChange = chngStore.getPositionChange()
                    .multiplicate(-1 / (mvStore.getSpeed() * Constants.SPEED_MULT));
            if (keyboard.get(SpaxelKey.DOWN).isDown()) {
                velChange = new VectorD(-Math.sin(trnsStore.getRotation()), -Math.cos(trnsStore.getRotation()))
                        .multiplicate(mvStore.getAcceleration());
            }
            if (keyboard.get(SpaxelKey.UP).isDown()) {
                velChange = new VectorD(Math.sin(trnsStore.getRotation()), Math.cos(trnsStore.getRotation()))
                        .multiplicate(mvStore.getAcceleration());
            }
            if (keyboard.get(SpaxelKey.LEFT).isDown()) {
                velChange = new VectorD(Math.sin(trnsStore.getRotation() - Constants.HALF_CIRLCE),
                        Math.cos(trnsStore.getRotation() - Constants.HALF_CIRLCE))
                                .multiplicate(mvStore.getAcceleration());
            }

            if (keyboard.get(SpaxelKey.RIGHT).isDown()) {
                velChange = new VectorD(Math.sin(trnsStore.getRotation() + Constants.HALF_CIRLCE),
                        Math.cos(trnsStore.getRotation() + Constants.HALF_CIRLCE))
                                .multiplicate(mvStore.getAcceleration());
            }
            if (chngStore.getPositionChange().sum(velChange).length() < mvStore.getSpeed()) {
                chngStore.setPositionChange(chngStore.getPositionChange().sum(velChange));
            } else {
                chngStore.setPositionChange(chngStore.getPositionChange().sum(
                        chngStore.getPositionChange().multiplicate(-1 / (mvStore.getSpeed() * Constants.SPEED_MULT))));
            }
        }
    }
}
