package spaxel.entity.storage.item;

import spaxel.entity.SpaxelComponent;
import voide.entity.Storage;

public class ItemStorage extends Storage {

    private ItemContainer container;
    private ItemName name;

    public ItemStorage() {
        super(SpaxelComponent.ITEM);
    }

    public ItemStorage(ItemContainer container, ItemName name) {
        super(SpaxelComponent.ITEM);
        this.container = container;
        this.name = name;
    }

    public ItemStorage copy() {
        return new ItemStorage(container, name);
    }

    /**
     * @return the type
     */
    public ItemContainer getContainer() {
        return container;
    }

    /**
     * @param type the type to set
     */
    public void setContainer(ItemContainer container) {
        this.container = container;
    }

    /**
     * @return the name
     */
    public ItemName getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(ItemName name) {
        this.name = name;
    }
}
