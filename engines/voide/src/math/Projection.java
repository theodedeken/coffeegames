/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package voide.math;

/**
 * Represents a projection of vectors onto an axis, only keeps the minimum and
 * maximum points
 */
public class Projection {
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private boolean empty;

    /**
     * Create a new projection using a vector
     * 
     * @param vec the vector to initialize the bounds with
     */
    public Projection(VectorD vec) {
        xMin = vec.getValue(0);
        xMax = vec.getValue(0);
        yMin = vec.getValue(1);
        yMax = vec.getValue(1);
        empty = false;
    }

    /**
     * Create an empty projection
     */
    public Projection() {
        empty = true;
    }

    /**
     * Adds a vector to this projection and updates the bounds where necessary
     * 
     * @param vec the vector to add
     */
    public void addVector(VectorD vec) {
        if (empty) {
            xMin = vec.getValue(0);
            xMax = vec.getValue(0);
            yMin = vec.getValue(1);
            yMax = vec.getValue(1);
            empty = false;
        } else {
            double x = vec.getValue(0);
            double y = vec.getValue(1);
            if (x < xMin) {
                xMin = x;
            }
            if (x > xMax) {
                xMax = x;
            }
            if (y < yMin) {
                yMin = y;
            }
            if (y > yMax) {
                yMax = y;
            }
        }
    }

    public double getXMin() {
        return xMin;
    }

    public double getXMax() {
        return xMax;
    }

    public double getYMin() {
        return yMin;
    }

    public double getYMax() {
        return yMax;
    }

    /**
     * Checks if this projection overlaps with the given projection
     * 
     * @param p the projection to check
     * 
     * @return true if there is overlap
     */
    public boolean overlap(Projection p) {
        if (xMax < p.getXMin() || xMin > p.getXMax() || yMax < p.getYMin()) {
            return false;
        }
        return yMin <= p.getYMax();
    }

    public String toString() {
        return (int) xMin + " " + (int) xMax + " | " + (int) yMin + " " + (int) yMax;
    }
}
