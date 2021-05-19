package spaxel.system;

import java.util.Set;
import spaxel.engine.Engine;
import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.age.AgeStorage;
import voide.entity.Entity;

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
        Set<Entity> nEntities = Engine
            .get()
            .getEntityStream()
            .getEntities(SpaxelComponent.AGE);
        for (Entity ne : nEntities) {
            AgeStorage ac = (AgeStorage) ne.getComponent(SpaxelComponent.AGE);
            if (ac.getLife() != 0) {
                ac.setLife(ac.getLife() - 1);
            } else {
                Behaviour dc = (Behaviour) ne.getComponent(
                    SpaxelComponent.DEATH
                );
                if (dc != null) {
                    dc.execute(ne);
                }
                ne.destroy();
            }
        }
    }
}
