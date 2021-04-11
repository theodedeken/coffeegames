package spaxel.factories.entities;

import spaxel.entity.Component;
import spaxel.entity.ComponentType;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.EntityType;
import spaxel.entity.Entity;
import java.util.Map;

/**
 * Created by theo on 5/06/17.
 */
public class StaticParticleIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage pc, RenderableStorage rc) {
        Entity entity = new Entity(EntityType.RANDOM_PARTICLE);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        components.put(rc.getType(), rc);
        entity.setComponents(components);
        return entity;
    }
}
