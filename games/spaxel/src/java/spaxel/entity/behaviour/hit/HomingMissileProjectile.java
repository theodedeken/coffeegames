package spaxel.entity.behaviour.hit;

import spaxel.entity.storage.projectile.ProjectileStorage;
import voide.entity.Entity;

/**
 * Created by theo on 8/07/17.
 */
public class HomingMissileProjectile extends ProjectileHandler {
    // TODO private static final int PARTICLE_SIZE = 4;

    public HomingMissileProjectile() {
        super(HitType.HOMING_MISSILE);
    }

    public void payload(Entity entity, Entity victim, ProjectileStorage projStore) {
        entity.destroy();
    }
}
