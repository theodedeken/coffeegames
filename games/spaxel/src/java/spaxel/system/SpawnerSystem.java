package spaxel.system;

import java.util.Set;
import spaxel.entity.Behaviour;
import spaxel.entity.ComponentType;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.entity.EntityStream;

/**
 * The SpawnerSystem is responsible for updating the entities with
 * SpawnerComponents
 * 
 * Created by theo on 14-6-2016.
 */
public class SpawnerSystem extends GameSystem {
    /**
     * Create a new SpawnerSystem
     */
    public SpawnerSystem() {
        super(SystemType.SPAWNER);
    }

    public void update() {
        // update all spawners and acquire particles
        EntityStream nentities = Engine.get().getNEntityStream();
        Set<Entity> spawners = nentities.getEntities(ComponentType.SPAWN);
        for (Entity ne : spawners) {
            ((Behaviour) ne.getComponent(ComponentType.SPAWN)).execute(ne);
        }
    }

}
