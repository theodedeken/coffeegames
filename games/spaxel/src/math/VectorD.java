package spaxel.math;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vector of arbitrary size of type double.
 */
public class VectorD {
    private static final int DEFAULT_DIM = 3;
    private int m = DEFAULT_DIM;
    private double[] vector;

    /**
     * Create a new vector of the given size with all elements zero.
     *
     * @param m the size of the vector
     */
    public VectorD(int m) {
        this.m = m;
        vector = new double[m];
    }

    /**
     * Create a new vector with the given elements.
     *
     * @param vector the elements of the vector
     */
    public VectorD(double[] vector) {
        m = vector.length;
        this.vector = vector;
    }

    /**
     * Create a new 2D vector.
     *
     * @param x the x value
     * @param y the y value
     */
    public VectorD(@JsonProperty("x") double x, @JsonProperty("y") double y) {
        this.vector = new double[] { x, y, 1 };
    }

    /**
     * Get a value of the vector.
     *
     * @param i the index of the value to get
     * 
     * @return the value at that index
     */
    public double getValue(int i) {
        return vector[i];
    }

    /**
     * Set a value of the vector.
     *
     * @param i     the index to set
     * @param value the value to set
     */
    public void setValue(int i, double value) {
        vector[i] = value;
    }

    /**
     * Calculate the dot product of this vector and the given vector.
     *
     * @param vec the other vector
     * 
     * @return the dot product
     */
    public double dotProduct(VectorD vec) {
        double sol = 0;
        for (int i = 0; i < m; i++) {
            sol += (vector[i] * vec.getValue(i));
        }
        return sol;
    }

    /**
     * Calculate the cross product of this vector and the given vector. NOTE: this
     * only uses the first two values of each vector.
     *
     * @param vec the other vector
     * 
     * @return the cross product
     */
    public double crossProduct(VectorD vec) {
        return vector[0] * vec.getValue(1) - vector[1] * vec.getValue(0);
    }

    /**
     * Multiply this vector with a value and return a new vector with the result.
     *
     * @param a the value to multiply with
     * 
     * @return the vector with the result
     */
    public VectorD multiplicate(double a) {
        VectorD sol = new VectorD(m);
        for (int i = 0; i < m; i++) {
            sol.setValue(i, vector[i] * a);
        }
        return sol;
    }

    /**
     * Add this vector to the given vector and return a new vector with the result.
     *
     * @param vec the vector to add to
     * 
     * @return the vector with the result
     */
    public VectorD sum(VectorD vec) {
        VectorD sol = new VectorD(m);
        for (int i = 0; i < m; i++) {
            sol.setValue(i, vector[i] + vec.getValue(i));
        }
        return sol;
    }

    /**
     * Subtract the given vector to this vector and return a new vector with the
     * result.
     *
     * @param vec the vector to subtract
     * 
     * @return the vector with the result
     */
    public VectorD diff(VectorD vec) {
        VectorD sol = new VectorD(m);
        for (int i = 0; i < m; i++) {
            sol.setValue(i, vector[i] - vec.getValue(i));
        }
        return sol;
    }

    /**
     * Calculate the normal vector. NOTE: only works with 2d vectors.
     *
     * @return the normal vector
     */
    public VectorD normal() {
        VectorD sol = new VectorD(m);
        sol.setValue(0, vector[1]);
        sol.setValue(1, -vector[0]);
        return sol;
    }

    /**
     * Converts the vector to a String.
     *
     * @return the vector string
     */
    public String toString() {
        List<String> parts = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            parts.add(String.valueOf(vector[i]));
        }
        return String.join(" ", parts);
    }

    /**
     * Calculate the length of this vector. NOTE: only works with 2d vectors.
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt((vector[0] * vector[0]) + (vector[1] * vector[1]));
    }

    /**
     * Calculate the angle of this vector. NOTE: only works with 2d vectors.
     *
     * @return the angle of the vector
     */
    public double angle() {
        return Math.atan2(vector[0], vector[1]);
    }

    /**
     * Copy this vector.
     *
     * @return a new vector with the same elements as this vector
     */
    public VectorD copy() {
        return new VectorD(vector.clone());
    }

    /**
     * Perform element wise division on this vector with another vector and return a
     * new vector with the result.
     *
     * @param vec the other vector
     * 
     * @return the result
     */
    public VectorD elementDiv(VectorD vec) {
        VectorD sol = new VectorD(m);
        for (int i = 0; i < m; i++) {
            sol.setValue(i, vector[i] / vec.getValue(i));
        }
        return sol;
    }

    /**
     * Perform element wise multiplication on this vector with another vector and
     * return a new vector with the result.
     *
     * @param vec the other vector
     * 
     * @return the result
     */
    public VectorD elementMult(VectorD vec) {
        VectorD sol = new VectorD(m);
        for (int i = 0; i < m; i++) {
            sol.setValue(i, vector[i] * vec.getValue(i));
        }
        return sol;
    }

}
