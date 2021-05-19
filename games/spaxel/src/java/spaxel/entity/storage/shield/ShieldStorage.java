package spaxel.entity.storage.shield;

import spaxel.entity.SpaxelComponent;
import voide.entity.Storage;

public class ShieldStorage extends Storage {

    private int currentCapacity;
    private int maxCapacity;

    public ShieldStorage() {
        super(SpaxelComponent.SHIELD);
    }

    public ShieldStorage(int currentCapacity, int maxCapacity) {
        super(SpaxelComponent.SHIELD);
        this.currentCapacity = currentCapacity;
        this.maxCapacity = maxCapacity;
    }

    /**
     * @return the currentCapacity
     */
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    /**
     * @param currentCapacity the currentCapacity to set
     */
    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    /**
     * @return the maxCapacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * @param maxCapacity the maxCapacity to set
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void subCapacity(int subtraction) {
        currentCapacity -= subtraction;
    }

    public void resetCapacity() {
        currentCapacity = maxCapacity;
    }

    public ShieldStorage copy() {
        return new ShieldStorage(currentCapacity, maxCapacity);
    }
}
