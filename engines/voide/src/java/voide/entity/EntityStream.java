package voide.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Collection of all entities while playing the game
 * 
 * Created by theo on 31/05/17.
 */
public class EntityStream {
    private Map<String, Set<Entity>> entityTypeMap;
    private Map<String, Set<Entity>> toAddEntityTypeMap;
    private Map<String, Set<Entity>> toRemoveEntityTypeMap;
    private Map<String, Set<Entity>> componentTypeMap;
    private Map<String, Set<Entity>> toAddComponentTypeMap;
    private Map<String, Set<Entity>> toRemoveComponentTypeMap;
    private boolean clear;

    /**
     * Create a new EntityStream
     */
    public EntityStream() {
        entityTypeMap = new HashMap<>();
        toAddEntityTypeMap = new HashMap<>();
        toRemoveEntityTypeMap = new HashMap<>();

        componentTypeMap = new HashMap<>();
        toAddComponentTypeMap = new HashMap<>();
        toRemoveComponentTypeMap = new HashMap<>();
    }

    /**
     * Get a set of entities of the given type
     * 
     * @param type the entity type
     * 
     * @return the set of entities
     */
    public Set<Entity> getEntities(EntityType type) {
        return entityTypeMap.get(type.id());
    }

    /**
     * Get a copied set of entities that contain the given type
     * 
     * @param type the component type
     * 
     * @return the set of entities
     */
    public Set<Entity> getEntitiesCopy(ComponentType type) {
        synchronized (componentTypeMap) {
            return new HashSet<>(componentTypeMap.get(type.id()));
        }
    }

    /**
     * Get a set of entities that contain the given type
     * 
     * @param type the component type
     * 
     * @return the set of entities
     */
    public Set<Entity> getEntities(ComponentType type) {
        return componentTypeMap.get(type.id());
    }

    /**
     * Add an entity to the stream
     * 
     * @param entity the entity to add
     */
    public void addEntity(Entity entity) {
        for (Entity link : entity.getLinks()) {
            addEntity(link);
        }
        entity.setStream(this);
        toAddEntityTypeMap.get(entity.getType().id()).add(entity);
    }

    /**
     * Add a list of entities to the stream
     * 
     * @param type     the type of the entities
     * @param entities the entities to add
     */
    public void addEntities(Iterable<Entity> entities) {
        for (Entity entity : entities) {
            addEntity(entity);
        }
    }

    /**
     * Remove an Entity from the stream
     * 
     * @param entity the entity to remove
     */
    public void removeEntity(Entity entity) {
        toRemoveEntityTypeMap.get(entity.getType().id()).add(entity);
    }

    /**
     * Add a component to an entity
     * 
     * @param type   the type of the component
     * @param entity the entity
     */
    public void addComponent(ComponentType type, Entity entity) {
        Set<Entity> value = toAddComponentTypeMap.getOrDefault(type.id(), new HashSet<>());
        value.add(entity);
        toAddComponentTypeMap.putIfAbsent(type.id(), value);
    }

    /**
     * Remove a component from an entity
     * 
     * @param type   the type of the component
     * @param entity the entity
     */
    public void removeComponent(ComponentType type, Entity entity) {
        Set<Entity> value = toRemoveComponentTypeMap.getOrDefault(type.id(), new HashSet<>());
        value.add(entity);
        toRemoveComponentTypeMap.putIfAbsent(type.id(), value);
    }

    /**
     * Cleanup the pending additions and removal of entities and components
     */
    public void cleanup() {
        synchronized (componentTypeMap) {
            if (!clear) {
                // add entities
                addEntities();

                // remove entities
                removeEntities();

                // add and remove components
                for (Entry<String, Set<Entity>> entry : toAddComponentTypeMap.entrySet()) {
                    Set<Entity> added = entry.getValue();
                    Set<Entity> entities = componentTypeMap.getOrDefault(entry.getKey(), new HashSet<>());
                    entities.addAll(added);
                    componentTypeMap.putIfAbsent(entry.getKey(), entities);
                }
                toAddComponentTypeMap.clear();
                for (Entry<String, Set<Entity>> entry : toRemoveComponentTypeMap.entrySet()) {
                    Set<Entity> removed = entry.getValue();
                    Set<Entity> entities = componentTypeMap.getOrDefault(entry.getKey(), new HashSet<>());
                    entities.removeAll(removed);
                    componentTypeMap.putIfAbsent(entry.getKey(), entities);
                }
                toRemoveComponentTypeMap.clear();
            } else {
                clearEntities();
                clear = false;
            }
        }
    }

    private void addEntities() {
        for (Entry<String, Set<Entity>> entry : toAddEntityTypeMap.entrySet()) {
            Set<Entity> added = entry.getValue();
            Set<Entity> entities = entityTypeMap.getOrDefault(entry.getKey(), new HashSet<>());
            entities.addAll(added);
            for (Entity e : added) {
                for (Component c : e.getComponents().values()) {
                    toAddComponentTypeMap.get(c.getType().id()).add(e);
                }
            }
        }
        toAddEntityTypeMap.clear();
    }

    private void removeEntities() {
        for (Entry<String, Set<Entity>> entry : toAddEntityTypeMap.entrySet()) {
            Set<Entity> removed = entry.getValue();
            Set<Entity> entities = entityTypeMap.getOrDefault(entry.getKey(), new HashSet<>());
            entities.removeAll(removed);
            for (Entity e : removed) {
                for (Component c : e.getComponents().values()) {
                    toRemoveComponentTypeMap.get(c.getType().id()).add(e);
                }
            }
        }
        toRemoveEntityTypeMap.clear();
    }

    /**
     * Schedule a clearing of all entities
     */
    public void scheduleClear() {
        clear = true;
    }

    private void clearEntities() {
        entityTypeMap.clear();
        toAddEntityTypeMap.clear();
        toRemoveEntityTypeMap.clear();
        componentTypeMap.clear();
        toAddComponentTypeMap.clear();
        toRemoveComponentTypeMap.clear();
    }
}
