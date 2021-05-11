package spaxel.system;

import spaxel.entity.Behaviour;
import spaxel.entity.ComponentType;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.system.SystemType;
import java.util.Set;

/**
 * The KeyboardSystem is responsible for updating the entities with a KeyboardBehaviour
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
        Set<Entity> entities = Engine.get().getNEntityStream().getEntities(ComponentType.KEYBOARD);
        for (Entity entity : entities) {
            ((Behaviour) entity.getComponent(ComponentType.KEYBOARD)).execute(entity);
        }
    }
}
