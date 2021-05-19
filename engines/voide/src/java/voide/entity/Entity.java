package voide.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represent an entity in the game
 *
 * Created by theo on 31/05/17.
 */
public class Entity {

    private EntityType type;
    private Map<String, Component> components;
    private Entity parent;
    private Set<Entity> links;
    private EntityStream stream;

    /**
     * Create a new entity of the given type
     *
     * @param type the type of the entity
     */
    public Entity(EntityType type) {
        this.type = type;
        this.components = new HashMap<>();
        this.links = new HashSet<>();
    }

    public EntityType getType() {
        return type;
    }

    public Map<String, Component> getComponents() {
        return components;
    }

    public boolean hasComponent(ComponentType type) {
        return components.containsKey(type.id());
    }

    public void setComponents(Map<String, Component> components) {
        this.components = components;
    }

    /**
     * Get the component of the given type from this entity
     *
     * @param type the type of the component to get
     *
     * @return the component
     */
    public Component getComponent(ComponentType type) {
        Component result = components.get(type.id());
        if (parent == null || result != null) {
            return result;
        } else {
            return result;
            // currently buggy with effects FIXME
            // return parent.getComponent(type);
        }
    }

    /**
     * Add a component to this entity
     *
     * @param component the component to add
     */
    public void addComponent(Component component) {
        components.put(component.getType().id(), component);
        stream.addComponent(component.getType(), this);
    }

    /**
     * Remove a component of the given type from this entity
     *
     * @param type the type of component to remove
     */
    public void removeComponent(ComponentType type) {
        components.remove(type.id());
        stream.removeComponent(type, this);
    }

    public Entity getParent() {
        return parent;
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public void addLink(Entity link) {
        links.add(link);
        link.setParent(this);
    }

    public void removeLink(Entity link) {
        links.remove(link);
    }

    public Set<Entity> getLinks(EntityFilter... filters) {
        Set<Entity> result = new HashSet<>();
        for (Entity link : links) {
            boolean passed = true;
            for (EntityFilter filter : filters) {
                if (!filter.pass(link)) {
                    passed = false;
                    break;
                }
            }
            if (passed) {
                result.add(link);
            }
        }
        return result;
    }

    public interface EntityFilter {
        public boolean pass(Entity entity);
    }

    public void destroy() {
        destroy(true);
    }

    private void destroy(boolean root) {
        /**
         * TODO find another way if (components.containsKey(ComponentType.DEATH)) {
         * ((Behaviour) components.get(ComponentType.DEATH)).execute(this); }
         */
        stream.removeEntity(this);
        for (Entity link : links) {
            link.destroy(false);
        }
        if (root && parent != null) {
            parent.removeLink(this);
        }
    }

    public void setStream(EntityStream stream) {
        this.stream = stream;
    }

    public EntityStream getStream() {
        return stream;
    }
}
