package geometries;
import primitives.Point;
import primitives.Vector;
public class Plane  implements  Geometry{
 final private Point q0;
 final private Vector normal;

    /**
     * constructer for plane
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2,Point p3) {
        this.q0 = p1;
        this.normal = null;
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
     * returns null
     * @return
     */

    public Vector getNormal() {
        return null;
    }

}
