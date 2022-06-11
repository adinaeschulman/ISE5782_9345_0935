package geometries;
// im not sure if normal is correct
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry  {
 final private Point q0;
 final private Vector normal;

    /**
     * constructer for plane
     * calculates normal according to equation
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        if(p1.equals(p2)||p2.equals(p3)||p1.equals(p3))
            throw new IllegalArgumentException("2 of the points are the same");
        Vector u1=p2.subtract(p1);
        Vector u2=p3.subtract(p1);
        if(u1.normalize().dotProduct(u2.normalize())==1)
            throw new IllegalArgumentException("the points are on the same line");
        this.q0 = p1;
        Vector N =u1.crossProduct(u2);
       normal= N.normalize();

    }

    /**
     * constructer for plane
     * @param p0
     * @param normal
     */
    public Plane(Point p0, Vector normal) {/// not sure if we need this constructor
        this.q0 = p0;
        this.normal = normal.normalize();
    }

    /**
     * returns q0
     * @return
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * returns normal
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * getter of _normal field
     * @deprecated use {@link Plane#getNormal()} with null values as parameter
     * @return refrence to normal vector of the plane
     */
    @Deprecated
    public Vector getNormal() {
        return normal;
    }

    /**
     *
     * @param ray
     * @param maxDistance
     * @return find Geometric Intersections Helper
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        if (q0.equals(P0)) {
            return null;
        }
        Vector n = normal;
        //denominator
        double nv = alignZero(n.dotProduct(v));
        //ray is lying in the plane axis
        if (isZero(nv)) {
            return null;
        }
        Vector P0_Q0 = q0.subtract(P0);
        double nP0_Q0 = alignZero(n.dotProduct(P0_Q0));
        //numerator
        double nP0Q0 = alignZero(n.dotProduct(P0_Q0));
        //t should>0
        if (isZero(nP0Q0)) {
            return null;
        }
        double np0q0=alignZero(n.dotProduct(P0_Q0));
        double t = alignZero(nP0Q0 / nv);
        if (t <= 0) {
            return null;
        }
        t=alignZero(np0q0/nv);
        if(alignZero(t-maxDistance)>0)
        {
            return null;
        }
        return List.of(new GeoPoint(this, ray.getPoint(t)));
    }
}
