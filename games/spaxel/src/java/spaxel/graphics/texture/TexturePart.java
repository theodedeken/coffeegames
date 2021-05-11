package spaxel.graphics.texture;

import voide.math.VectorD;

/**
 * Represents a part of an image texture
 */
public class TexturePart extends Texture {
    private Texture spritesheet;
    private String sheetName;

    /**
     * Create a new TexturePart
     */
    public TexturePart() {
        super();
    }

    /**
     * Initialize the coordinates of this TexturePart using the data of the parent
     * spritesheet
     * 
     * @param spritesheet the parent spritesheet
     */
    public void initializeCoordinates(Texture spritesheet) {
        this.spritesheet = spritesheet;
        VectorD sheetDim = spritesheet.getTextureDim();
        VectorD sheetPos = spritesheet.getTextureCoord();

        textureCoord = sheetPos.sum(sheetDim.elementMult(pos.elementDiv(spritesheet.getDim())));
        textureDim = sheetDim.elementMult(dim.elementDiv(spritesheet.getDim()));
        spritesheetId = spritesheet.getSpriteSheetId();

        initCoordinates();
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public void setSpritesheet(Texture spritesheet) {
        this.spritesheet = spritesheet;
    }

    @Override
    public Texture getSpritesheet() {
        return spritesheet;
    }

    public String getSheetName() {
        return sheetName;
    }
}
