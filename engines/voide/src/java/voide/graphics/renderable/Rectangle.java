package voide.graphics.renderable;

import com.fasterxml.jackson.annotation.JsonSetter;
import voide.debug.RepresentationBuilder;
import voide.math.VectorD;
import voide.render.buffer.RenderJob;

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
     * @param shape the dimensions of the colorbox
     * @param color the color of the colorbox
     */
    public Rectangle(VectorD shape, int color) {
        super(shape);
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
        job.applyScale(shape);
        job.setColor(color);
    }

    public String repr() {
        return String.format("Rectangle { %s, %s }", name, shape.repr());
    }

    public String fullRepr() {
        return new RepresentationBuilder(getClass().getName())
            .field("color", color)
            .field("shape", shape)
            .field("name", name)
            .build();
    }
}
