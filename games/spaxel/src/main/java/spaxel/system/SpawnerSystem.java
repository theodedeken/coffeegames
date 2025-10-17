package spaxel.system;

import java.util.Set;
import spaxel.engine.Engine;
import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import voide.entity.Entity;
import voide.entity.EntityStream;

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
        EntityStream nentities = Engine.get().getEntityStream();
        Set<Entity> spawners = nentities.getEntities(SpaxelComponent.SPAWN);
        for (Entity ne : spawners) {
            ((Behaviour) ne.getComponent(SpaxelComponent.SPAWN)).execute(ne);
        }
    }
}
