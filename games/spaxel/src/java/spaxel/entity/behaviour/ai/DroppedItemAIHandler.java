package spaxel.entity.behaviour.ai;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.change.ChangeStorage;
import voide.entity.Entity;

/**
 * Created by theod on 11-7-2017.
 */
public class DroppedItemAIHandler extends AIHandler {
    private static final double HALF = 0.5D;

    public DroppedItemAIHandler() {
        super(AIType.DROPPED_ITEM);
    }

    public void execute(Entity entity) {
        ChangeStorage vc = (ChangeStorage) entity.getComponent(SpaxelComponent.CHANGE);
        vc.setPositionChange(vc.getPositionChange().multiplicate(HALF));
    }
}
