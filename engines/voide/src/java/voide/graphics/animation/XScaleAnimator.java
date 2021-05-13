package voide.graphics.animation;

import voide.render.RenderJob;

/**
 * The XScaleAnimator animates the x scale of the animation
 */
public class XScaleAnimator extends Animator {
    private double maxScale;
    private double minScale;

    /**
     * Create a new XScaleAnimator
     */
    public XScaleAnimator() {
        super(AnimatorType.X_SCALE);
    }

    public void animate(double percentage, RenderJob data) {
        double scale = percentage * (maxScale - minScale);

        data.applyXScale(minScale + scale);
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
