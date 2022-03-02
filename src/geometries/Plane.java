package geometries;
import primitives.Point3D;
import primitives.Vector;
public class Plane implements Geometry{
    Point3D p3D;
    Vector normal;

    public Plane(Point3D p3D, Vector normal) {
        this.p3D = p3D;
        this.normal = normal;
    }
}
