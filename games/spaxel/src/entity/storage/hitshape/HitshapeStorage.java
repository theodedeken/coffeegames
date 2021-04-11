package spaxel.entity.storage.hitshape;

import spaxel.collision.HitShape;
import spaxel.entity.Storage;
import spaxel.entity.ComponentType;
import spaxel.engine.Resources;

/**
 * Represent a collidable for an entity
 * 
 * Created by theo on 3/06/17.
 */
public class HitshapeStorage extends Storage {
    private HitShape hitShape;

    public HitshapeStorage() {
        super(ComponentType.HITSHAPE);
    }

    /**
     * Create a new HitshapeStorage with the specified hitShape
     * 
     * @param hitShape the hitshape of the component
     */
    public HitshapeStorage(HitShape hitShape) {
        super(ComponentType.HITSHAPE);
        this.hitShape = hitShape;
    }

    public HitShape getHitShape() {
        return hitShape;
    }

    public void setHitShape(HitShape hitShape) {
        this.hitShape = hitShape;
    }

    public void setHitShape(String hitShape) {
        this.hitShape = Resources.get().getHitShapeAtlas().get(hitShape);
    }

    public HitshapeStorage copy() {
        return new HitshapeStorage(hitShape);
    }
}
