package spaxel.entity.storage.spawn;

import spaxel.entity.ComponentType;
import spaxel.entity.Storage;

public class SpawnStorage extends Storage {
    private double maxDeltaRot;
    private double maxSpeed;
    private int maxLife;
    private int rate;
    private String industry;

    public SpawnStorage() {
        super(ComponentType.SPAWN_STORE);
    }

    public SpawnStorage(double maxDeltaRot, double maxSpeed, int maxLife, int rate,
            String industry) {
        super(ComponentType.SPAWN_STORE);
        this.maxDeltaRot = maxDeltaRot;
        this.maxSpeed = maxSpeed;
        this.maxLife = maxLife;
        this.rate = rate;
        this.industry = industry;
    }

    /**
     * @return the maxDeltaRot
     */
    public double getMaxDeltaRot() {
        return maxDeltaRot;
    }

    /**
     * @param maxDeltaRot the maxDeltaRot to set
     */
    public void setMaxDeltaRot(double maxDeltaRot) {
        this.maxDeltaRot = maxDeltaRot;
    }

    /**
     * @return the maxSpeed
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @param maxSpeed the maxSpeed to set
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * @return the maxLife
     */
    public int getMaxLife() {
        return maxLife;
    }

    /**
     * @param maxLife the maxLife to set
     */
    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * @return the industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * @param industry the industry to set
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public SpawnStorage copy() {
        return new SpawnStorage(maxDeltaRot, maxSpeed, maxLife, rate, industry);
    }
}
