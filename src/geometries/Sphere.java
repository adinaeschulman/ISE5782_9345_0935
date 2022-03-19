package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

//did get normal
public class Sphere implements Geometry {
    private final Point center;
    private final double raduius;

    /**
     * constructer for sphere
     * @param center
     * @param raduius
     */
    public Sphere(Point center, double raduius) {
        this.center = center;
        this.raduius = raduius;
    }

    /**
     *
     * @return the center of sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     *
     * @return the radius of sphere
     */
    public double getRaduius() {
        return raduius;
    }

    /**
     *
     * @return the tostring of spehre
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", raduius=" + raduius +
                '}';
    }

    /**
     *
     * @param p
     * calculates normal according ot equation
     * @return normal
     */
    @Override
    public Vector getNormal(Point p) {
        Vector p0_p = p.subtract(center);
        return p0_p.normalize();
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(center)) {
            return List.of(center.add(v.scale(raduius)));
        }

        Vector U = center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= raduius) {
            return null;
        }

        double th = alignZero(Math.sqrt(raduius * raduius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
//            Point P1 = P0.add(v.scale(t1));
//            Point P2 = P0.add(v.scale(t2));
            Point P1 =ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);
            return List.of(P1, P2);
        }
        if (t1 > 0) {
//            Point P1 = P0.add(v.scale(t1));
            Point P1 =ray.getPoint(t1);
            return List.of(P1);
        }
        if (t2 > 0) {
//            Point P2 = P0.add(v.scale(t2));
            Point P2 =ray.getPoint(t2);
            return List.of(P2);
        }
        return null;
    }
}
