package voide.graphics.util;

import java.util.HashMap;
import java.util.Map;
import voide.graphics.load.Image;
import voide.graphics.load.ImagePart;
import voide.math.VectorD;

/**
 * Represents a node in the tree representing the sprites in the packed texture
 */
public class ImageNode {

    private static final int DIM_BASE = 2;
    private Image image;
    private String key;
    private int dim;
    private NodePlacement placement;
    private ImageNode parent;

    private ImageNode topLeft;
    private ImageNode topRight;
    private ImageNode botLeft;
    private ImageNode botRight;

    /**
     * Create a new leaf ImageNode
     *
     * @param texture the leaf content
     */
    public ImageNode(String key, Image image) {
        this.key = key;
        this.image = image;
        int maxDim = (int) Math.max(
            image.getShape().getValue(0),
            image.getShape().getValue(1)
        );
        this.dim = DIM_BASE;
        while (dim < maxDim) {
            dim *= DIM_BASE;
        }
    }

    /**
     * Create a new ImageNode with the specified dimension
     *
     * @param dim the dimension of the ImageNode
     */
    public ImageNode(int dim) {
        this.dim = dim;
        this.placement = NodePlacement.TOP_LEFT;
    }

    public VectorD getPos() {
        VectorD pos;
        switch (placement) {
            case TOP_LEFT:
                pos = new VectorD(0, 0);
                break;
            case TOP_RIGHT:
                pos = new VectorD(dim, 0);
                break;
            case BOT_LEFT:
                pos = new VectorD(0, dim);
                break;
            case BOT_RIGHT:
                pos = new VectorD(dim, dim);
                break;
            default:
                pos = new VectorD(0, 0);
        }
        if (parent != null) {
            return parent.getPos().sum(pos);
        } else {
            return pos;
        }
    }

    private enum NodePlacement {
        TOP_LEFT,
        TOP_RIGHT,
        BOT_LEFT,
        BOT_RIGHT
    }

    public int getDim() {
        return dim;
    }

    public void setPlacement(NodePlacement placement) {
        this.placement = placement;
    }

    public void setParent(ImageNode parent) {
        this.parent = parent;
    }

    public ImageNode getTopLeft() {
        return topLeft;
    }

    public ImageNode getTopRight() {
        return topRight;
    }

    public ImageNode getBotLeft() {
        return botLeft;
    }

    public ImageNode getBotRight() {
        return botRight;
    }

    /**
     * @param topLeft the topLeft to set
     */
    public void setTopLeft(ImageNode topLeft) {
        this.topLeft = topLeft;
        topLeft.setPlacement(NodePlacement.TOP_LEFT);
        topLeft.setParent(this);
    }

    /**
     * @param topRight the topRight to set
     */
    public void setTopRight(ImageNode topRight) {
        this.topRight = topRight;
        topRight.setPlacement(NodePlacement.TOP_RIGHT);
        topRight.setParent(this);
    }

    /**
     * @param botLeft the botLeft to set
     */
    public void setBotLeft(ImageNode botLeft) {
        this.botLeft = botLeft;
        botLeft.setPlacement(NodePlacement.BOT_LEFT);
        botLeft.setParent(this);
    }

    /**
     * @param botRight the botRight to set
     */
    public void setBotRight(ImageNode botRight) {
        this.botRight = botRight;
        botRight.setPlacement(NodePlacement.BOT_RIGHT);
        botRight.setParent(this);
    }

    /**
     * Initialize the texture coordinates of all the leaf textures in this tree
     *
     * @param packedTexture the packedTexture of this tree
     */
    public Map<String, ImagePart> toImageParts(String packed) {
        Map<String, ImagePart> output = new HashMap<>();
        if (image != null) {
            output.put(
                key,
                new ImagePart(packed, this.getPos(), image.getShape())
            );
        } else {
            if (topLeft != null) {
                output.putAll(topLeft.toImageParts(packed));
            }
            if (topRight != null) {
                output.putAll(topRight.toImageParts(packed));
            }
            if (botLeft != null) {
                output.putAll(botLeft.toImageParts(packed));
            }
            if (botRight != null) {
                output.putAll(botRight.toImageParts(packed));
            }
        }
        return output;
    }

    /**
     * Load a texture tree into memory
     *
     *
     * @return the texture data of the tree
     */
    public int[] loadImageTree() {
        int[] dest = new int[this.getDim() * this.getDim()];
        if (image == null) {
            if (this.getTopLeft() != null) {
                blitData(
                    0,
                    0,
                    this.getDim(),
                    this.getDim() / DIM_BASE,
                    this.getDim() / DIM_BASE,
                    this.getTopLeft().loadImageTree(),
                    dest
                );
            }
            if (this.getTopRight() != null) {
                blitData(
                    this.getDim() / DIM_BASE,
                    0,
                    this.getDim(),
                    this.getDim() / DIM_BASE,
                    this.getDim() / DIM_BASE,
                    this.getTopRight().loadImageTree(),
                    dest
                );
            }
            if (this.getBotLeft() != null) {
                blitData(
                    0,
                    this.getDim() / DIM_BASE,
                    this.getDim(),
                    this.getDim() / DIM_BASE,
                    this.getDim() / DIM_BASE,
                    this.getBotLeft().loadImageTree(),
                    dest
                );
            }
            if (this.getBotRight() != null) {
                blitData(
                    this.getDim() / DIM_BASE,
                    this.getDim() / DIM_BASE,
                    this.getDim(),
                    this.getDim() / DIM_BASE,
                    this.getDim() / DIM_BASE,
                    this.getBotRight().loadImageTree(),
                    dest
                );
            }
            return dest;
        } else {
            blitData(
                0,
                0,
                this.getDim(),
                (int) image.getShape().getValue(0),
                (int) image.getShape().getValue(1),
                image.getData(),
                dest
            );
            return dest;
        }
    }

    private void blitData(
        int x,
        int y,
        int width,
        int sourceWidth,
        int sourceHeight,
        int[] source,
        int[] dest
    ) {
        for (int i = 0; i < sourceHeight; i++) {
            for (int j = 0; j < sourceWidth; j++) {
                dest[x + j + ((y + i) * width)] = source[j + i * sourceWidth];
            }
        }
    }
}
