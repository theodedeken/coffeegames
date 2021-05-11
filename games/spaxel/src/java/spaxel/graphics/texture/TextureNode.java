package spaxel.graphics.texture;

import voide.math.VectorD;

/**
 * Represents a node in the tree representing the sprites in the packed texture
 */
public class TextureNode {
    private static final int DIM_BASE = 2;
    private Texture texture;
    private int dim;
    private NodePlacement placement;
    private TextureNode parent;

    private TextureNode topLeft;
    private TextureNode topRight;
    private TextureNode botLeft;
    private TextureNode botRight;

    /**
     * Create a new leaf TextureNode
     * 
     * @param texture the leaf content
     */
    public TextureNode(Texture texture) {
        this.texture = texture;
        int maxDim = (int) Math.max(texture.getDim().getValue(0), texture.getDim().getValue(1));
        this.dim = DIM_BASE;
        while (dim < maxDim) {
            dim *= DIM_BASE;
        }
    }

    /**
     * Create a new TextureNode with the specified dimension
     * 
     * @param dim the dimension of the texturenode
     */
    public TextureNode(int dim) {
        this.dim = dim;
        this.placement = NodePlacement.TOP_LEFT;
    }

    public Texture getTexture() {
        return texture;
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
        TOP_LEFT, TOP_RIGHT, BOT_LEFT, BOT_RIGHT
    }

    public int getDim() {
        return dim;
    }

    public void setPlacement(NodePlacement placement) {
        this.placement = placement;
    }

    public void setParent(TextureNode parent) {
        this.parent = parent;
    }

    public TextureNode getTopLeft() {
        return topLeft;
    }

    public TextureNode getTopRight() {
        return topRight;
    }

    public TextureNode getBotLeft() {
        return botLeft;
    }

    public TextureNode getBotRight() {
        return botRight;
    }

    /**
     * @param topLeft the topLeft to set
     */
    public void setTopLeft(TextureNode topLeft) {
        this.topLeft = topLeft;
        topLeft.setPlacement(NodePlacement.TOP_LEFT);
        topLeft.setParent(this);
    }

    /**
     * @param topRight the topRight to set
     */
    public void setTopRight(TextureNode topRight) {
        this.topRight = topRight;
        topRight.setPlacement(NodePlacement.TOP_RIGHT);
        topRight.setParent(this);
    }

    /**
     * @param botLeft the botLeft to set
     */
    public void setBotLeft(TextureNode botLeft) {
        this.botLeft = botLeft;
        botLeft.setPlacement(NodePlacement.BOT_LEFT);
        botLeft.setParent(this);
    }

    /**
     * @param botRight the botRight to set
     */
    public void setBotRight(TextureNode botRight) {
        this.botRight = botRight;
        botRight.setPlacement(NodePlacement.BOT_RIGHT);
        botRight.setParent(this);
    }

    /**
     * Initialize the texture coordinates of all the leaf textures in this tree
     * 
     * @param packedTexture the packedTexture of this tree
     */
    public void initializeCoordinates(VectorD packedTextureDim, int spritesheetId) {
        if (texture != null) {
            texture.initializeCoordinates(this.getPos(), packedTextureDim, spritesheetId);
        } else {
            if (topLeft != null) {
                topLeft.initializeCoordinates(packedTextureDim, spritesheetId);
            }
            if (topRight != null) {
                topRight.initializeCoordinates(packedTextureDim, spritesheetId);
            }
            if (botLeft != null) {
                botLeft.initializeCoordinates(packedTextureDim, spritesheetId);
            }
            if (botRight != null) {
                botRight.initializeCoordinates(packedTextureDim, spritesheetId);
            }
        }
    }

    /**
     * Load a texture tree into memory
     * 
     * 
     * @return the texture data of the tree
     */
    public int[] loadTextureTree() {
        int[] dest = new int[this.getDim() * this.getDim()];
        if (this.getTexture() == null) {
            if (this.getTopLeft() != null) {
                blitData(0, 0, this.getDim(), this.getDim() / DIM_BASE, this.getDim() / DIM_BASE,
                        this.getTopLeft().loadTextureTree(), dest);
            }
            if (this.getTopRight() != null) {
                blitData(this.getDim() / DIM_BASE, 0, this.getDim(), this.getDim() / DIM_BASE, this.getDim() / DIM_BASE,
                        this.getTopRight().loadTextureTree(), dest);
            }
            if (this.getBotLeft() != null) {
                blitData(0, this.getDim() / DIM_BASE, this.getDim(), this.getDim() / DIM_BASE, this.getDim() / DIM_BASE,
                        this.getBotLeft().loadTextureTree(), dest);
            }
            if (this.getBotRight() != null) {
                blitData(this.getDim() / DIM_BASE, this.getDim() / DIM_BASE, this.getDim(), this.getDim() / DIM_BASE,
                        this.getDim() / DIM_BASE, this.getBotRight().loadTextureTree(), dest);
            }
            return dest;
        } else {
            Texture texture = this.getTexture();
            blitData(0, 0, this.getDim(), (int) texture.getDim().getValue(0), (int) texture.getDim().getValue(1),
                    texture.load(), dest);
            return dest;
        }
    }

    private void blitData(int x, int y, int width, int sourceWidth, int sourceHeight, int[] source, int[] dest) {
        for (int i = 0; i < sourceHeight; i++) {
            for (int j = 0; j < sourceWidth; j++) {
                dest[x + j + ((y + i) * width)] = source[j + i * sourceWidth];
            }
        }
    }

}
