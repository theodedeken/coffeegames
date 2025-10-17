package voide.graphics.animation;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import voide.debug.RepresentationBuilder;
import voide.render.buffer.RenderJob;
import voide.resources.Resource;

/**
 * Represents an animation
 */
public class Animation implements Resource {

    private static final Logger LOGGER = Logger.getLogger(
        Animation.class.getName()
    );
    private List<Animator> animators;

    /**
     * Creates a new animation
     */
    public Animation() {
        super();
    }

    public void initialize() {
        LOGGER.log(Level.INFO, "Initialized " + repr());
    }

    public String repr() {
        return String.format("Animation { %s animators }", animators.size());
    }

    public String fullRepr() {
        return new RepresentationBuilder(getClass().getName())
            .field("animators", animators)
            .build();
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
