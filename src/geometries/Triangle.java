package geometries;
//has normal from pologyn
import primitives.Point;
import primitives.Ray;

import java.util.List;

public class Triangle extends Polygon{
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

    /**
     *
     * @param ray
     * @param maxD
     * @return finds the geometrics intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxD) {
        return super.findGeoIntersectionsHelper(ray,maxD);
    }
}
