package geometries;
import primitives.Point;
import primitives.Vector;
public class Plane  implements  Geometry{
 final private Point q0;
 final private Vector normal;

    public Plane(Point p1, Point p2,Point p3) {
        this.q0 = p1;
        this.normal = null;
    }
    public Plane(Point p0, Vector normal) {/// not sure if we need this constructor
        this.q0 = p0;
        this.normal = normal.normalize();
    }

    public Point getQ0() {
        return q0;
    }
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
    public Vector getNormal() {
        return null;
    }

}
