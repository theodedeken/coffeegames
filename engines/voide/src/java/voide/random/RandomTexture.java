package voide.random;

import voide.graphics.renderable.Texture;
import voide.math.VectorD;

/**
 * Created by theo on 15/07/17.
 */
public final class RandomTexture {

    private static VoideRandom random = new VoideRandom();

    private RandomTexture() {}

    /**
     * Return a random part of a sprite with the specified dimensions.
     *
     * @param spriteData The sprite to generate the part from
     * @param width      The width of the generated part.
     * @param height     The height of the generated part.
     *
     * @return A new {@link code.graphics.texture.Texture} object containing with
     *         the properties of the generated part.
     */
    public static Texture getRandomPart(
        Texture spriteData,
        int width,
        int height
    ) {
        int x = random.nextInt((int) spriteData.getShape().getValue(0) - width);
        int y = random.nextInt(
            (int) spriteData.getShape().getValue(1) - height
        );

        VectorD textureOffset = spriteData
            .getTextureOffset()
            .sum(new VectorD(x, y).elementDiv(spriteData.getTextureShape()));
        VectorD textureShape = new VectorD(width, height)
            .elementDiv(spriteData.getTextureShape());

        Texture part = new Texture(
            new VectorD(width, height),
            spriteData.getTextureId(),
            textureOffset,
            textureShape
        );
        // TODO double check if this is still working
        return part;
    }
}
