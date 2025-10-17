package spaxel.entity.industry;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Mixin to allow for subtype serialization
 *
 * Created by theo on 3/06/17.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes(
    {
        @JsonSubTypes.Type(
            value = RandomParticleIndustry.class,
            name = "RANDOM_PARTICLE"
        ),
        @JsonSubTypes.Type(
            value = StaticParticleIndustry.class,
            name = "STATIC_PARTICLE"
        ),
        @JsonSubTypes.Type(value = SpawnerIndustry.class, name = "SPAWNER"),
        @JsonSubTypes.Type(value = EnemyIndustry.class, name = "ENEMY"),
        @JsonSubTypes.Type(
            value = ProjectileIndustry.class,
            name = "PROJECTILE"
        ),
        @JsonSubTypes.Type(value = ItemIndustry.class, name = "ITEM"),
        @JsonSubTypes.Type(value = PlayerIndustry.class, name = "PLAYER"),
        @JsonSubTypes.Type(value = EffectIndustry.class, name = "EFFECT"),
        @JsonSubTypes.Type(value = EffectIndustry.class, name = "VISUAL_EFFECT")
    }
)
public class EntityIndustryMixin {}
