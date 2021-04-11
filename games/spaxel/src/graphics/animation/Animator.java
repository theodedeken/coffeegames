package spaxel.graphics.animation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import spaxel.graphics.buffer.RenderJob;

/**
 * Abstract superclass for all Animators. An animator is an object that animates a certain property
 * in the animation.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",
        visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = FrameAnimator.class, name = "FRAME"),
        @JsonSubTypes.Type(value = XScaleAnimator.class, name = "X_SCALE"),
        @JsonSubTypes.Type(value = YScaleAnimator.class, name = "Y_SCALE"),
        @JsonSubTypes.Type(value = XPosAnimator.class, name = "X_POS"),
        @JsonSubTypes.Type(value = YPosAnimator.class, name = "Y_POS")})
public abstract class Animator {
    private AnimatorType type;

    /**
     * Create a new Animator instance of a certain type
     * 
     * @param type the type of the animator
     */
    public Animator(AnimatorType type) {
        this.type = type;
    }

    /**
     * Set the data of the given RenderData for the state of the animated property at the completion
     * percentage
     * 
     * @param percentage the completion percentage
     * @param data       the renderdata
     */
    public abstract void animate(double percentage, RenderJob data);

    /**
     * @return the type
     */
    public AnimatorType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AnimatorType type) {
        this.type = type;
    }


}
