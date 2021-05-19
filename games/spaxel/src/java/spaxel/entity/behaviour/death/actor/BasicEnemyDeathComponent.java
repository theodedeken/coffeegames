package spaxel.entity.behaviour.death.actor;

import spaxel.engine.Engine;
import spaxel.entity.EntityUtil;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.behaviour.ai.AIBehaviour;
import spaxel.entity.behaviour.ai.DroppedItemAIHandler;
import spaxel.entity.behaviour.death.DeathHandler;
import spaxel.entity.behaviour.death.DeathType;
import spaxel.entity.behaviour.hit.EquipHandler;
import spaxel.entity.behaviour.hit.HitBehaviour;
import spaxel.entity.industry.SpawnerIndustry;
import spaxel.entity.item.ItemCatalogue;
import spaxel.entity.storage.age.AgeStorage;
import spaxel.entity.storage.experience.ExperienceStorage;
import spaxel.entity.storage.item.ItemName;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;
import voide.graphics.renderable.Texture;
import voide.random.RandomTexture;
import voide.random.VoideRandom;

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

    private VoideRandom random;

    /**
     * Create a new BasicEnemyDeathComponent
     */
    public BasicEnemyDeathComponent() {
        super(DeathType.BASIC_ENEMY);
        random = new VoideRandom();
    }

    public void die(Entity entity) {
        SpawnerIndustry hpsi = voide.resources.Resources
            .get()
            .getResource(
                "enemy_death_particle_spawner_industry",
                SpawnerIndustry.class
            );

        RenderableStorage rndrStore = (RenderableStorage) entity.getComponent(
            SpaxelComponent.RENDERABLE
        );
        TransformationStorage trnsStore = (TransformationStorage) entity.getComponent(
            SpaxelComponent.TRANSFORMATION
        );

        RenderableStorage particle = new RenderableStorage(
            RandomTexture.getRandomPart(
                (Texture) rndrStore.getRenderable(),
                DEATH_PARTICLE_SIZE,
                DEATH_PARTICLE_SIZE
            )
        );
        // add particle effect
        entity.getStream().addEntity(hpsi.produce(trnsStore, particle));

        Engine.get().getGameState().addScore(BASIC_ENEMY_SCORE);
        // add experience
        Entity player = EntityUtil.getPlayer(entity.getStream());
        ExperienceStorage exp = (ExperienceStorage) player.getComponent(
            SpaxelComponent.EXPERIENCE
        );
        exp.addXp(BASIC_ENEMY_XP_GAIN);
        // chance of dropping item
        if (random.pass(BASIC_ENEMY_DROPRATE)) {
            Entity item = voide.resources.Resources
                .get()
                .getResource("spaxel.item_catalogue", ItemCatalogue.class)
                .produceRandom(
                    prop ->
                        EntityUtil
                            .getAllItemNames(entity)
                            .contains(ItemName.valueOf(prop.getName()))
                );
            item.addComponent(new HitBehaviour(new EquipHandler()));
            item.addComponent(new AgeStorage(ITEM_LIFETIME, ITEM_LIFETIME));
            item.addComponent(trnsStore.copy());
            item.addComponent(entity.getComponent(SpaxelComponent.CHANGE));
            item.addComponent(entity.getComponent(SpaxelComponent.RENDER));
            item.addComponent(new AIBehaviour(new DroppedItemAIHandler()));
            entity.getStream().addEntity(item);
        }
    }
}
