package spaxel.system;

import java.util.Set;
import spaxel.engine.Engine;
import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import voide.entity.Entity;

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
        Set<Entity> entities = Engine
            .get()
            .getNEntityStream()
            .getEntities(SpaxelComponent.MOUSE);
        for (Entity entity : entities) {
            ((Behaviour) entity.getComponent(SpaxelComponent.MOUSE)).execute(
                    entity
                );
        }
    }
}
