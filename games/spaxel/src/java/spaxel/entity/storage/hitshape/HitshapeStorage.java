package spaxel.entity.storage.hitshape;

import spaxel.entity.SpaxelComponent;
import voide.collision.HitShape;
import voide.entity.Storage;
import voide.resources.ResourceProxy;

/**
 * Represent a collidable for an entity
 *
 * Created by theo on 3/06/17.
 */
public class HitshapeStorage extends Storage {

    private ResourceProxy<HitShape> hitShape;

    public HitshapeStorage() {
        super(SpaxelComponent.HITSHAPE);
    }

    /**
     * Create a new HitshapeStorage with the specified hitShape
     *
     * @param hitShape the hitshape of the component
     */
    public HitshapeStorage(ResourceProxy<HitShape> hitShape) {
        super(SpaxelComponent.HITSHAPE);
        this.hitShape = hitShape;
    }

    public HitShape getHitShape() {
        return hitShape.get();
    }

    public void setHitShape(String hitShape) {
        this.hitShape = new ResourceProxy<>(hitShape, HitShape.class);
    }

    public HitshapeStorage copy() {
        return new HitshapeStorage(hitShape.copy());
    }
}
