package spaxel.entity;

import spaxel.entity.Component;
import spaxel.entity.ComponentType;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import spaxel.entity.Entity;
import spaxel.entity.EntityType;

/**
 * Collection of all entities while playing the game
 * 
 * Created by theo on 31/05/17.
 */
public class EntityStream {
    private EnumMap<EntityType, Set<Entity>> entityTypeMap;
    private EnumMap<EntityType, Set<Entity>> toAddEntityTypeMap;
    private EnumMap<EntityType, Set<Entity>> toRemoveEntityTypeMap;
    private EnumMap<ComponentType, Set<Entity>> componentTypeMap;
    private EnumMap<ComponentType, Set<Entity>> toAddComponentTypeMap;
    private EnumMap<ComponentType, Set<Entity>> toRemoveComponentTypeMap;
    private boolean clear;

    /**
     * Create a new EntityStream
     */
    public EntityStream() {
        entityTypeMap = new EnumMap<>(EntityType.class);
        toAddEntityTypeMap = new EnumMap<>(EntityType.class);
        toRemoveEntityTypeMap = new EnumMap<>(EntityType.class);
        for (EntityType eType : EntityType.values()) {
            entityTypeMap.put(eType, new HashSet<>());
            toAddEntityTypeMap.put(eType, new HashSet<>());
            toRemoveEntityTypeMap.put(eType, new HashSet<>());
        }
        componentTypeMap = new EnumMap<>(ComponentType.class);
        toAddComponentTypeMap = new EnumMap<>(ComponentType.class);
        toRemoveComponentTypeMap = new EnumMap<>(ComponentType.class);
        for (ComponentType cType : ComponentType.values()) {
            componentTypeMap.put(cType, new HashSet<>());
            toAddComponentTypeMap.put(cType, new HashSet<>());
            toRemoveComponentTypeMap.put(cType, new HashSet<>());
        }
    }


    /**
     * Get a set of entities of the given type
     * 
     * @param type the entity type
     * 
     * @return the set of entities
     */
    public Set<Entity> getEntities(EntityType type) {
        return entityTypeMap.get(type);
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
            return new HashSet<>(componentTypeMap.get(type));
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
        return componentTypeMap.get(type);
    }

    public Entity getPlayer() {
        return entityTypeMap.get(EntityType.PLAYER).iterator().next();
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
        toAddEntityTypeMap.get(entity.getType()).add(entity);
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
        toRemoveEntityTypeMap.get(entity.getType()).add(entity);
    }

    /**
     * Add a component to an entity
     * 
     * @param type   the type of the component
     * @param entity the entity
     */
    public void addComponent(ComponentType type, Entity entity) {
        toAddComponentTypeMap.get(type).add(entity);
    }

    /**
     * Remove a component from an entity
     * 
     * @param type   the type of the component
     * @param entity the entity
     */
    public void removeComponent(ComponentType type, Entity entity) {
        toRemoveComponentTypeMap.get(type).add(entity);
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
                for (Map.Entry<ComponentType, Set<Entity>> entry : componentTypeMap.entrySet()) {
                    ComponentType type = entry.getKey();
                    Set<Entity> entities = entry.getValue();
                    entities.addAll(toAddComponentTypeMap.get(type));
                    toAddComponentTypeMap.get(type).clear();
                    entities.removeAll(toRemoveComponentTypeMap.get(type));
                    toRemoveComponentTypeMap.get(type).clear();
                }
            } else {
                clearEntities();
                clear = false;
            }
        }
    }

    private void addEntities() {
        for (Map.Entry<EntityType, Set<Entity>> entry : entityTypeMap.entrySet()) {
            EntityType type = entry.getKey();
            entry.getValue().addAll(toAddEntityTypeMap.get(type));
            for (Entity e : toAddEntityTypeMap.get(type)) {
                for (Component c : e.getComponents().values()) {
                    toAddComponentTypeMap.get(c.getType()).add(e);
                }
            }
            toAddEntityTypeMap.get(type).clear();
        }
    }

    private void removeEntities() {
        for (Map.Entry<EntityType, Set<Entity>> entry : entityTypeMap.entrySet()) {
            EntityType type = entry.getKey();
            entry.getValue().removeAll(toRemoveEntityTypeMap.get(type));
            for (Entity e : toRemoveEntityTypeMap.get(type)) {
                for (Component c : e.getComponents().values()) {
                    toRemoveComponentTypeMap.get(c.getType()).add(e);
                }
            }
            toRemoveEntityTypeMap.get(type).clear();
        }
    }

    /**
     * Schedule a clearing of all entities
     */
    public void scheduleClear() {
        clear = true;
    }

    private void clearEntities() {
        for (EntityType type : EntityType.values()) {
            entityTypeMap.get(type).clear();
            toAddEntityTypeMap.get(type).clear();
            toRemoveEntityTypeMap.get(type).clear();
        }
        for (ComponentType type : ComponentType.values()) {
            componentTypeMap.get(type).clear();
            toAddComponentTypeMap.get(type).clear();
            toRemoveComponentTypeMap.get(type).clear();
        }
    }
}
