package spaxel.system;

import java.util.Set;
import spaxel.engine.Engine;
import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import voide.entity.Entity;

/**
 * The KeyboardSystem is responsible for updating the entities with a
 * KeyboardBehaviour
 *
 * Created by theo on 21/06/17.
 */
public class KeyboardSystem extends GameSystem {

    /**
     * Create a new InputSystem
     */
    public KeyboardSystem() {
        super(SystemType.KEYBOARD);
    }

    public void update() {
        Set<Entity> entities = Engine
            .get()
            .getNEntityStream()
            .getEntities(SpaxelComponent.KEYBOARD);
        for (Entity entity : entities) {
            ((Behaviour) entity.getComponent(SpaxelComponent.KEYBOARD)).execute(
                    entity
                );
        }
    }
}
