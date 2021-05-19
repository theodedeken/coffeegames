package spaxel.entity.storage.damage;

/**
 * Created by theo on 8/06/17.
 */
public class Damage {

    private int damageValue;

    /**
     * Create a new Damage instance with the specified damage amount
     *
     * @param damageValue the damage
     */
    public Damage(int damageValue) {
        this.damageValue = damageValue;
    }

    public int getDamage() {
        return damageValue;
    }

    public void setDamage(int damageValue) {
        this.damageValue = damageValue;
    }
}
