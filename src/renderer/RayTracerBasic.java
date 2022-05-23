package renderer;

import geometries.Geometries;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0 ;
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        Geometries geometries = scene.getGeometries();
        List<GeoPoint> intersections = geometries.findGeoIntersectionsHelper(ray);
        if (intersections != null) {
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
            return calcColor(closestPoint, ray);
        }
        //ray did not intersect any geometrical object
        return scene.getBackground();
    }

    private Color calcColor(GeoPoint gp, Ray ray) {
        Color result = scene.getAmbientLight().getIntesity();
        result = result.add(gp.geometry.getEmission());
        result = result.add(calcLocalEffects(gp, ray));
        return result;
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if  (unshaded(gp, lightSource, l, n, nv)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.add(n.scale(l.dotProduct(n) * -2));
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Double3.ZERO;
        return material.getKs().scale(Math.pow(minusVR, material.getnShininess()));
    }


    private Double3 calcDiffusive(Material material, double nl) {
        nl = Math.abs(nl);
        Double3 result = material.getKd().scale(nl);
        return result;
    }



    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(gp.point, lightDirection,n);
        double lightDistance = light.getDistance(gp.point);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay); //new Ray(lightDistance)
        if (intersections == null) return true;
        //if there are no intersections return true (there is no shadow)


//        for (GeoPoint intersection : intersections) {
//            //for each intersection if there are points in the intersections list that are closer
//            //to the point then light source, return false
//            if (intersection.geometry.getMaterial().kT.lowerThan(MIN_CALC_COLOR_K)) {
//                return false;
//            }
//        }
//        return true;
        return true;

    }

    /**
     * find the closest Geo point intersection to the ray
     * @param ray is the ray from the viewer
     * @return the closest intersection to the ray
     */
    private GeoPoint findClosestIntersection(Ray ray){
        if (ray == null) {
            return null;
        }

        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null) {
            return null;
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return closestPoint;
    }
}

