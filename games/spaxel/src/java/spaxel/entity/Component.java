package spaxel.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import spaxel.entity.storage.status.StatusStorage;
import spaxel.entity.behaviour.affect.AffectBehaviour;
import spaxel.entity.behaviour.ai.AIBehaviour;
import spaxel.entity.behaviour.keyboard.KeyboardBehaviour;
import spaxel.entity.behaviour.mouse.MouseBehaviour;
import spaxel.entity.behaviour.spawn.SpawnBehaviour;
import spaxel.entity.storage.damage.DamageStorage;
import spaxel.entity.storage.event.EventStorage;
import spaxel.entity.behaviour.death.DeathBehaviour;
import spaxel.entity.behaviour.event.EventBehaviour;
import spaxel.entity.storage.experience.ExperienceStorage;
import spaxel.entity.storage.health.HealthStorage;
import spaxel.entity.behaviour.hit.HitBehaviour;
import spaxel.entity.storage.move.MoveStorage;
import spaxel.entity.storage.projectile.ProjectileStorage;
import spaxel.entity.storage.render.RenderStorage;
import spaxel.entity.storage.renderable.RenderableStorage;
import spaxel.entity.storage.shield.ShieldStorage;
import spaxel.entity.storage.shoot.ShootStorage;
import spaxel.entity.storage.spawn.SpawnStorage;
import spaxel.entity.storage.transformation.TransformationStorage;
import spaxel.entity.behaviour.render.RenderBehaviour;
import spaxel.entity.storage.stack.StackStorage;
import spaxel.entity.storage.affect.AffectStorage;
import spaxel.entity.storage.age.AgeStorage;
import spaxel.entity.storage.change.ChangeStorage;
import spaxel.entity.storage.cooldown.CooldownStorage;
import spaxel.entity.storage.hitshape.HitshapeStorage;
import spaxel.entity.storage.item.ItemStorage;

/**
 * Created by theo on 31/05/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",
        visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = HitBehaviour.class, name = "HIT"),
        @JsonSubTypes.Type(value = SpawnBehaviour.class, name = "SPAWN"),
        @JsonSubTypes.Type(value = MouseBehaviour.class, name = "MOUSE"),
        @JsonSubTypes.Type(value = KeyboardBehaviour.class, name = "KEYBOARD"),
        @JsonSubTypes.Type(value = DeathBehaviour.class, name = "DEATH"),
        @JsonSubTypes.Type(value = EventBehaviour.class, name = "EVENT"),
        @JsonSubTypes.Type(value = AffectBehaviour.class, name = "AFFECT"),
        @JsonSubTypes.Type(value = RenderBehaviour.class, name = "RENDER"),
        @JsonSubTypes.Type(value = AIBehaviour.class, name = "AI"),

        @JsonSubTypes.Type(value = SpawnStorage.class, name = "SPAWN_STORE"),
        @JsonSubTypes.Type(value = AgeStorage.class, name = "AGE"),
        @JsonSubTypes.Type(value = ChangeStorage.class, name = "CHANGE"),
        @JsonSubTypes.Type(value = HitshapeStorage.class, name = "HITSHAPE"),
        @JsonSubTypes.Type(value = CooldownStorage.class, name = "COOLDOWN"),
        @JsonSubTypes.Type(value = TransformationStorage.class, name = "TRANSFORMATION"),
        @JsonSubTypes.Type(value = RenderableStorage.class, name = "RENDERABLE"),
        @JsonSubTypes.Type(value = MoveStorage.class, name = "MOVE"),
        @JsonSubTypes.Type(value = StatusStorage.class, name = "STATUS"),
        @JsonSubTypes.Type(value = DamageStorage.class, name = "DAMAGE"),
        @JsonSubTypes.Type(value = StackStorage.class, name = "STACK"),
        @JsonSubTypes.Type(value = HealthStorage.class, name = "HEALTH"),
        @JsonSubTypes.Type(value = ExperienceStorage.class, name = "EXPERIENCE"),
        @JsonSubTypes.Type(value = ItemStorage.class, name = "ITEM"),
        @JsonSubTypes.Type(value = ShootStorage.class, name = "SHOOT"),
        @JsonSubTypes.Type(value = ShieldStorage.class, name = "SHIELD"),
        @JsonSubTypes.Type(value = EventStorage.class, name = "EVENT_STORE"),
        @JsonSubTypes.Type(value = AffectStorage.class, name = "AFFECT_STORE"),
        @JsonSubTypes.Type(value = RenderStorage.class, name = "RENDER_STORE"),
        @JsonSubTypes.Type(value = ProjectileStorage.class, name = "PROJECTILE"),



})
public abstract class Component {
    private ComponentType type;

    /**
     * Create a new component of the specified type
     * 
     * @param type the type of the component
     */
    public Component(ComponentType type) {
        this.type = type;
    }

    /**
     * Return a copy of this component.
     * 
     * @return The copied component
     */
    public abstract Component copy();

    public ComponentType getType() {
        return type;
    }
}
