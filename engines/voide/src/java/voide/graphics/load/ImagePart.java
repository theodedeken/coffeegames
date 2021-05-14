package voide.graphics.load;

import voide.graphics.renderable.Texture;
import voide.math.VectorD;
import voide.resources.ResourceProxy;

public class ImagePart extends Image {
    private VectorD offset;
    private ResourceProxy<Image> parent;

    public ImagePart() {

    }

    public ImagePart(String parentKey, VectorD offset, VectorD shape) {
        this.parent = new ResourceProxy<>(parentKey, Image.class);
        this.offset = offset;
        this.shape = shape;
    }

    public void initialize() {
    }

    /*
     * TODO add this method public Image toImage() {
     * 
     * }
     */

    public Texture toTexture(int textureId) {
        return new Texture(shape, textureId, getTextureOffset(), getTextureShape());
    }

    public VectorD getTextureOffset() {
        VectorD parentOffset = parent.hardGet().getTextureOffset();
        VectorD parentShape = parent.hardGet().getTextureShape();

        VectorD relOffset = offset.elementDiv(parent.hardGet().getShape()).elementMult(parentShape);

        return parentOffset.sum(relOffset);
    }

    public VectorD getTextureShape() {
        VectorD parentShape = parent.hardGet().getTextureShape();
        VectorD relShape = shape.elementDiv(parent.hardGet().getShape()).elementMult(parentShape);
        return relShape;
    }

    public void setParent(String parent) {
        this.parent = new ResourceProxy<>(parent, Image.class);
    }

    public void setOffset(VectorD offset) {
        this.offset = offset;
    }

}
