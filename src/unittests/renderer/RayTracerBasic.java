package unittests.renderer;
import geometries.Geometries;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        Geometries geometries=scene.getGeometries();
        List<GeoPoint> intersections = geometries.findGeoIntersectionsHelper(ray);
        if (intersections != null) {
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
            return calcColor(closestPoint);
        }
        //ray did not intersect any geometrical object
        return scene.getBackground();
    }

    private Color calcColor(GeoPoint point) {
        return scene.getAmbientLight().getIntensity();
    }
}
