package geometries;
//has normal from pologyn
import primitives.Point;
import primitives.Vector;

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


}
