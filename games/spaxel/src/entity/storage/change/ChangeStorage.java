package spaxel.entity.storage.change;

import spaxel.entity.Storage;
import spaxel.entity.ComponentType;
import voide.math.VectorD;

/**
 * Created by theo on 3/06/17.
 */
public class ChangeStorage extends Storage {
    private VectorD positionChange;
    private double rotationChange;
    private double scaleChange;

    public ChangeStorage() {
        super(ComponentType.CHANGE);
    }

    public ChangeStorage(VectorD positionChange, double rotationChange, double scaleChange) {
        super(ComponentType.CHANGE);
        this.positionChange = positionChange;
        this.rotationChange = rotationChange;
        this.scaleChange = scaleChange;
    }

    /**
     * @return the positionChange
     */
    public VectorD getPositionChange() {
        return positionChange;
    }

    /**
     * @param positionChange the positionChange to set
     */
    public void setPositionChange(VectorD positionChange) {
        this.positionChange = positionChange;
    }

    /**
     * @return the rotationChange
     */
    public double getRotationationChange() {
        return rotationChange;
    }

    /**
     * @param rotationChange the rotationChange to set
     */
    public void setRotationChange(double rotationChange) {
        this.rotationChange = rotationChange;
    }

    /**
     * @return the scaleChange
     */
    public double getScaleChange() {
        return scaleChange;
    }

    /**
     * @param scaleChange the scaleChange to set
     */
    public void setScaleChange(double scaleChange) {
        this.scaleChange = scaleChange;
    }

    public ChangeStorage copy() {
        return new ChangeStorage(positionChange.copy(), rotationChange, scaleChange);
    }
}
