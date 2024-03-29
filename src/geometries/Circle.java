package geometries;


import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Circle extends Geometry {
    Plane plane;
    Point center;
    double radius;

    /**
     * constructer
     * @param center
     * @param normal
     * @param radius
     */
    public Circle(Point center, Vector normal, double radius) {
        this.center = center;
        this.radius = radius;
        this.plane = new Plane(center, normal);
    }

    /**
     *
     * @param point
     * @return the normalized
     */
    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal(point);
    }

    /**
     *
     * @param ray
     * @param maxDistance
     * @return
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> geoPoints = plane.findGeoIntersectionsHelper(ray, maxDistance);
        if (geoPoints == null)
            return null;
        GeoPoint geoPoint = geoPoints.get(0);
        if (geoPoint.point.distance(center) > radius)
            return null;
        List<GeoPoint> newGeoPoints = new ArrayList<>();
        for (GeoPoint geo : geoPoints) {
            newGeoPoints.add(new GeoPoint(this, geo.point));
        }
        return newGeoPoints;
    }
}