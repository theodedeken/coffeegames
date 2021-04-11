package spaxel.factories.entities;

import spaxel.entity.storage.age.AgeStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.Component;
import spaxel.entity.ComponentType;
import spaxel.entity.EntityType;
import spaxel.entity.Entity;
import java.util.Map;

/**
 * Created by theo on 5/06/17.
 */
public class RandomParticleIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage pc, AgeStorage ac, ChangeStorage vc,
            RenderableStorage sc) {
        Entity entity = new Entity(EntityType.RANDOM_PARTICLE);
        Map<ComponentType, Component> components = buildComponents();
        components.put(pc.getType(), pc);
        components.put(ac.getType(), ac);
        components.put(vc.getType(), vc);
        components.put(sc.getType(), sc);
        entity.setComponents(components);
        return entity;
    }
}
