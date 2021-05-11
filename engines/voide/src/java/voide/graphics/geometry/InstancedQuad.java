package voide.graphics.geometry;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL33.glVertexAttribDivisor;

/**
 * Represents a quad that can be used in instanced rendering
 * 
 * Created by theod on 16-7-2017.
 */
public class InstancedQuad extends Quad {
    private int texOffsetScale;
    private int transScale;
    private int sinCosAlphaColor;

    private static final int TRSC_ATTRIB_INDEX = 2;
    private static final int TEX_ATTRIB_INDEX = 3;
    private static final int SINCOS_ATTRIB_INDEX = 4;

    private static final int ATTRIB_SIZE = 4;
    private static final int BYTES_INA_FLOAT = 4;

    /**
     * Create a new InstancedQuad
     */
    public InstancedQuad() {
        super();

        transScale = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, transScale);
        glEnableVertexAttribArray(TRSC_ATTRIB_INDEX);
        glVertexAttribPointer(TRSC_ATTRIB_INDEX, ATTRIB_SIZE, GL_FLOAT, false,
                ATTRIB_SIZE * BYTES_INA_FLOAT, 0);
        glVertexAttribDivisor(TRSC_ATTRIB_INDEX, 1);

        texOffsetScale = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, texOffsetScale);
        glEnableVertexAttribArray(TEX_ATTRIB_INDEX);
        glVertexAttribPointer(TEX_ATTRIB_INDEX, ATTRIB_SIZE, GL_FLOAT, false,
                ATTRIB_SIZE * BYTES_INA_FLOAT, 0);
        glVertexAttribDivisor(TEX_ATTRIB_INDEX, 1);

        sinCosAlphaColor = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, sinCosAlphaColor);
        glEnableVertexAttribArray(SINCOS_ATTRIB_INDEX);
        glVertexAttribPointer(SINCOS_ATTRIB_INDEX, ATTRIB_SIZE, GL_FLOAT, false,
                ATTRIB_SIZE * BYTES_INA_FLOAT, 0);
        glVertexAttribDivisor(SINCOS_ATTRIB_INDEX, 1);
    }

    public int getTexOffsetScale() {
        return texOffsetScale;
    }

    public int getTransScale() {
        return transScale;
    }

    public int getSinCosAlphaColor() {
        return sinCosAlphaColor;
    }

}
