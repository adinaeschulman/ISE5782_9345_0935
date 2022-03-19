package geometries;
//has normal from pologyn
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

public class Triangle extends Polygon {
    /**
     * constructor that intializes the triangle
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }

}
