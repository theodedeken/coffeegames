package spaxel.entity.storage.status;

import spaxel.entity.Storage;
import spaxel.entity.ComponentType;

/**
 * Created by theod on 28-6-2017.
 */
public class StatusStorage extends Storage {
    private boolean canShoot;
    private boolean canMove;
    // TODO implement slow and other status effects and modifiers via map

    public StatusStorage() {
        super(ComponentType.STATUS);
    }

    public StatusStorage(boolean canShoot, boolean canMove) {
        super(ComponentType.STATUS);
        this.canShoot = canShoot;
        this.canMove = canMove;
    }

    public boolean canShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public StatusStorage copy() {
        return new StatusStorage(canShoot, canMove);
    }
}
