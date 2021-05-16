package spaxel.entity.industry;

import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Component;
import voide.entity.Entity;
import voide.entity.EntityIndustry;
import spaxel.entity.SpaxelEntity;

import java.util.Map;

/**
 * Created by theo on 7/06/17.
 */
public class EnemyIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage pc) {
        Entity entity = new Entity(SpaxelEntity.ENEMY);
        Map<String, Component> components = buildComponents();
        components.put(pc.getType().id(), pc);
        entity.setComponents(components);
        return entity;
    }
}
