package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

public abstract class   Intersectable   {
    public class GeoPoint{
        public  final Geometry geometry;
        public  final Point point;

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry=geometry;
            this.point= point;
        }
    }
    public final List<Point> findIntersections (Ray ray){
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList==null ? null
                : geoList.stream().map(gp->gp.point).toList();
    }
//NVI pattern
    private List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
