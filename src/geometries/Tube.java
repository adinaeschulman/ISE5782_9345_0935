package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
// did get normal
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
     * @param p
     * @return null
     */
    @Override
    public Vector getNormal(Point p) {
        Point P0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        Vector P0_P = p.subtract(P0);

        double t = alignZero(v.dotProduct(P0_P));

        if (isZero(t)) {
            return P0_P.normalize();
        }

        Point o = P0.add(v.scale(t));

        if (p.equals(o)) {
            throw new IllegalArgumentException("point cannot be on the tube axis");
        }

        Vector n = p.subtract(o).normalize();

        return n;
    }
}
