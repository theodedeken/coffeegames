package voide.render.buffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.glFramebufferTexture;

import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL30;

/**
 * Frame Buffer object for rendering data offscreen
 *
 * Created by theod on 16-7-2017.
 */
public class FBO {

    private int fboID;
    private int texture;
    private int depthBuffer;
    private int bufferWidth;
    private int bufferHeight;

    /**
     * Create a new FBO
     */
    public FBO(int bufferWidth, int bufferHeight) {
        this.bufferWidth = bufferWidth;
        this.bufferHeight = bufferHeight;
        fboID = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, fboID);
        glDrawBuffer(GL_COLOR_ATTACHMENT0);
        texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        glTexImage2D(
            GL_TEXTURE_2D,
            0,
            GL_RGB,
            bufferWidth,
            bufferHeight,
            0,
            GL_RGB,
            GL_UNSIGNED_BYTE,
            (ByteBuffer) null
        );
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glFramebufferTexture(
            GL30.GL_FRAMEBUFFER,
            GL30.GL_COLOR_ATTACHMENT0,
            texture,
            0
        );
        glBindTexture(GL_TEXTURE_2D, 0);

        depthBuffer = glGenRenderbuffers();
        glBindRenderbuffer(GL30.GL_RENDERBUFFER, depthBuffer);
        glRenderbufferStorage(
            GL30.GL_RENDERBUFFER,
            GL_DEPTH_COMPONENT,
            bufferWidth,
            bufferHeight
        );
        GL30.glFramebufferRenderbuffer(
            GL30.GL_FRAMEBUFFER,
            GL30.GL_DEPTH_ATTACHMENT,
            GL30.GL_RENDERBUFFER,
            depthBuffer
        );

        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    /**
     * Bind this buffer
     */
    public void bindBuffer() {
        glBindTexture(GL_TEXTURE_2D, 0);
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fboID);
        glViewport(0, 0, this.bufferWidth, this.bufferHeight);
    }

    /**
     * Unbind this buffer
     */
    public void unbindBuffer() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glViewport(0, 0, this.bufferWidth, this.bufferHeight);
    }

    /**
     * Bind the buffer texture
     */
    public void bindTexture() {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    /**
     * Unbind the buffer texture
     */
    public void unbindTexture() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    /**
     * Bind the depthbuffer
     */
    public void bindDepth() {
        glBindRenderbuffer(GL_RENDERBUFFER, depthBuffer);
    }

    /**
     * Unbind the depthbuffer
     */
    public void unbindDepth() {
        glBindRenderbuffer(GL_RENDERBUFFER, 0);
    }
}
