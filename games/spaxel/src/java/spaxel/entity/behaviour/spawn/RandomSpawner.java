package spaxel.entity.behaviour.spawn;

import java.util.ArrayList;
import java.util.List;
import spaxel.Constants;
import spaxel.entity.ComponentType;
import spaxel.entity.storage.age.AgeStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.spawn.SpawnStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.Entity;
import spaxel.factories.entities.RandomParticleIndustry;
import voide.math.VectorD;
import voide.random.VoideRandom;
import spaxel.engine.Resources;

public class RandomSpawner extends Spawner {
        private VoideRandom rand;

        public RandomSpawner() {
                super(SpawnerType.RANDOM);
                this.rand = new VoideRandom();
        }

        public List<Entity> spawn(Entity entity) {
                SpawnStorage spwnStorage = (SpawnStorage) entity.getComponent(ComponentType.SPAWN_STORE);
                TransformationStorage trnsfrmStorage = (TransformationStorage) entity
                                .getComponent(ComponentType.TRANSFORMATION);
                RenderableStorage rndrStorage = (RenderableStorage) entity.getComponent(ComponentType.RENDERABLE);
                RandomParticleIndustry industry = (RandomParticleIndustry) Resources.get().getIndustryMap()
                                .get(spwnStorage.getIndustry());

                List<Entity> spawned = new ArrayList<>();
                for (int i = 0; i < spwnStorage.getRate(); i++) {
                        int life = rand.nextInt(spwnStorage.getMaxLife());
                        double dir = rand.nextDouble(Constants.FULL_CIRCLE);
                        double speed = rand.nextDouble(spwnStorage.getMaxSpeed());
                        double deltaRot = rand.between(-spwnStorage.getMaxDeltaRot(), spwnStorage.getMaxDeltaRot());
                        double dx = Math.sin(dir) * speed;
                        double dy = Math.cos(dir) * speed;
                        spawned.add(industry.produce(trnsfrmStorage.copy(), new AgeStorage(life, life),
                                        new ChangeStorage(new VectorD(dx, dy), deltaRot, 0), rndrStorage.copy()));
                }
                return spawned;
        }
}
