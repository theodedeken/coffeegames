package spaxel.entity.behaviour.death.actor;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.age.AgeStorage;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.behaviour.ai.AIBehaviour;
import spaxel.entity.behaviour.ai.DroppedItemAIHandler;
import spaxel.entity.behaviour.death.DeathHandler;
import spaxel.entity.behaviour.death.DeathType;
import spaxel.entity.behaviour.hit.EquipHandler;
import spaxel.entity.behaviour.hit.HitBehaviour;
import spaxel.entity.storage.experience.ExperienceStorage;
import spaxel.entity.storage.item.ItemName;
import spaxel.engine.Engine;
import spaxel.engine.Resources;
import spaxel.entity.Entity;
import spaxel.factories.entities.SpawnerIndustry;
import spaxel.util.SpriteDataUtil;
import voide.graphics.renderable.Texture;
import spaxel.entity.EntityUtil;
import spaxel.util.SpaxelRandom;

/**
 * Executed on the death of the entity
 * 
 * Created by theo on 24/06/17.
 */
public class BasicEnemyDeathComponent extends DeathHandler {
        private static final int BASIC_ENEMY_SCORE = 100;
        private static final double BASIC_ENEMY_DROPRATE = 0.25;
        private static final int ITEM_LIFETIME = 500;
        private static final int DEATH_PARTICLE_SIZE = 6;
        private static final int BASIC_ENEMY_XP_GAIN = 25;

        private SpaxelRandom random;

        /**
         * Create a new BasicEnemyDeathComponent
         */
        public BasicEnemyDeathComponent() {
                super(DeathType.BASIC_ENEMY);
                random = new SpaxelRandom();
        }

        public void die(Entity entity) {
                SpawnerIndustry hpsi = (SpawnerIndustry) Resources.get().getIndustryMap()
                                .get("enemy_death_particle_spawner_industry");
                RenderableStorage rndrStore = (RenderableStorage) entity.getComponent(ComponentType.RENDERABLE);
                TransformationStorage trnsStore = (TransformationStorage) entity
                                .getComponent(ComponentType.TRANSFORMATION);

                RenderableStorage particle = new RenderableStorage(SpriteDataUtil.getRandomPart(
                                (Texture) rndrStore.getRenderable(), DEATH_PARTICLE_SIZE, DEATH_PARTICLE_SIZE));
                // add particle effect
                entity.getStream().addEntity(hpsi.produce(trnsStore, particle));

                Engine.get().getGameState().addScore(BASIC_ENEMY_SCORE);
                // add experience
                Entity player = entity.getStream().getPlayer();
                ExperienceStorage exp = (ExperienceStorage) player.getComponent(ComponentType.EXPERIENCE);
                exp.addXp(BASIC_ENEMY_XP_GAIN);
                // chance of dropping item
                if (random.pass(BASIC_ENEMY_DROPRATE)) {
                        Entity item = Resources.get().getItems().produceRandom(prop -> EntityUtil
                                        .getAllItemNames(entity).contains(ItemName.valueOf(prop.getName())));
                        item.addComponent(new HitBehaviour(new EquipHandler()));
                        item.addComponent(new AgeStorage(ITEM_LIFETIME, ITEM_LIFETIME));
                        item.addComponent(trnsStore.copy());
                        item.addComponent(entity.getComponent(ComponentType.CHANGE));
                        item.addComponent(entity.getComponent(ComponentType.RENDER));
                        item.addComponent(new AIBehaviour(new DroppedItemAIHandler()));
                        entity.getStream().addEntity(item);
                }
        }
}
