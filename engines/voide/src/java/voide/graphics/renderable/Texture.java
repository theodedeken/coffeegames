package voide.graphics.renderable;

import java.util.Vector;

import voide.math.VectorD;
import voide.render.buffer.RenderJob;

/**
 * Represents a part of a gpu texture
 */
public class Texture extends Renderable {
    private int textureId;
    private VectorD textureOffset;
    private VectorD textureShape;

    public Texture(VectorD shape, int textureId, VectorD textureOffset, VectorD textureShape) {
        super(shape);
        this.textureId = textureId;
        this.textureOffset = textureOffset;
        this.textureShape = textureShape;
    }

    public int getTextureId() {
        return textureId;
    }

    public void setTextureId(int textureId) {
        this.textureId = textureId;
    }

    public void apply(RenderJob data) {
        data.setTexOffset(textureOffset, textureShape);
        data.setSpriteSheetID(textureId);
        data.applyScale(shape);
    }

    public Texture getSubTexture(VectorD subTextureOffset, VectorD subTextureShape) {
        VectorD subShape = subTextureShape.elementMult(shape);
        VectorD rescaledOffset = subTextureOffset.elementMult(textureShape);
        VectorD rescaledShape = subTextureShape.elementMult(textureShape);
        VectorD newOffset = textureOffset.sum(rescaledOffset);
        return new Texture(subShape, textureId, newOffset, rescaledShape);
    }

    public VectorD getTextureOffset() {
        return textureOffset;
    }

    public VectorD getTextureShape() {
        return textureShape;
    }

}
