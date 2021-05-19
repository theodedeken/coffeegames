package spaxel.system;

import java.util.Set;
import spaxel.engine.Engine;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.damage.Damage;
import spaxel.entity.storage.damage.DamageStorage;
import spaxel.entity.storage.health.HealthStorage;
import voide.entity.Entity;

/**
 * The DamageSystem is responsible for updating all entities with a
 * DamageComponent
 *
 * Created by theo on 8/06/17.
 */
public class DamageSystem extends GameSystem {

    /**
     * Create a new DamageSystem
     */
    public DamageSystem() {
        super(SystemType.DAMAGE);
    }

    public void update() {
        Set<Entity> entities = Engine
            .get()
            .getNEntityStream()
            .getEntities(SpaxelComponent.DAMAGE);
        for (Entity e : entities) {
            DamageStorage dc = (DamageStorage) e.getComponent(
                SpaxelComponent.DAMAGE
            );
            HealthStorage hc = (HealthStorage) e.getComponent(
                SpaxelComponent.HEALTH
            );
            for (Damage d : dc.getDamages()) {
                hc.setCurrentHealth(hc.getCurrentHealth() - d.getDamage());
            }
            dc.getDamages().clear();
        }
    }
}
