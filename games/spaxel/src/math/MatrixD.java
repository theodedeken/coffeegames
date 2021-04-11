package spaxel.math;

import spaxel.util.BufferUtils;
import java.nio.FloatBuffer;

/**
 * Represents a matrix with elements of type double with arbitrary size
 */
public class MatrixD {
	private int m;
	private int n;
	private double[] matrix;

	/**
	 * Create a matrix with all elements zero with the given dimensions
	 * 
	 * @param m the number of columns in the matrix
	 * @param n the number of rows in the matrix
	 */
	public MatrixD(int m, int n) {
		this.m = m;
		this.n = n;
		matrix = new double[m * n];
	}

	/**
	 * Create a matrix with the given elements and dimensions
	 * 
	 * @param m      the number of columns in the matrix
	 * @param n      the number of rows in the matrix
	 * @param matrix the elements of the matrix
	 */
	public MatrixD(int m, int n, double[] matrix) {
		this.m = m;
		this.n = n;
		this.matrix = matrix;
	}

	/**
	 * Set a value of the matrix
	 * 
	 * @param x     the row index
	 * @param y     the column index
	 * @param value the value to set
	 */
	public void setValue(int x, int y, double value) {
		matrix[x * n + y] = value;
	}

	/**
	 * Get a value of the matrix
	 * 
	 * @param x the row index
	 * @param y the column index
	 * 
	 * @return the value at this position
	 */
	public double getValue(int x, int y) {
		return matrix[x * n + y];
	}

	public void setMatrix(double[] matrix) {
		this.matrix = matrix;
	}

	public double[] getMatrix() {
		return matrix;
	}

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}

	/**
	 * Perform a matrix multiplication with the given matrix and return a new matrix with the result
	 * 
	 * @param mat the matrix to multiplicate with
	 * 
	 * @return the result of the multiplication
	 */
	public MatrixD multiplicate(MatrixD mat) {
		MatrixD sol = new MatrixD(m, mat.getN());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < mat.getN(); j++) {
				double sum = 0;
				for (int l = 0; l < n; l++) {
					sum += (matrix[i * n + l] * mat.getValue(l, j));
				}
				sol.setValue(i, j, sum);
			}
		}
		return sol;
	}

	/**
	 * Multiplicate the matrix with a vector and return a new vector with the result
	 * 
	 * @param vec the vector to multiplicate with
	 * 
	 * @return the result of the multiplication
	 */
	public VectorD multiplicate(VectorD vec) {
		VectorD sol = new VectorD(n);
		for (int i = 0; i < m; i++) {
			double sum = 0;
			for (int l = 0; l < n; l++) {
				sum += (matrix[i * n + l] * vec.getValue(l));
			}
			sol.setValue(i, sum);
		}
		return sol;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result.append(matrix[i * n + j] + " ");
			}
			result.append("\n");
		}
		return result.toString();
	}

	/**
	 * Convert the contents of this matrix to a FloatBuffer
	 * 
	 * @return a floatbuffer with the elements of the matrix
	 */
	public FloatBuffer toFloatBuffer() {
		return BufferUtils.createFloatBuffer(matrix);
	}

	/**
	 * Multiply all values of the matrix with the given value an return a new matrix with the result
	 * 
	 * @param value the value to multiply with
	 * 
	 * @return the resulting matrix
	 */
	public MatrixD multiply(double value) {
		double[] sol = new double[m * n];
		for (int i = 0; i < m * n; i++) {
			sol[i] = matrix[i] * value;
		}
		return new MatrixD(m, n, sol);
	}

	/**
	 * Transpose this matrix and return a new matrix with the result
	 * 
	 * @return the transposed matrix
	 */
	public MatrixD transpose() {
		double[] sol = new double[m * n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sol[i * n + j] = matrix[i + j * m];
			}
		}
		return new MatrixD(n, m, sol);
	}

	/**
	 * Add this matrix to another matrix and return a new matrix with the result
	 * 
	 * @param other the matrix to add to this matrix
	 * 
	 * @return the resulting matrix
	 */
	public MatrixD sum(MatrixD other) {
		double[] sol = new double[m * n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sol[i * n + j] = matrix[i * n + j] + other.getValue(i, j);
			}
		}
		return new MatrixD(m, n, sol);
	}

}
