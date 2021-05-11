package spaxel.system;

import spaxel.entity.Behaviour;
import spaxel.entity.ComponentType;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.system.SystemType;
import java.util.Set;

/**
 * The MouseSystem is responsible for updating the entities with a
 * MouseBehaviour
 * 
 * Created by theo on 21/06/17.
 */
public class MouseSystem extends GameSystem {
    /**
     * Create a new InputSystem
     */
    public MouseSystem() {
        super(SystemType.MOUSE);
    }

    public void update() {
        Set<Entity> entities = Engine.get().getNEntityStream().getEntities(ComponentType.MOUSE);
        for (Entity entity : entities) {
            ((Behaviour) entity.getComponent(ComponentType.MOUSE)).execute(entity);
        }
    }
}
