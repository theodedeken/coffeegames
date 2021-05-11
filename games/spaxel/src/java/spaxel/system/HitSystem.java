package spaxel.system;

import spaxel.entity.Behaviour;
import spaxel.entity.ComponentType;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.system.SystemType;
import java.util.Set;

/**
 * The HitSystem is responsible for updating all the entities with a HitComponent
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
        Set<Entity> entities = Engine.get().getNEntityStream().getEntities(ComponentType.HIT);
        for (Entity entity : entities) {
            ((Behaviour) entity.getComponent(ComponentType.HIT)).execute(entity);
        }
    }


}
