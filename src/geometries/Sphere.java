package geometries;
import primitives.Point3D; //really other things adina is intializing
import  primitives.Vector; //"
public class Sphere implements Geometry {
   final  point3D _center;
    final double _radius;

    public Sphere(point3D _center, double _radius) {
        this._center = _center;
        this._radius = _radius;
    }

    public point3D get_center() {
        return _center;
    }

    public double get_radius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }
}
