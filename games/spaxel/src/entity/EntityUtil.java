package spaxel.entity;

import java.util.ArrayList;
import java.util.List;
import spaxel.entity.ComponentType;
import spaxel.entity.storage.item.ItemContainer;
import spaxel.entity.storage.item.ItemName;
import spaxel.entity.storage.item.ItemStorage;
import spaxel.engine.Resources;
import spaxel.entity.Entity;
import spaxel.entity.EntityType;

/**
 * Provides utility functions for entities
 */
public final class EntityUtil {


    private EntityUtil() {

    }

    /**
     * Return a list with all the item names an entity has in its inventories
     * 
     * @param entity the entity to get the item names from
     * 
     * @return a list with item names
     */
    public static List<ItemName> getAllItemNames(Entity entity) {
        List<ItemName> items = new ArrayList<>();

        for (Entity link : entity.getLinks((e) -> e.getType() == EntityType.ITEM)) {
            ItemStorage itemStore = (ItemStorage) link.getComponent(ComponentType.ITEM);

            items.add(itemStore.getName());
        }
        return items;
    }

    /**
     * Add a number of random items to an entity
     * 
     * @param entity the entity to add the items to
     * @param number the amount of items to add
     * @param type   the type of items to add
     */
    public static void addRandomItems(Entity entity, int number, ItemContainer type) {
        for (int i = 0; i < number; i++) {
            // produce a random item of the given type
            Entity item = Resources.get().getItems().produceRandom(prop -> prop.getType() == type);
            entity.addLink(item);
        }
    }

    /**
     * Calculate the rotation for one update step based on the required rotation change and the
     * turnrate
     * 
     * @param rotChange the required rotation change
     * @param turnrate  the maximum amount of rotation that can occur
     * 
     * @return the rotation for on update step
     */
    public static double calculateDeltaRot(double rotChange, double turnrate) {
        double deltaRot = 0;
        if (Math.abs(rotChange) < turnrate) {
            // if the rotation change is less than the turnrate we can just do it
            deltaRot = rotChange;
        } else if (rotChange < 0) {
            // else decide which direction to turn
            if (rotChange < -Math.PI) {
                deltaRot = turnrate;
            } else {
                deltaRot = -turnrate;
            }
        } else {
            if (rotChange > Math.PI) {
                deltaRot = -turnrate;
            } else {
                deltaRot = turnrate;
            }
        }
        return deltaRot;
    }

    public static int healthAtLevel(int level, int baseHealth) {
        return baseHealth * level;
    }
}
