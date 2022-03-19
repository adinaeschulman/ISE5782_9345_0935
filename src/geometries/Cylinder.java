package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

public class Cylinder extends Tube{
    private final double height;
    final Plane base1;
    final Plane base2;

    /**
     * constructor
     * @param axisRay
     * @param radius
     * @param height
     * @param base1
     * @param base2
     */
    public Cylinder(Ray axisRay, double radius, double height, Plane base1, Plane base2) {
        super(axisRay, radius);
        this.height = height;
        this.base1 = base1;
        this.base2 = base2;
    }

    @Override
    public  Vector getNormal(Point point) {
        return null;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return super.toString() +
                "height=" + height;
    }

    /**
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
//        //todo rethink the all thing
//        List<Point3D> result = super.findIntersections(ray);
//        if(result != null){
//            Point3D p = result.get(0);
//            Vector v = p.subtract(_axis.getP0());
//           //todo
//        }
//        //todo do the caps
//        return result;

        Vector vAxis = axisRay.getDir();
        Vector v = ray.getDir();
        Point p0 = ray.getP0();
        Point pC = axisRay.getP0();
        Point p1;
        Point p2;

        // intersections of the ray with infinite cylinder {without the bases)
        List<Point> intersections = super.findIntersections(ray);
        double vAxisV = alignZero(vAxis.dotProduct(v)); // cos(angle between ray directions)

        if (intersections == null) { // no intersections with the infinite cylinder
            try {
                vAxis.crossProduct(v); // if ray and axis are parallel - throw exception
                return null; // they are not parallel - the ray is outside the cylinder
            } catch (Exception e) {}

            // The rays are parallel
            Vector vP0PC;
            try {
                vP0PC = pC.subtract(p0); // vector from P0 to Pc (O1)
            } catch (Exception e) { // the rays start at the same point
                // check whether the ray goes into the cylinder
                return vAxisV > 0 ? //
                        List.of(ray.getPoint(height)) : null;
            }

            double t1 = alignZero(vP0PC.dotProduct(v)); // project Pc (O1) on the ray
            p1 = ray.getPoint(t1); // intersection point with base1

            // Check the distance between the rays
            if (alignZero(p1.distance(pC) - radius) >= 0)
                return null;

            // intersection points with base2
            double t2 = alignZero(vAxisV > 0 ? t1 + height : t1 - height);
            p2 = ray.getPoint(t2);
            // the ray goes through the bases - test bases vs. ray head and return points
            // accordingly
            if (t1 > 0 && t2 > 0)
                return List.of(p1, p2);
            if (t1 > 0)
                return List.of(p1);
            if (t2 > 0)
                return List.of(p2);
            return null;
        }

        // Ray - infinite cylinder intersection points
        p1 = intersections.get(0);
        p2 = intersections.get(1);

        // get projection of the points on the axis vs. base1 and update the
        // points if both under base1 or they are from different sides of base1
        double p1OnAxis = alignZero(p1.subtract(pC).dotProduct(vAxis));
        double p2OnAxis = alignZero(p2.subtract(pC).dotProduct(vAxis));
        if (p1OnAxis < 0 && p2OnAxis < 0)
            p1 = null;
        else if (p1OnAxis < 0)
            p1 = base1.findIntersections(ray).get(0);
        else
            /* p2OnAxis < 0 */ p2 = base1.findIntersections(ray).get(0);

        // get projection of the points on the axis vs. base2 and update the
        // points if both above base2 or they are from different sides of base2
        double p1OnAxisMinusH = alignZero(p1OnAxis - height);
        double p2OnAxisMinusH = alignZero(p2OnAxis - height);
        if (p1OnAxisMinusH > 0 && p2OnAxisMinusH > 0)
            p1 = null;
        else if (p1OnAxisMinusH > 0)
            p1 = base2.findIntersections(ray).get(0);
        else
            /* p2OnAxisMinusH > 0 */ p2 = base2.findIntersections(ray).get(0);

        // Check the points and return list of points accordingly
        if (p1 != null && p2 != null)
            return List.of(p1, p2);
        if (p1 != null)
            return List.of(p1);
        if (p2 != null)
            return List.of(p2);
        return null;
    }
}