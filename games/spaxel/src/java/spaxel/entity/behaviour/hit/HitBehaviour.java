package spaxel.entity.behaviour.hit;

import java.util.Set;
import voide.collision.HitShape;
import voide.entity.Entity;
import spaxel.entity.Behaviour;
import spaxel.entity.SpaxelComponent;
import spaxel.entity.storage.hitshape.HitshapeStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import voide.math.MatrixD;
import voide.math.MatrixMaker;

/**
 * Created by theo on 18/06/17.
 */
public class HitBehaviour extends Behaviour {
    private HitHandler handler;

    public HitBehaviour() {
        super(SpaxelComponent.HIT);
    }

    public HitBehaviour(HitHandler handler) {
        super(SpaxelComponent.HIT);
        this.handler = handler;
    }

    public void execute(Entity entity) {
        Set<Entity> colliders = entity.getStream().getEntities(SpaxelComponent.HITSHAPE);
        // Entity parent = entity.getParent();
        HitshapeStorage cc = (HitshapeStorage) entity.getComponent(SpaxelComponent.HITSHAPE);
        TransformationStorage pc = (TransformationStorage) entity.getComponent(SpaxelComponent.TRANSFORMATION);
        MatrixD eTransform = MatrixMaker.getTransRotationMatrix(pc.getPosition(), pc.getRotation());
        HitShape updated = cc.getHitShape().update(eTransform);
        for (Entity collider : colliders) {
            // if (collider != parent) {
            HitshapeStorage ccc = (HitshapeStorage) collider.getComponent(SpaxelComponent.HITSHAPE);
            TransformationStorage cpc = (TransformationStorage) collider.getComponent(SpaxelComponent.TRANSFORMATION);
            MatrixD cTransform = MatrixMaker.getTransRotationMatrix(cpc.getPosition(), cpc.getRotation());
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
