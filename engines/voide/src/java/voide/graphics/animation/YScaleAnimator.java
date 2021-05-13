package voide.graphics.animation;

import voide.render.RenderJob;

/**
 * The YScaleAnimator animates the y scale of the animation
 */
public class YScaleAnimator extends Animator {
    private double maxScale;
    private double minScale;

    /**
     * Create a new YScaleAnimator
     */
    public YScaleAnimator() {
        super(AnimatorType.Y_SCALE);
    }

    public void animate(double percentage, RenderJob data) {
        double scale = percentage * (maxScale - minScale);

        data.applyYScale(minScale + scale);
    }

    /**
     * @return the maxScale
     */
    public double getMaxScale() {
        return maxScale;
    }

    /**
     * @param maxScale the maxScale to set
     */
    public void setMaxScale(double maxScale) {
        this.maxScale = maxScale;
    }

    /**
     * @return the minScale
     */
    public double getMinScale() {
        return minScale;
    }

    /**
     * @param minScale the minScale to set
     */
    public void setMinScale(double minScale) {
        this.minScale = minScale;
    }

}
