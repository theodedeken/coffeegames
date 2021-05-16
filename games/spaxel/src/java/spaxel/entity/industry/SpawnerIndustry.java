package spaxel.entity.industry;

import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Component;
import voide.entity.Entity;
import voide.entity.EntityIndustry;
import spaxel.entity.SpaxelEntity;

import java.util.Map;

/**
 * Created by theo on 6/06/17.
 */
public class SpawnerIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage trnsfrmStore, RenderableStorage rndrStore) {
        Entity entity = new Entity(SpaxelEntity.SPAWNER);
        Map<String, Component> components = buildComponents();
        components.put(rndrStore.getType().id(), rndrStore);
        components.put(trnsfrmStore.getType().id(), trnsfrmStore);
        entity.setComponents(components);
        return entity;
    }
}
