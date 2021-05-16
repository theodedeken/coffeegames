package spaxel.entity.storage.health;

import spaxel.entity.SpaxelComponent;
import voide.entity.Storage;

/**
 * Created by theo on 3/06/17.
 */
public class HealthStorage extends Storage {
    private int currentHealth;
    private int baseHealth;
    private int maxHealth;

    public HealthStorage() {
        super(SpaxelComponent.HEALTH);
    }

    public HealthStorage(int health, int baseHealth, int maxHealth) {
        super(SpaxelComponent.HEALTH);
        this.currentHealth = health;
        this.maxHealth = maxHealth;
        this.baseHealth = baseHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public HealthStorage copy() {
        return new HealthStorage(currentHealth, baseHealth, maxHealth);
    }
}
