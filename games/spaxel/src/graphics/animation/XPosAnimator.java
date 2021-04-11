package spaxel.graphics.animation;

import spaxel.graphics.buffer.RenderJob;

/**
 * The XPosAnimator animates the x position of the animation
 */
public class XPosAnimator extends Animator {
    private double maxPos;
    private double minPos;

    /**
     * Create a new XPosAnimator
     */
    public XPosAnimator() {
        super(AnimatorType.X_POS);
    }

    public void animate(double percentage, RenderJob data) {
        double pos = percentage * (maxPos - minPos);

        data.applyXTranslation(minPos + pos);
    }

    /**
     * @return the maxPos
     */
    public double getMaxPos() {
        return maxPos;
    }

    /**
     * @param maxPos the maxPos to set
     */
    public void setMaxPos(double maxPos) {
        this.maxPos = maxPos;
    }

    /**
     * @return the minPos
     */
    public double getMinPos() {
        return minPos;
    }

    /**
     * @param minPos the minPos to set
     */
    public void setMinPos(double minPos) {
        this.minPos = minPos;
    }
}
