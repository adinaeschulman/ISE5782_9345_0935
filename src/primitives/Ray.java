package primitives;

import geometries.Intersectable;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Ray {
    private static final double DELTA = 0.1;
    private Point p0;
    private Vector dir;

    /**
     * constructor of a ray given 3 different dimensions
     * @param xyz
     * @param x
     * @param y
     * @param z
     */
    public Ray(Double3 xyz,double x,double y,double z){
        p0=new Point(xyz);
        dir=new Vector(x,y,z).normalize();

    }

    /**
     * constructor initializes the basic parts of rhe ray
     * @param p
     * @param v
     */
    public Ray(Point p, Vector v){
        p0=p;
        dir=v.normalize();

    }

    public Ray(Point point, Vector direction, Vector normal) {
        //point + normal.scale(±EPSILON)
        dir = direction.normalize();

        double nv = alignZero(normal.dotProduct(dir));

        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        p0 = point.add(normalDelta);
    }


    /**
     * returns p0 - the initial point of the ray
     * @return
     */
    public Point getP0() {
        return p0;
    }

    /**
     * returns the dir- the initial vector in the ray
     * @return
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * prints the ray and its details
     * @return
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * checks if 2 rays are equal to eachother
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray ray = (Ray)obj;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * return the hahscode of the ray
     * @return
     */
    @Override
    //need to fix
    public int hashCode(){
        return Objects.hash(p0 );
    }
    /**
     * Get point at specific distance in the ray direction
     * @param t distance for reaching new Point
     * @return new {@link Point}
     */
    public Point getPoint(double t)
    {
        if(isZero(t)){
            throw new IllegalArgumentException("t equal 0 cause illegal vector ZERO");
        }
        return p0.add(dir.scale(t));
    }

    /**
     * finds the closest point that intersects by the ray
     * @param pointList intersection points
     * @return closest point
     */
    public Point findClosestPoint(List<Point> pointList){
        Point result = null;
        double distance=Double.MAX_VALUE;
        double d; 
        for(var pt:pointList){
            d=pt.distance(p0);
            d=pt.distance(p0);
            if(d<distance) {
                distance = d;
                result = pt;
            }
        }
        return result;
    }
    /**
     *
     * @param intersections
     * @return
     */
    public Intersectable.GeoPoint findClosestGeoPoint(List<Intersectable.GeoPoint> intersections){
        double minDistance = Double.MAX_VALUE;
        double d;
        Intersectable.GeoPoint closePoint = null;

        if(intersections==null){
            return null;
        }

        for (Intersectable.GeoPoint geoP : intersections) {

            d = geoP.point.distance(p0);
            //check if the distance of p is smaller then minDistance
            if (d < minDistance) {
                minDistance = d;
                closePoint = geoP;
            }
        }
        return closePoint;
    }
}
