package spaxel.graphics.texture;

import spaxel.graphics.buffer.RenderJob;
import voide.math.VectorD;

/**
 * Represents a Renderable part of the game
 */
public abstract class Renderable {
    protected VectorD dim;
    protected String name;

    /**
     * Create a new Renderable
     */
    public Renderable() {
        super();
    }

    /**
     * Create new Renderable with the specified dimension
     * 
     * @param dim the dimension
     */
    public Renderable(VectorD dim) {
        this.dim = dim;
    }

    /**
     * @param dim the dim to set
     */
    public void setDim(VectorD dim) {
        this.dim = dim;
    }

    public VectorD getDim() {
        return dim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Apply this renderable's data to the renderjob
     * 
     * @param job the renderjob
     */
    public abstract void apply(RenderJob job);
}
