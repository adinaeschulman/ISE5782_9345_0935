package geometries;
//did get normal
import primitives.Ray;
import primitives.Vector;
import primitives.Point;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Cylinder extends Tube{
   private final double height;

    /**
     * builds a cylinder
     * @param axisRay
     * @param radius
     * @param height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     *
     * @return height of cyliner
     */
    public double getHeight() {
        return height;
    }

    /**
     *
     * @return to string of cylinder
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                '}';
    }

    /**
     * uses the super- tube to get the normal
     * @param point
     * @return the normal
     */
   @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }


}
