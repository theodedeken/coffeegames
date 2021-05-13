package voide.graphics.renderable;

import voide.math.VectorD;
import voide.render.RenderJob;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Represents a renderable colored box
 */
public class Rectangle extends Renderable {
    private static final int HEX_SIZE = 16;

    private int color;

    /**
     * Create a new ColorBox
     */
    public Rectangle() {
        super();
    }

    /**
     * Create a new ColorBox with the specified dimensions and color
     * 
     * @param dim   the dimensions of the colorbox
     * @param color the color of the colorbox
     */
    public Rectangle(VectorD dim, int color) {
        super(dim);
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    @JsonSetter("color")
    public void setColorValue(String colorValue) {
        color = Integer.parseUnsignedInt(colorValue, HEX_SIZE);
    }

    public void apply(RenderJob job) {
        job.applyScale(dim);
        job.setColor(color);
    }
}
