package spaxel.system;

import java.util.Set;
import spaxel.engine.Engine;
import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.health.HealthStorage;
import voide.entity.Entity;

/**
 * The HealthSystem is responsible for updating all entities with a
 * HealthComponent
 *
 * Created by theo on 8/06/17.
 */
public class HealthSystem extends GameSystem {

    /**
     * Create a new HealthSystem
     */
    public HealthSystem() {
        super(SystemType.HEALTH);
    }

    public void update() {
        Set<Entity> entities = Engine
            .get()
            .getEntityStream()
            .getEntities(SpaxelComponent.HEALTH);
        for (Entity e : entities) {
            if (
                (
                    (HealthStorage) e.getComponent(SpaxelComponent.HEALTH)
                ).getCurrentHealth() <
                0
            ) {
                Behaviour dc = (Behaviour) e.getComponent(
                    SpaxelComponent.DEATH
                );
                if (dc != null) {
                    dc.execute(e);
                }
                e.destroy();
            }
        }
    }
}
