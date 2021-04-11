package spaxel.factories.entities;

import spaxel.entity.Component;
import spaxel.entity.ComponentType;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.EntityType;
import spaxel.entity.Entity;
import java.util.Map;

/**
 * Created by theo on 18/06/17.
 */
public class ProjectileIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage pc, Entity shooter) {
        Entity entity = new Entity(EntityType.PROJECTILE);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        // components.put(lc.getType(), lc);
        entity.setComponents(components);
        shooter.addLink(entity);
        return entity;
    }
}
