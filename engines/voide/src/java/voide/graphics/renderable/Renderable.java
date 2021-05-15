package voide.graphics.renderable;

import voide.math.VectorD;
import voide.render.buffer.RenderJob;
import voide.resources.Resource;

/**
 * Represents a Renderable part of the game
 */
public abstract class Renderable implements Resource {
    protected VectorD shape;
    protected String name;

    /**
     * Create a new Renderable
     */
    public Renderable() {
        super();
    }

    public void initialize() {
    }

    /**
     * Create new Renderable with the specified dimension
     * 
     * @param shape the dimension
     */
    public Renderable(VectorD shape) {
        this.shape = shape;
    }

    /**
     * @param shape the dim to set
     */
    public void setShape(VectorD shape) {
        this.shape = shape;
    }

    public VectorD getShape() {
        return shape;
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
