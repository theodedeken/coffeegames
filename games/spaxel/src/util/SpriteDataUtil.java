package spaxel.util;

import spaxel.graphics.texture.TexturePart;
import spaxel.graphics.texture.Texture;
import spaxel.math.VectorD;

/**
 * Created by theo on 15/07/17.
 */
public final class SpriteDataUtil {

    private static SpaxelRandom random = new SpaxelRandom();

    private SpriteDataUtil() {

    }

    /**
     * Return a random part of a sprite with the specified dimensions.
     * 
     * @param spriteData The sprite to generate the part from
     * @param width      The width of the generated part.
     * @param height     The height of the generated part.
     * 
     * @return A new {@link code.graphics.texture.TexturePart} object containing with the properties
     *         of the generated part.
     */
    public static TexturePart getRandomPart(Texture spriteData, int width, int height) {
        int x = random.nextInt((int) spriteData.getDim().getValue(0) - width);
        int y = random.nextInt((int) spriteData.getDim().getValue(1) - height);

        VectorD pos = spriteData.getPos().sum(new VectorD(x, y));

        TexturePart part = new TexturePart();
        part.setDim(new VectorD(width, height));
        part.setPos(pos);
        part.initializeCoordinates(spriteData.getSpritesheet());
        return part;
    }
}
