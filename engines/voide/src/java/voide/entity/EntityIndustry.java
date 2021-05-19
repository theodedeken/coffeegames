package voide.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import voide.debug.RepresentationBuilder;
import voide.resources.Resource;
import voide.resources.Resources;

/**
 * Groups ComponentFactories together in an industry to build entities
 *
 * Created by theo on 3/06/17.
 */
public class EntityIndustry implements Resource {

    private EntityType type;
    private List<Component> blueprints;
    private List<String> links;

    /**
     * Creates a new EntityIndustry
     */
    public EntityIndustry() {
        super();
    }

    public void initialize() {}

    public String repr() {
        return String.format(
            "EntityIndustry { %s, %d blueprints, %d links }",
            type.id(),
            blueprints.size(),
            links.size()
        );
    }

    public String fullRepr() {
        return new RepresentationBuilder(getClass().getName())
            .field("type", type)
            .field("blueprints", blueprints)
            .field("links", links)
            .build();
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
            entity.addLink(
                Resources
                    .get()
                    .getResource(link, EntityIndustry.class)
                    .produce()
            );
        }
        return entity;
    }

    /**
     * Builds the componentmap using the factories of this industry
     *
     * @return the created componentmap
     */
    public Map<String, Component> buildComponents() {
        Map<String, Component> components = new HashMap<>();
        for (Component blueprint : blueprints) {
            Component c = blueprint.copy();
            components.put(c.getType().id(), c);
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
