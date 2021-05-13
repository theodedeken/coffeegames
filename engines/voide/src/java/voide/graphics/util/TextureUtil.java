package voide.graphics.util;

import static org.lwjgl.opengl.GL11.*;
import voide.buffer.BufferUtils;

/**
 * Provides utility functions for the creation and packing of textures
 */
public final class TextureUtil {
    private TextureUtil() {

    }

    /**
     * Create a new GL texture and load the data into it
     * 
     * @param width  the width of the texture
     * @param height the height of the texture
     * @param data   the data of the texture
     * 
     * @return the id of the texture
     */
    public static int createGPUTexture(int width, int height, int[] data) {
        int id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE,
                BufferUtils.createIntBuffer(data));

        glBindTexture(GL_TEXTURE_2D, 0);
        return id;
    }
}
