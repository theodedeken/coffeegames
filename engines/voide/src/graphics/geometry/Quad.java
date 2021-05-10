package voide.graphics.geometry;

import voide.buffer.BufferUtils;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by theod on 16-7-2017.
 */
public class Quad {
    protected int vao;

    private int vbo;
    private int ibo;
    private int tbo;
    private static final float[] VERTICES = new float[] { -.5F, -.5F, 0, -.5F, .5F, 0, .5F, .5F, 0, .5F, -.5F, 0 };

    private static final byte[] INDICES = new byte[] { 0, 1, 3, 3, 1, 2 };

    private static final float[] TEX_COORDS = new float[] { 0, 1, 0, 0, 1, 0, 1, 1 };

    private static final int VERTEX_DIM = 3;
    private static final int TEXTURE_DIM = 2;

    /**
     * Create a new Quad
     */
    public Quad() {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(VERTICES), GL_STATIC_DRAW);
        glVertexAttribPointer(0, VERTEX_DIM, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);

        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(TEX_COORDS), GL_STATIC_DRAW);
        glVertexAttribPointer(1, TEXTURE_DIM, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(INDICES), GL_STATIC_DRAW);
    }

    /**
     * Bind this Quad on the GPU
     */
    public void bind() {
        glBindVertexArray(vao);
    }

    public int getVao() {
        return vao;
    }

    public int getVbo() {
        return vbo;
    }

    public int getIbo() {
        return ibo;
    }

    public int getTbo() {
        return tbo;
    }

    public int getVertexCount() {
        return INDICES.length;
    }
}
