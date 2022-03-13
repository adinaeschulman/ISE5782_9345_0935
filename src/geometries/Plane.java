package geometries;
// im not sure if normal is correct
import primitives.Point;
import primitives.Vector;
public class Plane  implements  Geometry{
 final private Point q0;
 final private Vector normal;

    /**
     * constructer for plane
     * calculates normal according to equation
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2,Point p3) {
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
     * returns normal
     * @return
     */

    public Vector getNormal() {
        return normal;
    }

}
