package spaxel.entity.behaviour.death;

import spaxel.entity.behaviour.death.actor.BasicEnemyDeathComponent;
import spaxel.entity.behaviour.death.actor.PlayerDeathComponent;
import spaxel.entity.behaviour.death.effect.DisableMoveAffectDeathComponent;
import spaxel.entity.behaviour.death.effect.DisableShootAffectDeathComponent;
import spaxel.entity.behaviour.death.projectile.ClusterMissileDeathComponent;
import voide.entity.Entity;

/**
 * Represents a behavior of an entity executed on its death
 *
 * Created by theo on 24/06/17.
 */
public abstract class DeathHandler {

    protected DeathType deathType;

    /**
     * Create a new DeathComponent of the specified type
     *
     * @param deathType the type of death
     */
    public DeathHandler(DeathType deathType) {
        this.deathType = deathType;
    }

    public static DeathHandler createDeathHandler(DeathType type) {
        switch (type) {
            case BASIC_ENEMY:
                return new BasicEnemyDeathComponent();
            case PLAYER:
                return new PlayerDeathComponent();
            case DISABLE_SHOOT_AFFECT:
                return new DisableShootAffectDeathComponent();
            case DISABLE_MOVE_AFFECT:
                return new DisableMoveAffectDeathComponent();
            case CLUSTER_MISSILE:
                return new ClusterMissileDeathComponent();
            default:
                throw new RuntimeException(
                    "Death handler of type " + type + " not implemented"
                );
        }
    }

    /**
     * Execute this method on the death of the entity
     *
     * @param entity the dieing entity
     */
    public abstract void die(Entity entity);
}
