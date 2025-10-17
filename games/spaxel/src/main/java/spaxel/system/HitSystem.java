package spaxel.system;

import java.util.Set;
import spaxel.engine.Engine;
import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import voide.entity.Entity;

/**
 * The HitSystem is responsible for updating all the entities with a
 * HitComponent
 *
 * Created by theo on 20/06/17.
 */
public class HitSystem extends GameSystem {

    /**
     * Create a new HitSystem
     */
    public HitSystem() {
        super(SystemType.HIT);
    }

    public void update() {
        Set<Entity> entities = Engine
            .get()
            .getEntityStream()
            .getEntities(SpaxelComponent.HIT);
        for (Entity entity : entities) {
            ((Behaviour) entity.getComponent(SpaxelComponent.HIT)).execute(
                    entity
                );
        }
    }
}
