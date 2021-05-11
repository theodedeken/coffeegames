package spaxel.system;

import spaxel.entity.storage.age.AgeStorage;
import spaxel.entity.Behaviour;
import spaxel.entity.ComponentType;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.system.SystemType;
import java.util.Set;

/**
 * The AgeSystem is responsible for updating all entities with an AgeComponent
 * 
 * Created by theo on 4/06/17.
 */
public class AgeSystem extends GameSystem {
    /**
     * Create a new AgeSystem
     */
    public AgeSystem() {
        super(SystemType.AGE);
    }

    public void update() {
        Set<Entity> nEntities = Engine.get().getNEntityStream().getEntities(ComponentType.AGE);
        for (Entity ne : nEntities) {
            AgeStorage ac = (AgeStorage) ne.getComponent(ComponentType.AGE);
            if (ac.getLife() != 0) {
                ac.setLife(ac.getLife() - 1);
            } else {
                Behaviour dc = (Behaviour) ne.getComponent(ComponentType.DEATH);
                if (dc != null) {
                    dc.execute(ne);
                }
                ne.destroy();
            }
        }
    }
}
