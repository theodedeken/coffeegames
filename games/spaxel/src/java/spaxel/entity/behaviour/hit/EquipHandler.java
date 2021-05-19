package spaxel.entity.behaviour.hit;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.SpaxelEntity;
import voide.entity.Entity;

/**
 * Created by theo on 24/06/17.
 */
public class EquipHandler extends HitHandler {

    public EquipHandler() {
        super(HitType.EQUIP);
    }

    public void hit(Entity entity, Entity victim) {
        if (victim.getType() == SpaxelEntity.PLAYER) {
            // remove render, hit, transformation, age, velocity, ai
            entity.removeComponent(SpaxelComponent.RENDER);
            entity.removeComponent(SpaxelComponent.HIT);
            entity.removeComponent(SpaxelComponent.TRANSFORMATION);
            entity.removeComponent(SpaxelComponent.AGE);
            entity.removeComponent(SpaxelComponent.CHANGE);
            entity.removeComponent(SpaxelComponent.AI);

            // TODO figure out stacking behaviour
            victim.addLink(entity);
        }
    }
}
