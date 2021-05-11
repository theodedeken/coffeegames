package spaxel.factories.entities;

import spaxel.entity.Component;
import spaxel.entity.ComponentType;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.EntityType;
import spaxel.entity.Entity;
import java.util.Map;

/**
 * Created by theo on 7/06/17.
 */
public class EnemyIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage pc) {
        Entity entity = new Entity(EntityType.ENEMY);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        entity.setComponents(components);
        return entity;
    }
}
