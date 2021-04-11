package spaxel.engine;

import spaxel.entity.storage.item.ItemContainer;

/**
 * Represents the properties of an item
 * 
 * Created by theod on 12-7-2017.
 */
public class ItemProperties {
    private String name;
    private String industry;
    private int spawnRate;
    private Rarety rarety;
    private ItemContainer type;

    /**
     * Create a new ItemProperties
     */
    public ItemProperties() {
        super();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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
