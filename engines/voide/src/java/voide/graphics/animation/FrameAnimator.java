package voide.graphics.animation;

import voide.graphics.renderable.Renderable;
import voide.render.RenderJob;

import java.util.Map;

/**
 * The FrameAnimator animates the current sprite of the animation
 */
public class FrameAnimator extends Animator {
    private String spriteBase;
    private int numFrames;
    // TODO init somehow
    private Map<String, Renderable> renderables;

    /**
     * Create a new FrameAnimator
     */
    public FrameAnimator() {
        super(AnimatorType.FRAME);
    }

    public void animate(double percentage, RenderJob data) {
        Renderable frame;
        if (numFrames != 1) {
            int frameNumber = (int) Math.round(percentage * (numFrames - 1));
            frame = renderables.get(spriteBase + "_" + frameNumber);
        } else {
            frame = renderables.get(spriteBase);
        }
        frame.apply(data);
    }

    /**
     * @return the spriteBase
     */
    public String getSpriteBase() {
        return spriteBase;
    }

    /**
     * @param spriteBase the spriteBase to set
     */
    public void setSpriteBase(String spriteBase) {
        this.spriteBase = spriteBase;
    }

    /**
     * @return the numFrames
     */
    public int getNumFrames() {
        return numFrames;
    }

    /**
     * @param numFrames the numFrames to set
     */
    public void setNumFrames(int numFrames) {
        this.numFrames = numFrames;
    }
}
