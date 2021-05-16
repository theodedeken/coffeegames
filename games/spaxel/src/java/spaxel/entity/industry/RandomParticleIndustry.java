package spaxel.entity.industry;

import spaxel.entity.storage.age.AgeStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Component;
import voide.entity.Entity;
import voide.entity.EntityIndustry;
import spaxel.entity.SpaxelEntity;

import java.util.Map;

/**
 * Created by theo on 5/06/17.
 */
public class RandomParticleIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage pc, AgeStorage ac, ChangeStorage vc, RenderableStorage sc) {
        Entity entity = new Entity(SpaxelEntity.RANDOM_PARTICLE);
        Map<String, Component> components = buildComponents();
        components.put(pc.getType().id(), pc);
        components.put(ac.getType().id(), ac);
        components.put(vc.getType().id(), vc);
        components.put(sc.getType().id(), sc);
        entity.setComponents(components);
        return entity;
    }
}
