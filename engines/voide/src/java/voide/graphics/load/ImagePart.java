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
        return parentOffset.sum(offset.elementDiv(parentShape));
    }

    public VectorD getTextureShape() {
        return shape.elementDiv(parent.hardGet().getTextureShape());
    }

    public void setParent(String parent) {
        this.parent = new ResourceProxy<>(parent, Image.class);
    }

    public void setOffset(VectorD offset) {
        this.offset = offset;
    }

}
