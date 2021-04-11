package spaxel.factories.entities;

import spaxel.entity.ComponentType;
import spaxel.engine.Resources;
import spaxel.entity.Component;
import spaxel.entity.EntityType;
import spaxel.entity.Entity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Groups ComponentFactories together in an industry to build entities
 * 
 * Created by theo on 3/06/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",
        visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = RandomParticleIndustry.class, name = "RANDOM_PARTICLE"),
        @JsonSubTypes.Type(value = StaticParticleIndustry.class, name = "STATIC_PARTICLE"),
        @JsonSubTypes.Type(value = SpawnerIndustry.class, name = "SPAWNER"),
        @JsonSubTypes.Type(value = EnemyIndustry.class, name = "ENEMY"),
        @JsonSubTypes.Type(value = ProjectileIndustry.class, name = "PROJECTILE"),
        @JsonSubTypes.Type(value = ItemIndustry.class, name = "ITEM"),
        @JsonSubTypes.Type(value = PlayerIndustry.class, name = "PLAYER"),
        @JsonSubTypes.Type(value = EffectIndustry.class, name = "EFFECT"),
        @JsonSubTypes.Type(value = EffectIndustry.class, name = "VISUAL_EFFECT"),})
public class EntityIndustry {
    private EntityType type;
    private List<Component> blueprints;
    private List<String> links;

    /**
     * Creates a new EntityIndustry
     */
    public EntityIndustry() {
        super();
    }

    /**
     * Create a new entity with the components produced by the componentfactories
     * 
     * @return the created entity
     */
    public Entity produce() {
        Entity entity = new Entity(type);
        entity.setComponents(buildComponents());
        for (String link : links) {
            entity.addLink(Resources.get().getIndustryMap().get(link).produce());
        }
        return entity;
    }

    /**
     * Builds the componentmap using the factories of this industry
     * 
     * @return the created componentmap
     */
    public Map<ComponentType, Component> buildComponents() {
        EnumMap<ComponentType, Component> components = new EnumMap<>(ComponentType.class);
        for (Component blueprint : blueprints) {
            Component c = blueprint.copy();
            components.put(c.getType(), c);
        }
        return components;
    }

    public List<Component> getBlueprints() {
        return blueprints;
    }

    public void setBlueprints(List<Component> blueprints) {
        this.blueprints = blueprints;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    /**
     * @return the links
     */
    public List<String> getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(List<String> links) {
        this.links = links;
    }
}
