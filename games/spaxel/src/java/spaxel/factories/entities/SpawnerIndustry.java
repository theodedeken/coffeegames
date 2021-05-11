package spaxel.factories.entities;

import spaxel.entity.Component;
import spaxel.entity.ComponentType;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.EntityType;
import spaxel.entity.Entity;
import java.util.Map;

/**
 * Created by theo on 6/06/17.
 */
public class SpawnerIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage trnsfrmStore, RenderableStorage rndrStore) {
        Entity entity = new Entity(EntityType.SPAWNER);
        Map<ComponentType, Component> components = buildComponents();
        components.put(rndrStore.getType(), rndrStore);
        components.put(trnsfrmStore.getType(), trnsfrmStore);
        entity.setComponents(components);
        return entity;
    }
}
