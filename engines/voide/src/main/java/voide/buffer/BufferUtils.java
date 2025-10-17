package voide.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Provides utility functions for the creation of buffers
 */
public final class BufferUtils {

    private static final int BYTES_IN_FLOAT = 4;
    private static final int BYTES_IN_INT = 4;

    private BufferUtils() {}

    /**
     * Create a ByteBuffer and fill it with the given array of bytes
     *
     * @param array the content fo the bytebuffer
     *
     * @return A ByteBuffer with the array data
     */
    public static ByteBuffer createByteBuffer(byte[] array) {
        ByteBuffer result = ByteBuffer
            .allocateDirect(array.length)
            .order(ByteOrder.nativeOrder());
        result.put(array).flip();
        return result;
    }

    /**
     * Create a FloatBuffer and fill it with the given array of doubles. Will first
     * convert the doubles to floats.
     *
     * @param array the contents of the floatbuffer
     *
     * @return A FloatBuffer with the array data
     */
    public static FloatBuffer createFloatBuffer(double[] array) {
        int n = array.length;
        float[] ret = new float[n];
        for (int i = 0; i < n; i++) {
            ret[i] = (float) array[i];
        }
        return createFloatBuffer(ret);
    }

    /**
     * Create a FloatBuffer and fill it with the given array of floats
     *
     * @param array the contents of the floatbuffer
     *
     * @return A FloatBuffer with the array data
     */
    public static FloatBuffer createFloatBuffer(float[] array) {
        FloatBuffer result = ByteBuffer
            .allocateDirect(array.length * BYTES_IN_FLOAT)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer();
        result.put(array).flip();
        return result;
    }

    /**
     * Allocate a FloatBuffer with the given size
     *
     * @param size the size of the floatbuffer in terms of amount of elements
     *
     * @return A new FloatBuffer object with the given size
     */
    public static FloatBuffer allocateFloatBuffer(int size) {
        return ByteBuffer
            .allocateDirect(size * BYTES_IN_FLOAT)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer();
    }

    /**
     * Create an IntBuffer and fill it with the contents of the given array of ints
     *
     * @param array the contents of the intbuffer
     *
     * @return An IntBuffer with the array data
     */
    public static IntBuffer createIntBuffer(int[] array) {
        IntBuffer result = ByteBuffer
            .allocateDirect(array.length * BYTES_IN_INT)
            .order(ByteOrder.nativeOrder())
            .asIntBuffer();
        result.put(array).flip();
        return result;
    }
}
