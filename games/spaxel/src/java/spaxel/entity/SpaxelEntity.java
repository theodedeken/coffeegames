package spaxel.entity;

import voide.entity.EntityType;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "root", defaultImpl = SpaxelEntity.class, visible = false)
public enum SpaxelEntity implements EntityType {
    PLAYER, ENEMY, PROJECTILE, RANDOM_PARTICLE, SPAWNER, STATIC_PARTICLE, ITEM, EFFECT, VISUAL_EFFECT;

    public String id() {
        return name();
    }

}
