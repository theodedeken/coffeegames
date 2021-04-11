package spaxel.system;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.experience.ExperienceStorage;
import spaxel.entity.storage.health.HealthStorage;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.entity.EntityUtil;
import java.util.Set;

/**
 * The ExperienceSystem is responsible for updating all entities with an ExperienceComponent
 * 
 * Created by theo on 26/06/17.
 */
public class ExperienceSystem extends GameSystem {
    /**
     * Create a new ExperienceSystem
     */
    public ExperienceSystem() {
        super(SystemType.EXPERIENCE);
    }

    public void update() {
        Set<Entity> entities =
                Engine.get().getNEntityStream().getEntities(ComponentType.EXPERIENCE);
        for (Entity entity : entities) {
            ExperienceStorage ec =
                    (ExperienceStorage) entity.getComponent(ComponentType.EXPERIENCE);
            if (ec.ding()) {
                ec.levelUp();
                HealthStorage hc = (HealthStorage) entity.getComponent(ComponentType.HEALTH);
                int health = EntityUtil.healthAtLevel(ec.getLevel(), hc.getBaseHealth());
                hc.setMaxHealth(health);
                hc.setCurrentHealth(health);
            }

        }
    }
}
