package spaxel.system;

import java.util.Set;
import spaxel.engine.Engine;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.entity.Entity;

/**
 * The velocity system is responsible for updating the position and rotation of
 * the entities based on their current velocity
 *
 * Created by theo on 4/06/17.
 */
public class VelocitySystem extends GameSystem {

    /**
     * Creates a new VelocitySystem
     */
    public VelocitySystem() {
        super(SystemType.VELOCITY);
    }

    public void update() {
        Set<Entity> nEntities = Engine
            .get()
            .getEntityStream()
            .getEntities(SpaxelComponent.CHANGE);
        for (Entity ne : nEntities) {
            TransformationStorage pc = (TransformationStorage) ne.getComponent(
                SpaxelComponent.TRANSFORMATION
            );
            ChangeStorage vc = (ChangeStorage) ne.getComponent(
                SpaxelComponent.CHANGE
            );
            pc.setPosition(pc.getPosition().sum(vc.getPositionChange()));
            pc.setRotation(pc.getRotation() + vc.getRotationationChange());
        }
    }
}
