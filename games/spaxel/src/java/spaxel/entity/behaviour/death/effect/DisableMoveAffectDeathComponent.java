package spaxel.entity.behaviour.death.effect;

import spaxel.entity.storage.status.StatusStorage;
import voide.entity.Entity;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.behaviour.death.DeathHandler;
import spaxel.entity.behaviour.death.DeathType;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableMoveAffectDeathComponent extends DeathHandler {
    public DisableMoveAffectDeathComponent() {
        super(DeathType.DISABLE_MOVE_AFFECT);
    }

    public void die(Entity entity) {
        Entity parent = entity.getParent();
        StatusStorage mc = (StatusStorage) parent.getComponent(SpaxelComponent.STATUS);
        mc.setCanMove(true);
    }

}
