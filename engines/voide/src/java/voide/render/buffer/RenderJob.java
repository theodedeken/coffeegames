
package voide.render.buffer;

import voide.math.VectorD;

/**
 * Represents all data attributes for one render job
 * 
 * Created by theod on 22/10/2016.
 */
public class RenderJob {
    private static final int ATTRIB_DIM = 4;
    private static final int SCALE_OFFSET = 2;
    private static final int ALPHA_INDEX = 2;
    private static final int COLOR_INDEX = 3;
    private float[] texOffset;
    private float[] trSc;
    private float[] sinCos;
    private double rot;

    private int spriteSheetID;

    /**
     * Create a new RenderData
     */
    public RenderJob() {
        trSc = new float[] { 0.0F, 0.0F, 1.0F, 1.0F };
        sinCos = new float[ATTRIB_DIM];
        texOffset = new float[ATTRIB_DIM];
        sinCos[ALPHA_INDEX] = 1;
    }

    public float[] getTrSc() {
        return trSc;
    }

    public float[] getSinCos() {
        return sinCos;
    }

    public float[] getTexOffset() {
        return texOffset;
    }

    public void setTexOffset(VectorD textureOffset, VectorD textureShape) {
        this.texOffset = new float[] { (float) textureOffset.getValue(0), (float) textureOffset.getValue(1),
                (float) textureShape.getValue(0), (float) textureShape.getValue(1) };
    }

    public int getSpriteSheetID() {
        return spriteSheetID;
    }

    public void setSpriteSheetID(int spriteSheetID) {
        this.spriteSheetID = spriteSheetID;
    }

    /**
     * Applies a translation
     * 
     * @param translation the translation to apply
     */
    public void applyTranslation(VectorD translation) {
        applyXTranslation(translation.getValue(0));
        applyYTranslation(translation.getValue(1));
    }

    /**
     * Applies a translation on the x axis
     * 
     * @param xTrans the translation to apply
     */
    public void applyXTranslation(double xTrans) {
        trSc[0] += (float) xTrans;
    }

    /**
     * Applies a translation to the y axis
     * 
     * @param yTrans the translation to apply
     */
    public void applyYTranslation(double yTrans) {
        trSc[1] += (float) yTrans;
    }

    /**
     * Applies a scaling to the x axis
     * 
     * @param xScale the scaling to apply
     */
    public void applyXScale(double xScale) {
        trSc[SCALE_OFFSET] *= (float) xScale;
    }

    /**
     * Applies a scaling to the y axis
     * 
     * @param yScale the scaling to apply
     */
    public void applyYScale(double yScale) {
        trSc[SCALE_OFFSET + 1] *= (float) yScale;
    }

    /**
     * Applies a scaling vector
     * 
     * @param scale the scaling vector to apply
     */
    public void applyScale(VectorD scale) {
        applyXScale(scale.getValue(0));
        applyYScale(scale.getValue(1));
    }

    /**
     * Applies a scaling to both axis
     * 
     * @param scale the scaling to apply
     */
    public void applyScale(double scale) {
        applyXScale(scale);
        applyYScale(scale);
    }

    /**
     * Apply a rotation
     * 
     * @param rotChange the rotation change
     */
    public void applyRot(double rotChange) {
        rot += rotChange;
        sinCos[0] = (float) Math.sin(rot);
        sinCos[1] = (float) Math.cos(rot);
    }

    /**
     * Applies an alpha operation
     * 
     * @param alpha the alpha to apply
     */
    public void applyAlpha(double alpha) {
        sinCos[ALPHA_INDEX] *= (float) alpha;
    }

    public void setColor(int color) {
        sinCos[COLOR_INDEX] = color;
    }
}
