package spaxel.entity.storage.shoot;

import spaxel.entity.ComponentType;
import spaxel.entity.Storage;

public class ShootStorage extends Storage {
    private String projectileFactory;

    public ShootStorage(){
        super(ComponentType.SHOOT);
    }

    public ShootStorage(String projectileFactory){
        super(ComponentType.SHOOT);
        this.projectileFactory = projectileFactory;
    }

    /**
     * @return the projectileFactory
     */
    public String getProjectileFactory() {
        return projectileFactory;
    }

    /**
     * @param projectileFactory the projectileFactory to set
     */
    public void setProjectileFactory(String projectileFactory) {
        this.projectileFactory = projectileFactory;
    }

    public ShootStorage copy(){
        return new ShootStorage(projectileFactory);
    }
}