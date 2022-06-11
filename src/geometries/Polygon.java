package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane plane;
    private int size;



    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return;

        Vector n = plane.getNormal();
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
        size = vertices.length;
    }

    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }

    /**
     *
     * @param ray
     * @param maxDistance
     * @return finds geometric intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = plane.findGeoIntersections((ray));

        if (result == null) {
            return result;
        }

        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Point P1 = vertices.get(1);
        Point P2 = vertices.get(0);

        Vector v1 = P1.subtract(P0);
        Vector v2 = P2.subtract(P0);

        double sign = alignZero(v.dotProduct(v1.crossProduct(v2)));

        if (isZero(sign)) {
            return null;
        }

        boolean positive = sign > 0;

        //iterate through all vertices of the polygon
        for (int i = vertices.size() - 1; i > 0; --i) {
            v1 = v2;
            v2 = vertices.get(i).subtract(P0);

            sign = alignZero(v.dotProduct(v1.crossProduct(v2)));
            if (isZero(sign)) {
                return null;
            }

            if (positive != (sign > 0)) {
                return null;
            }
        }

        return List.of(new GeoPoint(this, result.get(0).point));
    }
}


