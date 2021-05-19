package spaxel.entity.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import voide.debug.RepresentationBuilder;
import voide.entity.Entity;
import voide.entity.EntityIndustry;
import voide.random.VoideRandom;
import voide.resources.Resource;

/**
 * Catalogue for all the items
 *
 * Created by theod on 12-7-2017.
 */
public class ItemCatalogue implements Resource {

    private static final Logger LOGGER = Logger.getLogger(
        ItemCatalogue.class.getName()
    );
    Map<String, ItemProperties> items;
    List<EntityIndustry> industryList;
    VoideRandom random;

    /**
     * Create a new ItemCatalogue from the given item properties
     *
     * @param itemProps the properties of the items
     */
    public ItemCatalogue(Map<String, ItemProperties> itemProps) {
        items = new HashMap<>();
        for (Entry<String, ItemProperties> ip : itemProps.entrySet()) {
            items.put(ip.getKey(), ip.getValue());
        }
        random = new VoideRandom();
        initialize();
    }

    public void initialize() {
        this.industryList = new ArrayList<>();
        for (ItemProperties prop : items.values()) {
            for (int i = 0; i < prop.getSpawnRate(); i++) {
                industryList.add(prop.getIndustry());
            }
        }
        LOGGER.log(Level.INFO, "Initialized " + repr());
    }

    /**
     * Add a new item
     *
     * @param ip the properties of the new item
     */
    public void addItemProp(ItemProperties ip) {
        items.put(ip.getName(), ip);
        initialize();
    }

    /**
     * Produce a random item out of the list of itemproperties
     *
     * @param options the items to choose from
     *
     * @return the produced item
     */
    private Entity produceRandom(List<ItemProperties> options) {
        ItemProperties chosen = random.choose(options);
        return chosen.getIndustry().produce();
    }

    /**
     * Produce a random item choosing from a list of items that pass the given
     * filters
     *
     * @param filters the filters to pass
     *
     * @return the produced item
     */
    public Entity produceRandom(ItemFilter... filters) {
        List<ItemProperties> chosen = new ArrayList<>();
        for (ItemProperties prop : items.values()) {
            boolean passed = true;
            for (ItemFilter filter : filters) {
                if (!filter.pass(prop)) {
                    passed = false;
                    break;
                }
            }
            if (passed) {
                chosen.add(prop);
            }
        }
        return produceRandom(chosen);
    }

    /**
     * Get the properties of an item with the given name
     *
     * @param name the name of the item
     *
     * @return the properties
     */
    public ItemProperties getItemProp(String name) {
        return items.get(name);
    }

    /**
     * Interface for filtering Items
     */
    public interface ItemFilter {
        /**
         * Return true if a given rule is forfilled
         *
         * @param prop the item properties to test the rule on
         *
         * @return true if passed
         */
        boolean pass(ItemProperties prop);
    }

    public String repr() {
        return String.format("ItemCatalogue { %s items }", items.size());
    }

    public String fullRepr() {
        return new RepresentationBuilder(getClass().getName())
            .field("items", new ArrayList<>(items.keySet()))
            .field("industries", industryList)
            .build();
    }
}
