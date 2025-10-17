package spaxel.entity.storage.shoot;

import spaxel.entity.SpaxelComponent;
import spaxel.entity.industry.ProjectileIndustry;
import voide.entity.Storage;
import voide.resources.ResourceProxy;

public class ShootStorage extends Storage {

    private ResourceProxy<ProjectileIndustry> projectileFactory;

    public ShootStorage() {
        super(SpaxelComponent.SHOOT);
    }

    public ShootStorage(ResourceProxy<ProjectileIndustry> projectileFactory) {
        super(SpaxelComponent.SHOOT);
        this.projectileFactory = projectileFactory;
    }

    /**
     * @return the projectileFactory
     */
    public ProjectileIndustry getProjectileFactory() {
        return projectileFactory.get();
    }

    /**
     * @param projectileFactory the projectileFactory to set
     */
    public void setProjectileFactory(String projectileFactory) {
        this.projectileFactory =
            new ResourceProxy<>(projectileFactory, ProjectileIndustry.class);
    }

    public ShootStorage copy() {
        return new ShootStorage(projectileFactory.copy());
    }
}
