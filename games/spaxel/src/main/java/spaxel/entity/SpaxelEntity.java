package spaxel.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import voide.entity.EntityType;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "root",
    defaultImpl = SpaxelEntity.class,
    visible = false
)
public enum SpaxelEntity implements EntityType {
    PLAYER,
    ENEMY,
    PROJECTILE,
    RANDOM_PARTICLE,
    SPAWNER,
    STATIC_PARTICLE,
    ITEM,
    EFFECT,
    VISUAL_EFFECT;

    public String id() {
        return name();
    }
}
