package spaxel.entity.industry;

import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Component;
import voide.entity.Entity;
import voide.entity.EntityIndustry;
import spaxel.entity.SpaxelEntity;

import java.util.Map;

/**
 * Created by theo on 18/06/17.
 */
public class ProjectileIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage pc, Entity shooter) {
        Entity entity = new Entity(SpaxelEntity.PROJECTILE);
        Map<String, Component> components = buildComponents();
        components.put(pc.getType().id(), pc);
        // components.put(lc.getType(), lc);
        entity.setComponents(components);
        shooter.addLink(entity);
        return entity;
    }
}
