package geometries;

import primitives.Ray;
import primitives.Vector;
import primitives.Point;
public class Cylinder extends Tube{
   private final double hight;

    public Cylinder(Ray axisRay, double radius, double hight) {
        super(axisRay, radius);
        this.hight = hight;
    }

    public double getHight() {
        return hight;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "hight=" + hight +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }
}
