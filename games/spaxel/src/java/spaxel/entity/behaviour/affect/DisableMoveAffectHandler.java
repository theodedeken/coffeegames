package spaxel.entity.behaviour.affect;

import spaxel.entity.storage.status.StatusStorage;
import voide.entity.Entity;
import spaxel.entity.SpaxelComponent;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableMoveAffectHandler extends AffectHandler {
    public DisableMoveAffectHandler() {
        super(AffectType.DISABLE_MOVE);
    }

    public void affect(Entity entity) {
        StatusStorage ac = (StatusStorage) entity.getParent().getComponent(SpaxelComponent.STATUS);
        ac.setCanMove(false);
    }
}
