package spaxel.entity.behaviour.hit;

import java.util.Set;
import voide.collision.HitShape;
import spaxel.entity.Behaviour;
import spaxel.entity.ComponentType;
import spaxel.entity.storage.hitshape.HitshapeStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.engine.Engine;
import spaxel.entity.Entity;
import voide.math.MatrixD;
import spaxel.util.MatrixUtil;

/**
 * Created by theo on 18/06/17.
 */
public class HitBehaviour extends Behaviour {
    private HitHandler handler;

    public HitBehaviour() {
        super(ComponentType.HIT);
    }

    public HitBehaviour(HitHandler handler) {
        super(ComponentType.HIT);
        this.handler = handler;
    }

    public void execute(Entity entity) {
        Set<Entity> colliders = entity.getStream().getEntities(ComponentType.HITSHAPE);
        // Entity parent = entity.getParent();
        HitshapeStorage cc = (HitshapeStorage) entity.getComponent(ComponentType.HITSHAPE);
        TransformationStorage pc = (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION);
        MatrixD eTransform = MatrixUtil.getTransRotationMatrix(pc.getPosition(), pc.getRotation());
        HitShape updated = cc.getHitShape().update(eTransform);
        for (Entity collider : colliders) {
            // if (collider != parent) {
            HitshapeStorage ccc = (HitshapeStorage) collider.getComponent(ComponentType.HITSHAPE);
            TransformationStorage cpc = (TransformationStorage) collider.getComponent(ComponentType.TRANSFORMATION);
            MatrixD cTransform = MatrixUtil.getTransRotationMatrix(cpc.getPosition(), cpc.getRotation());
            if (ccc.getHitShape().update(cTransform).collision(updated)) {
                handler.hit(entity, collider);
            }
            // }
        }
    }

    public HitBehaviour copy() {
        return new HitBehaviour(handler);
    }

    public void setHandler(HitType type) {
        this.handler = HitHandler.createHandler(type);
    }
}
