package spaxel.graphics.texture;

import voide.graphics.renderable.Renderable;
import voide.math.VectorD;
import voide.render.RenderJob;

import java.util.logging.Level;
import java.util.logging.Logger;
import spaxel.Constants;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Represents an image texture
 */
public class Texture extends Renderable {
    private static final Logger LOGGER = Logger.getLogger(Texture.class.getName());
    protected float[] coordinates;
    protected VectorD textureDim;
    protected VectorD textureCoord;
    protected VectorD pos;
    protected int spritesheetId;

    private String path;

    /**
     * Create a new Texture
     */
    public Texture() {
        super();
    }

    /**
     * Initialize the coordinates of this Texture using the data of the parent node
     * 
     * @param parent the parent node of this texture in the texture tree
     */
    public void initializeCoordinates(VectorD pos, VectorD ptextureDim, int ptextureId) {
        VectorD sheetPixelDim = getDim();

        spritesheetId = ptextureId;
        textureDim = sheetPixelDim.elementDiv(ptextureDim);
        textureCoord = pos.elementDiv(ptextureDim);

        initCoordinates();
    }

    protected void initCoordinates() {
        coordinates = new float[] { (float) textureCoord.getValue(0), (float) textureCoord.getValue(1),
                (float) textureDim.getValue(0), (float) textureDim.getValue(1) };

    }

    /**
     * @param pos the pos to set
     */
    public void setPos(VectorD pos) {
        this.pos = pos;
    }

    public VectorD getPos() {
        return pos;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public VectorD getTextureDim() {
        return textureDim;
    }

    public VectorD getTextureCoord() {
        return textureCoord;
    }

    public float[] getPositioninates() {
        return coordinates;
    }

    public void apply(RenderJob data) {
        data.setTexOffset(coordinates);
        data.setSpriteSheetID(spritesheetId);
        data.applyScale(dim);
    }

    public Texture getSpritesheet() {
        return this;
    }

    /**
     * Load a texture into memory
     * 
     * @param texture the texture to load
     * 
     * @return the texture data
     */
    public int[] load() {
        int width = (int) this.getDim().getValue(0);
        int height = (int) this.getDim().getValue(1);
        String path = this.getPath();
        int[] pixels = new int[width * height];
        LOGGER.log(Level.INFO, "loading {0}", path);
        try {
            BufferedImage image = ImageIO.read(Texture.class.getResource(path));
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        int[] data = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            int a = (pixels[i] & 0xff000000) >> Constants.THREE_BYTES;
            int r = (pixels[i] & 0xff0000) >> Constants.TWO_BYTES;
            int g = (pixels[i] & 0xff00) >> Constants.ONE_BYTE;
            int b = (pixels[i] & 0xff);

            data[i] = a << Constants.THREE_BYTES | b << Constants.TWO_BYTES | g << Constants.ONE_BYTE | r;
        }
        return data;
    }

    public int getSpriteSheetId() {
        return spritesheetId;
    }
}
