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
                if (unshaded(gp, l, n)) {
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
    private boolean unshaded(GeoPoint gp, Vector l, Vector n)
    {
        Vector lightDirection=l.scale(-1);
        Vector epsVector = n.scale(DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<Point> intersections = scene.getGeometries().findIntersections(lightRay);
        if (intersections == null)
            return true;
        return false;


    }

}

