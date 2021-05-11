package spaxel.entity.behaviour.hit;

import spaxel.entity.Entity;
import spaxel.entity.behaviour.ai.HomingMissileAIHandler;
import spaxel.entity.behaviour.death.projectile.ClusterMissileDeathComponent;

public abstract class HitHandler {
    private HitType type;

    public HitHandler(HitType type) {
        this.type = type;
    }

    public static HitHandler createHandler(HitType type) {
        switch (type) {
        case BASIC_LASER:
            return new BasicLaserProjectile();
        case PIERCING_LASER:
            return new PiercingLaserProjectile();
        case SLOWING_LASER:
            return new SlowingLaserProjectile();
        case DISRUPT_LASER:
            return new DisruptLaserProjectile();
        case BASIC_MISSILE:
            return new BasicMissileProjectile();
        case CLUSTER_MISSILE:
            return new ClusterMissileProjectile();
        case HACKING_MISSILE:
            return new HackingMissileProjectile();
        case HOMING_MISSILE:
            return new HomingMissileProjectile();
        case EQUIP:
            return new EquipHandler();
        default:
            throw new RuntimeException("Hit type " + type + " does not have a handler.");
        }

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
