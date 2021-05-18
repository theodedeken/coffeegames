package voide.graphics.load;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import voide.debug.RepresentationBuilder;
import voide.graphics.renderable.Texture;
import voide.graphics.util.TextureUtil;
import voide.math.VectorD;
import voide.resources.Resource;

public class Image implements Resource {
    private static final Logger LOGGER = Logger.getLogger(Image.class.getName());
    // bytes
    private static final int ONE_BYTE = 8;
    private static final int TWO_BYTES = ONE_BYTE * 2;
    private static final int THREE_BYTES = ONE_BYTE * 3;

    private String path;
    protected VectorD shape;
    private int[] data;

    public Image() {

    }

    public Image(VectorD shape, int[] data) {
        this.shape = shape;
        this.data = data;
    }

    public void initialize() {
        if (path != null) {
            int width = (int) shape.getValue(0);
            int height = (int) shape.getValue(1);
            int[] pixels = new int[width * height];
            LOGGER.log(Level.INFO, "loading {0}", path);
            try {
                BufferedImage image = ImageIO.read(Image.class.getResource(path));
                image.getRGB(0, 0, width, height, pixels, 0, width);
                LOGGER.log(Level.INFO, "Initialized " + repr());
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }

            data = new int[width * height];
            for (int i = 0; i < width * height; i++) {
                int a = (pixels[i] & 0xff000000) >> THREE_BYTES;
                int r = (pixels[i] & 0xff0000) >> TWO_BYTES;
                int g = (pixels[i] & 0xff00) >> ONE_BYTE;
                int b = (pixels[i] & 0xff);

                data[i] = a << THREE_BYTES | b << TWO_BYTES | g << ONE_BYTE | r;
            }
        }
    }

    public String repr() {
        return String.format("Image { %dx%d, %s }", (int) shape.getValue(0), (int) shape.getValue(1), path);
    }

    public String fullRepr() {
        return new RepresentationBuilder(getClass().getName()).field("path", path).field("shape", shape.repr()).build();
    }

    public Texture toTexture() {
        int id = TextureUtil.createGPUTexture((int) shape.getValue(0), (int) shape.getValue(1), data);
        return new Texture(shape, id, getTextureOffset(), getTextureShape());
    }

    public VectorD getShape() {
        return shape;
    }

    public void setShape(VectorD shape) {
        this.shape = shape;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public VectorD getTextureOffset() {
        return new VectorD(0, 0);
    }

    public VectorD getTextureShape() {
        return new VectorD(1, 1);
    }

}
