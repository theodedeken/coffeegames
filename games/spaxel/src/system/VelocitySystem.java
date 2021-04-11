package spaxel.system;

import spaxel.entity.ComponentType;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import spaxel.system.SystemType;
import java.util.Set;

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
        Set<Entity> nEntities = Engine.get().getNEntityStream().getEntities(ComponentType.CHANGE);
        for (Entity ne : nEntities) {
            TransformationStorage pc = (TransformationStorage) ne.getComponent(ComponentType.TRANSFORMATION);
            ChangeStorage vc = (ChangeStorage) ne.getComponent(ComponentType.CHANGE);
            pc.setPosition(pc.getPosition().sum(vc.getPositionChange()));
            pc.setRotation(pc.getRotation() + vc.getRotationationChange());
        }
    }
}
