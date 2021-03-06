package spaxel.entity.behaviour.affect;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.affect.AffectStorage;
import spaxel.entity.storage.change.ChangeStorage;
import voide.entity.Entity;

/**
 * Created by theod on 28-6-2017.
 */
public class SlowAffectHandler extends AffectHandler {

    public SlowAffectHandler() {
        super(AffectType.SLOW);
    }

    public void affect(Entity entity) {
        ChangeStorage chngStore = (ChangeStorage) entity
            .getParent()
            .getComponent(SpaxelComponent.CHANGE);
        AffectStorage afctStore = (AffectStorage) entity.getComponent(
            SpaxelComponent.AFFECT_STORE
        );

        chngStore.setPositionChange(
            chngStore.getPositionChange().multiplicate(afctStore.getFactor())
        );
    }
}
