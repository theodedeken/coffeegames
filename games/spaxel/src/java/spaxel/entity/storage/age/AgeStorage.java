package spaxel.entity.storage.age;

import spaxel.entity.SpaxelComponent;
import voide.entity.Storage;

/**
 * Represent the age of a component, components with age 0 will die
 *
 * Created by theo on 3/06/17.
 */
public class AgeStorage extends Storage {

    private int currentLife;
    private int maxLife;

    public AgeStorage() {
        super(SpaxelComponent.AGE);
    }

    /**
     * Create a new AgeStorage with the specified life
     *
     * @param currentLife the current life of the component
     * @param maxLife     the maximum life of the component
     */
    public AgeStorage(int currentLife, int maxLife) {
        super(SpaxelComponent.AGE);
        this.currentLife = currentLife;
        this.maxLife = maxLife;
    }

    public int getLife() {
        return currentLife;
    }

    public void setLife(int currentLife) {
        this.currentLife = currentLife;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public AgeStorage copy() {
        return new AgeStorage(currentLife, maxLife);
    }
}
