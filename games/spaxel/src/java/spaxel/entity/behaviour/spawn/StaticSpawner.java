package spaxel.entity.behaviour.spawn;

import java.util.Collections;
import java.util.List;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.industry.StaticParticleIndustry;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.spawn.SpawnStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;

public class StaticSpawner extends Spawner {

    public StaticSpawner() {
        super(SpawnerType.STATIC);
    }

    public List<Entity> spawn(Entity entity) {
        SpawnStorage spwnStorage = (SpawnStorage) entity.getComponent(
            SpaxelComponent.SPAWN_STORE
        );
        TransformationStorage trnsfrmStorage = (TransformationStorage) entity.getComponent(
            SpaxelComponent.TRANSFORMATION
        );
        RenderableStorage rndrStorage = (RenderableStorage) entity.getComponent(
            SpaxelComponent.RENDERABLE
        );
        StaticParticleIndustry industry = (StaticParticleIndustry) spwnStorage.getIndustry();

        return Collections.singletonList(
            industry.produce(trnsfrmStorage.copy(), rndrStorage.copy())
        );
    }
}
