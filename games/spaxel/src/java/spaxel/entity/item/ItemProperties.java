package spaxel.entity.item;

import spaxel.entity.storage.item.ItemContainer;
import voide.entity.EntityIndustry;
import voide.resources.Resource;
import voide.resources.ResourceProxy;

/**
 * Represents the properties of an item
 * 
 * Created by theod on 12-7-2017.
 */
public class ItemProperties implements Resource {
    private String name;
    private ResourceProxy<EntityIndustry> industry;
    private int spawnRate;
    private Rarety rarety;
    private ItemContainer type;

    /**
     * Create a new ItemProperties
     */
    public ItemProperties() {
        super();
    }

    public void initialize() {
    }

    public EntityIndustry getIndustry() {
        return industry.get();
    }

    public void setIndustry(String industry) {
        this.industry = new ResourceProxy<>(industry, EntityIndustry.class);
    }

    public int getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    public Rarety getRarety() {
        return rarety;
    }

    public void setRarety(Rarety rarety) {
        this.rarety = rarety;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemContainer getType() {
        return type;
    }

    public void setType(ItemContainer type) {
        this.type = type;
    }
}
