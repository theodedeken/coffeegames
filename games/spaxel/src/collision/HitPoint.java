package spaxel.collision;

import spaxel.math.MatrixD;
import spaxel.math.VectorD;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a hitpoint of a hitshape
 */
public class HitPoint {
	private static final int HITPOINT_DIM = 3;
	private VectorD vector;

	/**
	 * Create a new HitPoint with zero initialized elements
	 */
	public HitPoint() {
		vector = new VectorD(HITPOINT_DIM);
	}

	/**
	 * Create a new hitpoint from a vector
	 * 
	 * @param vector the hitpoint data
	 */
	public HitPoint(VectorD vector) {
		this.vector = vector;
	}

	/**
	 * Creates a new hitpoint with x and y offset
	 * 
	 * @param xOffset the x offset
	 * @param yOffset the y offset
	 */
	@JsonCreator
	public HitPoint(@JsonProperty("xOffset") double xOffset,
			@JsonProperty("yOffset") double yOffset) {
		this.vector = new VectorD(new double[] {xOffset, yOffset, 1});
	}

	/**
	 * Set the vector
	 * 
	 * @param vector the vector to set
	 */
	public void setVector(VectorD vector) {
		this.vector = vector;
	}

	public VectorD getVector() {
		return vector;
	}

	/**
	 * Transform the hitpoint with the given matrix and return a new hitpoint with the updated
	 * elements
	 * 
	 * @param updateMatrix the transformation matrix
	 * 
	 * @return the transformed hitpoint
	 */
	public HitPoint update(MatrixD updateMatrix) {
		HitPoint updated = new HitPoint();
		VectorD v = updateMatrix.multiplicate(vector);
		updated.setVector(v);
		return updated;
	}

	public String toString() {
		return vector.toString();
	}
}
