package spaxel.graphics.animation;

import java.util.List;
import spaxel.graphics.buffer.RenderJob;

/**
 * Represents an animation
 */
public class Animation {
    private List<Animator> animators;

    /**
     * Creates a new animation
     */
    public Animation() {
        super();
    }

    /**
     * Return the RenderData for the animation at the given completion percentage
     * 
     * @param percentage the completion percentage of the animation
     * 
     * @return a RenderData object with the state of the animation
     */
    public RenderJob getDataAt(double percentage) {
        RenderJob data = new RenderJob();
        for (Animator anim : animators) {
            anim.animate(percentage, data);
        }
        return data;
    }

    /**
     * @return the animators
     */
    public List<Animator> getAnimators() {
        return animators;
    }

    /**
     * @param animators the animators to set
     */
    public void setAnimators(List<Animator> animators) {
        this.animators = animators;
    }
}
