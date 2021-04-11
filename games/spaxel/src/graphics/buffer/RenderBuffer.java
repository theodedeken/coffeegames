package spaxel.graphics.buffer;

import spaxel.util.BufferUtils;
import java.nio.FloatBuffer;
import java.util.List;

/**
 * Respresents the Buffer data to be sent to the gpu as part of the rendering of instanced elements
 */
public class RenderBuffer {
    private static final int RENDERDATA_ELEMENTS = 4;

    private FloatBuffer trscBuffer;
    private FloatBuffer sinCosBuffer;
    private FloatBuffer texOffsetBuffer;

    private int size;

    /**
     * Create a new RenderBuffer with the given RenderData
     * 
     * @param rdata list with the data for all the instances to render
     */
    public RenderBuffer(List<RenderJob> rdata) {
        size = rdata.size();
        trscBuffer = BufferUtils.allocateFloatBuffer(size * RENDERDATA_ELEMENTS);
        sinCosBuffer = BufferUtils.allocateFloatBuffer(size * RENDERDATA_ELEMENTS);
        texOffsetBuffer = BufferUtils.allocateFloatBuffer(size * RENDERDATA_ELEMENTS);
        for (RenderJob r : rdata) {
            trscBuffer.put(r.getTrSc());
            sinCosBuffer.put(r.getSinCos());
            texOffsetBuffer.put(r.getTexOffset());
        }
        trscBuffer.flip();
        sinCosBuffer.flip();
        texOffsetBuffer.flip();

    }

    public FloatBuffer getTrscBuffer() {
        return trscBuffer;
    }

    public FloatBuffer getSinCosBuffer() {
        return sinCosBuffer;
    }

    public FloatBuffer getTexOffsetBuffer() {
        return texOffsetBuffer;
    }

    /**
     * return the size of the buffer
     * 
     * @return the size
     */
    public int size() {
        return size;
    }

}
