package spaxel.entity.storage.projectile;

import spaxel.entity.SpaxelComponent;
import voide.entity.EntityIndustry;
import voide.entity.Storage;
import voide.resources.ResourceProxy;

public class ProjectileStorage extends Storage {

    private int damage;
    private ResourceProxy<EntityIndustry> onHitSpawner;
    private ResourceProxy<EntityIndustry> onHitEffect;
    private int particleSize;

    public ProjectileStorage() {
        super(SpaxelComponent.PROJECTILE);
    }

    public ProjectileStorage(
        int damage,
        ResourceProxy<EntityIndustry> onHitSpawner,
        ResourceProxy<EntityIndustry> onHitEffect,
        int particleSize
    ) {
        super(SpaxelComponent.PROJECTILE);
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
    public EntityIndustry getOnHitSpawner() {
        return onHitSpawner.get();
    }

    /**
     * @param onHitSpawner the onHitSpawner to set
     */
    public void setOnHitSpawner(String onHitSpawner) {
        this.onHitSpawner =
            new ResourceProxy<>(onHitSpawner, EntityIndustry.class);
    }

    /**
     * @return the onHitEffect
     */
    public EntityIndustry getOnHitEffect() {
        return onHitEffect.get();
    }

    /**
     * @param onHitEffect the onHitEffect to set
     */
    public void setOnHitEffect(String onHitEffect) {
        this.onHitEffect =
            new ResourceProxy<>(onHitEffect, EntityIndustry.class);
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
        return new ProjectileStorage(
            damage,
            onHitSpawner.copy(),
            onHitEffect.copy(),
            particleSize
        );
    }
}
