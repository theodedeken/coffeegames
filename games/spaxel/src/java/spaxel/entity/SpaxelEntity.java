package spaxel.entity;

import voide.entity.EntityType;

public enum SpaxelEntity implements EntityType {
    PLAYER, ENEMY, PROJECTILE, RANDOM_PARTICLE, SPAWNER, STATIC_PARTICLE, ITEM, EFFECT, VISUAL_EFFECT;

    public String id() {
        return name();
    }

}
