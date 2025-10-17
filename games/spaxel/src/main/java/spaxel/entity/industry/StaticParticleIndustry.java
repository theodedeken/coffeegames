package spaxel.entity.industry;

import java.util.Map;
import spaxel.entity.SpaxelEntity;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Component;
import voide.entity.Entity;
import voide.entity.EntityIndustry;

/**
 * Created by theo on 5/06/17.
 */
public class StaticParticleIndustry extends EntityIndustry {

    public Entity produce(TransformationStorage pc, RenderableStorage rc) {
        Entity entity = new Entity(SpaxelEntity.RANDOM_PARTICLE);
        Map<String, Component> components = buildComponents();
        components.put(pc.getType().id(), pc);
        components.put(rc.getType().id(), rc);
        entity.setComponents(components);
        return entity;
    }
}
