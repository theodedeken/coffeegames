package spaxel.entity.behaviour.hit;

import spaxel.entity.storage.projectile.ProjectileStorage;
import spaxel.entity.Entity;

/**
 * Created by theo on 8/07/17.
 */
public class HackingMissileProjectile extends ProjectileHandler {
    // TODO private static final int PARTICLE_SIZE = 4;

    public HackingMissileProjectile() {
        super(HitType.HACKING_MISSILE);
    }

    public void payload(Entity entity, Entity victim, ProjectileStorage projStore) {
        addEffect(victim, projStore.getOnHitEffect());

        entity.destroy();
    }
}
