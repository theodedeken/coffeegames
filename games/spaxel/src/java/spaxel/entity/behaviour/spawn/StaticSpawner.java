package spaxel.entity.behaviour.spawn;

import java.util.Collections;
import spaxel.entity.ComponentType;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.spawn.SpawnStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.engine.Resources;
import spaxel.entity.Entity;
import spaxel.factories.entities.StaticParticleIndustry;
import java.util.List;

public class StaticSpawner extends Spawner {
        public StaticSpawner() {
                super(SpawnerType.STATIC);
        }


        public List<Entity> spawn(Entity entity) {
                SpawnStorage spwnStorage =
                                (SpawnStorage) entity.getComponent(ComponentType.SPAWN_STORE);
                TransformationStorage trnsfrmStorage = (TransformationStorage) entity
                                .getComponent(ComponentType.TRANSFORMATION);
                RenderableStorage rndrStorage =
                                (RenderableStorage) entity.getComponent(ComponentType.RENDERABLE);
                StaticParticleIndustry industry = (StaticParticleIndustry) Resources.get()
                                .getIndustryMap().get(spwnStorage.getIndustry());

                return Collections.singletonList(
                                industry.produce(trnsfrmStorage.copy(), rndrStorage.copy()));
        }
}
