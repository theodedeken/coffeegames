package spaxel.entity.storage.projectile;

import spaxel.entity.ComponentType;
import spaxel.entity.Storage;

public class ProjectileStorage extends Storage {
    private int damage;
    private String onHitSpawner;
    private String onHitEffect;
    private int particleSize;

    public ProjectileStorage() {
        super(ComponentType.PROJECTILE);
    }

    public ProjectileStorage(int damage, String onHitSpawner, String onHitEffect,
            int particleSize) {
        super(ComponentType.PROJECTILE);
        this.damage = damage;
        this.onHitSpawner = onHitSpawner;
        this.onHitEffect = onHitEffect;
        this.particleSize = particleSize;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void subDamage(int subtraction) {
        damage -= subtraction;
    }

    /**
     * @return the onHitSpawner
     */
    public String getOnHitSpawner() {
        return onHitSpawner;
    }

    /**
     * @param onHitSpawner the onHitSpawner to set
     */
    public void setOnHitSpawner(String onHitSpawner) {
        this.onHitSpawner = onHitSpawner;
    }

    /**
     * @return the onHitEffect
     */
    public String getOnHitEffect() {
        return onHitEffect;
    }

    /**
     * @param onHitEffect the onHitEffect to set
     */
    public void setOnHitEffect(String onHitEffect) {
        this.onHitEffect = onHitEffect;
    }

    /**
     * @return the particleSize
     */
    public int getParticleSize() {
        return particleSize;
    }

    /**
     * @param particleSize the particleSize to set
     */
    public void setParticleSize(int particleSize) {
        this.particleSize = particleSize;
    }

    public ProjectileStorage copy() {
        return new ProjectileStorage(damage, onHitSpawner, onHitEffect, particleSize);
    }
}
