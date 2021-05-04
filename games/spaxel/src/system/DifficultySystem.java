package spaxel.system;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.health.HealthStorage;
import spaxel.entity.storage.item.ItemContainer;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.engine.Engine;
import spaxel.entity.EntityType;
import spaxel.entity.Entity;
import spaxel.system.SystemType;
import spaxel.factories.entities.EnemyIndustry;
import voide.math.VectorD;
import spaxel.entity.EntityUtil;
import spaxel.util.SpaxelRandom;
import java.util.Set;
import spaxel.engine.Resources;

/**
 * The DifficultySystem is responsible for spawning new enemies based on the
 * current difficulty
 * 
 * Created by theo on 3/01/18.
 */
public class DifficultySystem extends GameSystem {
        private static final int MIN_SPAWN_DISTANCE = 500;
        private static final int MAX_SPAWN_DISTANCE = 1000;
        private static final int ENEMY_LEVELUP_TICK = 60;
        private static final int ENEMY_ITEMUP_TICK = 120;
        private static final int TICKS_INA_SECOND = 50;
        private static final int MAX_SPAWN_TIME = 7;
        private static final int NUM_INVENTORIES = 3;
        private int nextSpawn;
        private int spawnCap = 1;
        private int numItems = 1;
        private int maxLevel = 1;
        private String[] enemyIndustries = { "enemy_green_industry", "enemy_white_industry", "enemy_red_industry",
                        "enemy_blue_industry" };
        private SpaxelRandom rand;

        /**
         * Create a new DifficultySystem
         */
        public DifficultySystem() {
                super(SystemType.DIFFICULTY);
                rand = new SpaxelRandom();
        }

        public void update() {
                if (nextSpawn > 0) {
                        nextSpawn--;
                }
                Set<Entity> enemies = Engine.get().getNEntityStream().getEntities(EntityType.ENEMY);
                Entity player = Engine.get().getNEntityStream().getPlayer();
                TransformationStorage playerPos = (TransformationStorage) player
                                .getComponent(ComponentType.TRANSFORMATION);

                if (nextSpawn == 0 && enemies.size() < spawnCap) {
                        EnemyIndustry ei = (EnemyIndustry) Resources.get().getIndustryMap()
                                        .get(rand.choose(enemyIndustries));
                        // entity settings
                        int xOffset = rand.choose(1, -1) * rand.between(MIN_SPAWN_DISTANCE, MAX_SPAWN_DISTANCE);
                        int yOffset = rand.choose(1, -1) * rand.between(MIN_SPAWN_DISTANCE, MAX_SPAWN_DISTANCE);

                        VectorD offset = new VectorD(xOffset, yOffset);

                        Entity entity = ei
                                        .produce(new TransformationStorage(playerPos.getPosition().sum(offset), 0, 0));

                        HealthStorage hlthStore = (HealthStorage) entity.getComponent(ComponentType.HEALTH);

                        int health = EntityUtil.healthAtLevel(rand.between(1, maxLevel + 1), hlthStore.getBaseHealth());
                        hlthStore.setMaxHealth(health);
                        hlthStore.setCurrentHealth(health);

                        // items
                        EntityUtil.addRandomItems(entity, (1 + 1 + numItems) / NUM_INVENTORIES, ItemContainer.PRIMARY);
                        EntityUtil.addRandomItems(entity, (1 + numItems) / NUM_INVENTORIES, ItemContainer.SECONDARY);
                        EntityUtil.addRandomItems(entity, numItems / NUM_INVENTORIES, ItemContainer.SHIP);

                        // Add entity
                        entity.getStream().addEntity(entity);
                        // update difficulty
                        updateDifficulty();
                }
        }

        /**
         * Update the difficulty based on the time
         */
        public void updateDifficulty() {
                int time = Engine.get().getGameState().getGameTime();
                nextSpawn = time < MAX_SPAWN_TIME * TICKS_INA_SECOND ? MAX_SPAWN_TIME * TICKS_INA_SECOND - time
                                : TICKS_INA_SECOND;

                spawnCap = 1 + (int) Math.sqrt(time);
                numItems = 1 + time / ENEMY_ITEMUP_TICK;
                maxLevel = 1 + time / ENEMY_LEVELUP_TICK;
        }
}
