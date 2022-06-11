package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

//did get normal
public class Sphere extends Geometry {
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

    /**
     *
     * @param ray
     * @param maxDistance
     * @return finds the geometric intersection
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance)
    {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        if (P0.equals(center))
        {
            if(alignZero(raduius-maxDistance) > 0){
                return  null;
            }
            return List.of(new GeoPoint(this, center.add(v.scale(raduius))) );
        }

        Vector U = center.subtract(P0); // The vector from p0 to the center of the sphere
        double tm =alignZero(v.dotProduct(U)); // the scalar for the projection of v on u
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm)); // the distance of the center to the ray

        // no intersections : the ray direction is above the sphere
        if (d >= raduius) {
            return null;
        }

        double th = alignZero(Math.sqrt(raduius * raduius - d * d)); // distance from p1 to intersection with d

        double t1 = alignZero(tm - th); // from p0 to p1
        double t2 = alignZero(tm + th);// from p0 to p2


        if (t1 > 0 && t2 > 0 && alignZero(t1-maxDistance)<= 0 && alignZero(t2-maxDistance)<= 0) // take only t > 0 (going in the right direction)
        {

            Point P1 =ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this, P1),new GeoPoint(this, P2));
        }
        if (t1 > 0 && alignZero(t1-maxDistance)<= 0) {
            Point P1 =ray.getPoint(t1);
            return List.of(new GeoPoint(this, P1));
        }
        if (t2 > 0  && alignZero(t2-maxDistance)<= 0) {
            Point P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this, P2));
        }

        return null;

    }

}
