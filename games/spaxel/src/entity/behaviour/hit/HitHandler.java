package spaxel.entity.behaviour.hit;

import spaxel.entity.Entity;

public abstract class HitHandler {
    private HitType type;

    public HitHandler(HitType type) {
        this.type = type;
    }

    public abstract void hit(Entity entity, Entity victim);

    /**
     * @return the type
     */
    public HitType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(HitType type) {
        this.type = type;
    }


}
