package spaxel.entity.storage.transformation;

import spaxel.entity.ComponentType;
import spaxel.entity.Storage;
import spaxel.math.VectorD;

public class TransformationStorage extends Storage {
    private VectorD position;
    private double rotation;
    private double scale;

    public TransformationStorage() {
        super(ComponentType.TRANSFORMATION);
    }

    public TransformationStorage(VectorD position, double rotation, double scale) {
        super(ComponentType.TRANSFORMATION);
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }


    /**
     * @return the position
     */
    public VectorD getPosition() {
        return position;
    }


    /**
     * @param position the position to set
     */
    public void setPosition(VectorD position) {
        this.position = position;
    }


    /**
     * @return the rotation
     */
    public double getRotation() {
        return rotation;
    }


    /**
     * @param rotation the rotation to set
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }


    /**
     * @return the scale
     */
    public double getScale() {
        return scale;
    }


    /**
     * @param scale the scale to set
     */
    public void setScale(double scale) {
        this.scale = scale;
    }

    public TransformationStorage copy() {
        return new TransformationStorage(position.copy(), rotation, scale);
    }
}
