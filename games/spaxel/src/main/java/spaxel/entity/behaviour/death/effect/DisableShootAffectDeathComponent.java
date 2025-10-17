package spaxel.entity.behaviour.death.effect;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.behaviour.death.DeathHandler;
import spaxel.entity.behaviour.death.DeathType;
import spaxel.entity.storage.status.StatusStorage;
import voide.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableShootAffectDeathComponent extends DeathHandler {

    public DisableShootAffectDeathComponent() {
        super(DeathType.DISABLE_SHOOT_AFFECT);
    }

    public void die(Entity entity) {
        Entity parent = entity.getParent();
        StatusStorage mc = (StatusStorage) parent.getComponent(
            SpaxelComponent.STATUS
        );
        mc.setCanShoot(true);
    }
}
