package voide.graphics.load;

import java.util.logging.Level;
import java.util.logging.Logger;
import voide.debug.RepresentationBuilder;
import voide.graphics.renderable.Texture;
import voide.math.VectorD;
import voide.resources.ResourceProxy;

public class ImagePart extends Image {

    private static final Logger LOGGER = Logger.getLogger(
        ImagePart.class.getName()
    );
    private VectorD offset;
    private ResourceProxy<Image> parent;

    public ImagePart() {}

    public ImagePart(String parentKey, VectorD offset, VectorD shape) {
        this.parent = new ResourceProxy<>(parentKey, Image.class);
        this.offset = offset;
        this.shape = shape;
    }

    public void initialize() {
        LOGGER.log(Level.INFO, "Initialized " + repr());
    }

    public String repr() {
        return String.format(
            "ImagePart { %dx%d, at %s }",
            (int) shape.getValue(0),
            (int) shape.getValue(1),
            offset.repr()
        );
    }

    public String fullRepr() {
        return new RepresentationBuilder(getClass().getName())
            .field("shape", shape.repr())
            .field("offset", shape.repr())
            .field("parent", parent.repr())
            .build();
    }

    /*
     * TODO add this method public Image toImage() {
     *
     * }
     */

    public Texture toTexture(int textureId) {
        return new Texture(
            shape,
            textureId,
            getTextureOffset(),
            getTextureShape()
        );
    }

    public VectorD getTextureOffset() {
        VectorD parentOffset = parent.hardGet().getTextureOffset();
        VectorD parentShape = parent.hardGet().getTextureShape();

        VectorD relOffset = offset
            .elementDiv(parent.hardGet().getShape())
            .elementMult(parentShape);

        return parentOffset.sum(relOffset);
    }

    public VectorD getTextureShape() {
        VectorD parentShape = parent.hardGet().getTextureShape();
        VectorD relShape = shape
            .elementDiv(parent.hardGet().getShape())
            .elementMult(parentShape);
        return relShape;
    }

    public void setParent(String parent) {
        this.parent = new ResourceProxy<>(parent, Image.class);
    }

    public void setOffset(VectorD offset) {
        this.offset = offset;
    }
}
