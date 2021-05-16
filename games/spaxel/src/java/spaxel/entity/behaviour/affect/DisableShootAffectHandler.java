package spaxel.entity.behaviour.affect;

import spaxel.entity.storage.status.StatusStorage;
import voide.entity.Entity;
import spaxel.entity.SpaxelComponent;

/**
 * Created by theod on 28-6-2017.
 */
public class DisableShootAffectHandler extends AffectHandler {
    public DisableShootAffectHandler() {
        super(AffectType.DISABLE_SHOOT);
    }

    public void affect(Entity entity) {
        StatusStorage ac = (StatusStorage) entity.getParent().getComponent(SpaxelComponent.STATUS);
        ac.setCanShoot(false);
    }
}
