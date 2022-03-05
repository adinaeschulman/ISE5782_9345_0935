package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
public class Tube implements Geometry {
    protected final  Ray axisRay;
    protected final double radius;

    /**
     * constructer for tube
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     *
     * @return aaxisray of tube
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     *
     * @return radius of tube
     */
    public double getRadius() {
        return radius;
    }

    /**
     *
     * @return tostring of tube
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     *
     * @param point
     * @return null
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
