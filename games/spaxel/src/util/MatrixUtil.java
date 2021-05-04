package spaxel.util;

import voide.math.MatrixD;
import voide.math.VectorD;

/**
 * Provides utility functions for the creation of matrices
 */
public final class MatrixUtil {
	private static final int MATRIX_DIM = 3;
	private static final int PROJECTION_DIM = 4;
	private static final int PROJECTION_NUMERATOR = 2;

	private MatrixUtil() {

	}

	/**
	 * Create a matrix to execute a translation, rotation and scaling
	 * 
	 * @param coord the coordinates of the translation
	 * @param rot   the radian value of the rotation.
	 * @param scale the scaling in x and y direction
	 * 
	 * @return a {@link voide.math.MatrixD} that combines all these transformations
	 */
	public static MatrixD getTransformationMatrix(VectorD coord, double rot, VectorD scale) {
		return getRotationationMatrix(rot).multiplicate(getScaleMatrix(scale)).sum(getTranslationMatrix(coord));
	}

	/**
	 * Create a matrix to execute a translation and rotation
	 * 
	 * @param coord the coordinates of the translation
	 * @param rot   the radian value of the rotation
	 * 
	 * @return a {@link voide.math.MatrixD} that combines these transformations.
	 */
	public static MatrixD getTransRotationMatrix(VectorD coord, double rot) {
		return getRotationationMatrix(rot).sum(getTranslationMatrix(coord));
	}

	/**
	 * Create a matrix to execute a rotation
	 * 
	 * @param rot the radian value of the rotation
	 * 
	 * @return a {@link voide.math.MatrixD} that executes a rotation
	 */
	public static MatrixD getRotationationMatrix(double rot) {
		double sin = Math.sin(rot);
		double cos = Math.cos(rot);
		return new MatrixD(MATRIX_DIM, MATRIX_DIM, new double[] { cos, sin, 0, -sin, cos, 0, 0, 0, 1 });
	}

	/**
	 * Create a matrix to execute a scaling
	 * 
	 * @param scale the scaling in x and y direction
	 * 
	 * @return a {@link voide.math.MatrixD} that executes a scaling
	 */
	public static MatrixD getScaleMatrix(VectorD scale) {
		return new MatrixD(MATRIX_DIM, MATRIX_DIM,
				new double[] { scale.getValue(0), 0, 0, 0, scale.getValue(1), 0, 0, 0, 1 });
	}

	/**
	 * Create a matrix to execute a translation
	 * 
	 * @param coord the coordinates of the translation
	 * 
	 * @return a {@link voide.math.MatrixD} that executes a translation
	 */
	public static MatrixD getTranslationMatrix(VectorD coord) {
		return new MatrixD(MATRIX_DIM, MATRIX_DIM,
				new double[] { 0, 0, coord.getValue(0), 0, 0, coord.getValue(1), 0, 0, 0 });
	}

	/**
	 * Create an orthographic projection matrix
	 * 
	 * @param left   left side
	 * @param right  right side
	 * @param bottom bottom side
	 * @param top    top side
	 * @param near   near plane
	 * @param far    far plane
	 * 
	 * @return an orthograpic projection matrix
	 */
	public static MatrixD orthographic(double left, double right, double bottom, double top, double near, double far) {
		return new MatrixD(PROJECTION_DIM, PROJECTION_DIM,
				new double[] { PROJECTION_NUMERATOR / (right - left), 0, 0, -(right + left) / (right - left), 0,
						PROJECTION_NUMERATOR / (top - bottom), 0, -(top + bottom) / (top - bottom), 0, 0,
						-PROJECTION_NUMERATOR / (far - near), -(far + near) / (far - near), 0, 0, 0, 1 });
	}

}
