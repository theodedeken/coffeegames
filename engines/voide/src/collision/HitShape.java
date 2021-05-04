package voide.collision;

import java.util.ArrayList;
import java.util.List;
import voide.math.Axis;
import voide.math.MatrixD;
import voide.math.Projection;
import voide.math.VectorD;

/**
 * Represents a collection of hitpoints forming a hitshape
 */
public class HitShape {
    private List<HitPoint> hitPoints;

    /**
     * Create a new empty hitshape
     */
    public HitShape() {
        hitPoints = new ArrayList<>();
    }

    /**
     * Create a new hitshape with one hitpoint
     * 
     * @param hitPoint the hitpoint of the hitshape
     */
    public HitShape(HitPoint hitPoint) {
        hitPoints = new ArrayList<>();
        hitPoints.add(hitPoint);
    }

    /**
     * Add a hitpoint to this hitshape
     * 
     * @param hitPoint the hitpoint to add
     */
    public void addHitPoint(HitPoint hitPoint) {
        hitPoints.add(hitPoint);
    }

    public List<HitPoint> getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(List<HitPoint> hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Transform the hitshape with the given matrix and return a new hitshape with
     * the result
     * 
     * @param updateMatrix the transformation matrix
     * 
     * @return the transformed hitshape
     */
    public HitShape update(MatrixD updateMatrix) {
        HitShape updated = new HitShape();
        for (HitPoint h : hitPoints) {
            updated.addHitPoint(h.update(updateMatrix));
        }
        return updated;
    }

    /**
     * Project this hitshape onto an axis.
     * 
     * @param ax the axis to project onto
     * 
     * @return the resulting projection
     */
    public Projection project(Axis ax) {
        Projection pro = new Projection();
        for (HitPoint h : hitPoints) {
            pro.addVector(ax.project(h.getVector()));
        }
        return pro;
    }

    public String toString() {
        List<String> print = new ArrayList<>();
        for (HitPoint h : hitPoints) {
            print.add(h.toString());
        }
        return String.join(" | ", print);
    }

    /**
     * Checks whether this hitshape collides with the given hitshape. This uses the
     * SAT algorithm.
     * 
     * @param hitshape the hitshape to check collision
     * 
     * @return true if the hitshapes collide
     */
    public boolean collision(HitShape hitshape) {
        List<Axis> normals = new ArrayList<>();
        // getting hitpoints
        List<HitPoint> hitpointsA = this.getHitPoints();
        List<HitPoint> hitpointsB = hitshape.getHitPoints();
        // sizes of lists
        int aSize = hitpointsA.size();
        int bSize = hitpointsB.size();

        // getting normals of first hitshape
        if (aSize > 1) {
            for (int i = 0; i < aSize; i++) {
                // getting vectors for normal
                VectorD v1 = hitpointsA.get(i).getVector();
                VectorD v2 = hitpointsA.get((i + 1) % aSize).getVector();
                // build normal
                Axis normal = new Axis();
                normal.initializeNormal(v1, v2);
                normals.add(normal);
            }
        }
        // getting normals for second hitshape
        if (bSize > 1) {
            for (int i = 0; i < bSize; i++) {
                // getting vectors for normal
                VectorD v1 = hitpointsB.get(i).getVector();
                VectorD v2 = hitpointsB.get((i + 1) % bSize).getVector();
                // build normal
                Axis normal = new Axis();
                normal.initializeNormal(v1, v2);
                normals.add(normal);
            }
        }

        // end of prep: now we have to check the projection of each normal
        for (Axis ax : normals) {
            Projection p1 = this.project(ax);
            Projection p2 = hitshape.project(ax);
            if (!p1.overlap(p2)) {
                return false;
            }
        }
        return true;
    }

}
