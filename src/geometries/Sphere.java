package geometries;
import primitives.Point;
import primitives.Vector;
public class Sphere implements Geometry {
    private final Point center;
    private final double raduius;

    public Sphere(Point center, double raduius) {
        this.center = center;
        this.raduius = raduius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRaduius() {
        return raduius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", raduius=" + raduius +
                '}';
    }

    @Override
    public Vector getNormal(Point xyz) {
        return null;
    }
}
