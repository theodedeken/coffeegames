package spaxel.entity.behaviour.hit;

import spaxel.entity.storage.projectile.ProjectileStorage;
import spaxel.entity.Entity;

/**
 * Created by theo on 18/06/17.
 */
public class BasicLaserProjectile extends ProjectileHandler {
    // TODOprivate static final int PARTICLE_SIZE = 3;

    public BasicLaserProjectile() {
        super(HitType.BASIC_LASER);
    }

    public void payload(Entity entity, Entity victim, ProjectileStorage projStore) {
        entity.destroy();
    }


}
