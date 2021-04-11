package spaxel.math;

/**
 * Represents an axis through the origin with a direction vector
 */
public class Axis {
	private static final int AXIS_DIM = 2;

	private VectorD dirVec;

	/**
	 * Create a new empty axis
	 */
	public Axis() {
		dirVec = new VectorD(AXIS_DIM);
	}

	/**
	 * Initialize the direction vector of this axis as the difference of the two given vectors
	 * 
	 * @param vec1 the first vector of the axis
	 * @param vec2 the second vector of the axis
	 */
	public void initializeAxis(VectorD vec1, VectorD vec2) {
		dirVec = vec1.diff(vec2);
	}

	/**
	 * Initialize the direction vector of this axis as the normal of the difference of the two given
	 * vectors
	 * 
	 * @param vec1 the first vector of the axis
	 * @param vec2 the second vector of the axis
	 */
	public void initializeNormal(VectorD vec1, VectorD vec2) {
		dirVec = vec1.diff(vec2).normal();
	}

	/**
	 * Project the given vector onto this axis
	 * 
	 * @param vec the vector to project
	 * 
	 * @return the projected vector
	 */
	public VectorD project(VectorD vec) {
		double mul = dirVec.dotProduct(vec) / dirVec.dotProduct(dirVec);
		return dirVec.multiplicate(mul);
	}

	public String toString() {
		return dirVec.toString();
	}

}
