package spaxel.entity.storage.cooldown;

import spaxel.entity.SpaxelComponent;
import voide.entity.Storage;

/**
 * Represents a cooldown for an entity
 *
 * Created by theo on 16/06/17.
 */
public class CooldownStorage extends Storage {

    private int currentCooldown;
    private int maxCooldown;

    public CooldownStorage() {
        super(SpaxelComponent.COOLDOWN);
    }

    /**
     * Create a new CooldownStorage with the specified cooldown
     *
     * @param currentCooldown the current cooldown
     * @param maxCooldown     the maximum cooldown
     */
    public CooldownStorage(int currentCooldown, int maxCooldown) {
        super(SpaxelComponent.COOLDOWN);
        this.currentCooldown = currentCooldown;
        this.maxCooldown = maxCooldown;
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public void setCurrentCooldown(int currentCooldown) {
        this.currentCooldown = currentCooldown;
    }

    public int getMaxCooldown() {
        return maxCooldown;
    }

    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }

    public void startCooldown() {
        currentCooldown = maxCooldown;
    }

    public CooldownStorage copy() {
        return new CooldownStorage(currentCooldown, maxCooldown);
    }
}
