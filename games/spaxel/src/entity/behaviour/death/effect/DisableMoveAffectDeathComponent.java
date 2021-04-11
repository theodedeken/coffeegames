package spaxel.entity.behaviour.death.effect;

import spaxel.entity.storage.status.StatusStorage;
import spaxel.entity.ComponentType;
import spaxel.entity.behaviour.death.DeathHandler;
import spaxel.entity.behaviour.death.DeathType;
import spaxel.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableMoveAffectDeathComponent extends DeathHandler {
    public DisableMoveAffectDeathComponent() {
        super(DeathType.DISABLE_MOVE_AFFECT);
    }

    public void die(Entity entity) {
        Entity parent = entity.getParent();
        StatusStorage mc = (StatusStorage) parent.getComponent(ComponentType.STATUS);
        mc.setCanMove(true);
    }

}
