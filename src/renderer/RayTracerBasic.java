//package renderer;
//
//import geometries.Geometries;
//import geometries.Intersectable.GeoPoint;
//import lighting.LightSource;
//import primitives.*;
//import scene.Scene;
//
//import java.util.List;
//
//import static primitives.Util.alignZero;
//
//public class RayTracerBasic extends RayTracerBase {
//    public RayTracerBasic(Scene scene) {
//        super(scene);
//    }
//
//    private static final double EPS = 0.1;
//    private static final int MAX_CALC_COLOR_LEVEL = 10;
//    private static final Double3 MIN_CALC_COLOR_K = new Double3(0.001);
//    private static final double INITIAL_K = 1.0;
//
//
//    @Override
//    public Color traceRay(Ray ray) {
//        Geometries geometries = scene.getGeometries();
//        List<GeoPoint> intersections = geometries.findGeoIntersections(ray);
//        if (intersections != null) {
//            GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
//            return calcColor(closestPoint, ray);
//        }
//        //ray did not intersect any geometrical object
//        return scene.getBackground();
//    }
//
//    private Color calcColor(GeoPoint gp, Ray ray) {
//        Color result = scene.getAmbientLight().getIntesity();
//        result = result.add(gp.geometry.getEmission());
//        result = result.add(calcLocalEffects(gp, ray));
//        return result;
//    }
//
//    /**
//     * add here the lights effect
//     *
//     * @param gp  geopoint of intersection
//     * @param ray
//     * @return
//     */
//    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
//        Color color = gp.geometry.getEmission();
//        Vector v = ray.getDir();
//        Vector n = gp.geometry.getNormal(gp.point);
//        double nv = alignZero(n.dotProduct(v));
//        if (nv == 0) return color;
//        Material material = gp.geometry.getMaterial();
//        for (LightSource lightSource : scene.getLights()) {
//            Vector l = lightSource.getL(gp.point);
//            double nl = alignZero(n.dotProduct(l));
//            if (nl * nv > 0) { // sign(nl) == sing(nv)
//                if (unshaded(gp, lightSource, l, n, nl, nv)) {
//                    Color iL = lightSource.getIntensity(gp.point);
//                    color = color.add(iL.scale(calcDiffusive(material, nl)),
//                            iL.scale(calcSpecular(material, n, l, nl, v)));
//                }
//            }
//        }
//        return color;
//    }
//
//    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
//        Color color = Color.BLACK;
//        Vector n = gp.geometry.getNormal(gp.point);
//        Material material = gp.geometry.getMaterial();
//        Double3 kkr = material.getkR().product(k);
////        Ray reflectedRay = constructReflectedRay(n, gp.point, inRay);
//        //GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
//        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
//            color = calcGlobalEffect(constructRefractedRay(gp.point, new Ray(gp.point,v), n), level, material.getkR(), kkr);
//        }
//        Double3 kkt = material.getkT().product(k);
//        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
//            color = color.add(calcGlobalEffect(constructReflectedRay(gp.point, new Ray(gp.point,v), n), level, material.getkT(), kkt));
//        return color;
//    }
//
//    /**
//     * calc the global effects- reflection and refraction.
//     * this func call "calcColor" in recursion to add more and more global effects.
//     * @param ray is the ray from the viewer
//     * @param level of recursion- goes down each time till it gets to 1
//     * @param kx is kR or kT factor
//     * @param kkx is kR or kT factor multiplied by k - factor of reflection and refraction
//     * @return the color of the effect
//     * new change
//     */
//    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
//        GeoPoint gp = findClosestIntersection (ray);
//        return (gp == null ? scene.getBackground() : calcColor(gp, ray, level-1, kkx)
//        ).scale(kx);
//    }
//
//    public Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
//        if (intersection == null) {
//            return scene.getBackground();
//        }
//        Color color = intersection.geometry.getEmission();//color is the objects color
//        color = color.add(calcLocalEffects(intersection, ray));//adding the local effects
//        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));//adding the global effects
//    }
//
//    private GeoPoint findClosestIntersection(Ray ray) {
//            if (ray == null) {
//                return null;
//            }
//            List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
//            if (intersections == null) {
//                return null;
//            }
//            return ray.findClosestGeoPoint(intersections);//find closest intersection geo point
//
//    }
//
//    private Ray constructRefractedRay(Point point, Ray ray, Vector n) {
//        return new Ray(point, ray.getDir(), n);//creates new ray that is scaled by delta- it is moved a little from surface of geometry
//    }
//
//
//    private Ray constructReflectedRay(Point point, Ray ray, Vector normal) {
//        Vector dir = ray.getDir();
//        Vector normalDir = normal.scale(-2 * dir.dotProduct(normal));
//        Vector result = dir.add(normalDir);
//        return result;
//    }
//
//
//    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
//        Vector r = l.add(n.scale(l.dotProduct(n) * -2));
//        double minusVR = -alignZero(r.dotProduct(v));
//        if (minusVR <= 0)
//            return Double3.ZERO;
//        return material.getKs().scale(Math.pow(minusVR, material.getnShininess()));
//    }
//
//
//    private Double3 calcDiffusive(Material material, double nl) {
//        nl = Math.abs(nl);
//        Double3 result = material.getKd().scale(nl);
//        return result;
//    }
//
//    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl, double nv) {
//        Point point = gp.point;
//        Vector lightDirection = l.scale(-1);
//        Vector espVector = n.scale(nv < 0 ? EPS : -EPS);
//        Point pointRay = point.add(espVector);
//        Ray lightRay = new Ray(pointRay, lightDirection);
//        double maxDistance = lightSource.getDistance(point);
//        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, maxDistance);
//        return intersections == null;
//    }
//
//    private Double3 transparency(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nv) {
//        Vector lightDirection = l.scale(-1);
////        Vector espVector=n.scale(nv<0? EPS: -EPS);
//        Point point = gp.point;
////        Point pointRay=point.add(espVector);
////        Ray lightRay=new Ray(pointRay,lightDirection);
//        Ray lightRay = new Ray(point, l.scale(-1), lightDirection);
//        if (nv < 0) {
//            lightRay = new Ray(point, n, lightDirection);
//        }
//
//        double maxDistance = lightSource.getDistance(point);
//        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, maxDistance);
//
//        if (intersections == null)
//            return new Double3(1.0);
//        Double3 ktr = Double3.ONE;
////        double ktr = 1;
////        loop over intersections and for each intersection which is closer to the
////        point than the light source multiply ktr by 𝒌𝑻 of its geometry
//        for (var geo : intersections) {
//            ktr = ktr.product(geo.geometry.getMaterial().getkT());
//            if (ktr.lowerThan(MIN_CALC_COLOR_K))
//                return ktr;
//        }
//
//        return ktr;
//    }
//}
//
////    private boolean unshaded(GeoPoint gp, LightSource lighSource, Vector l, Vector n, double nl, double nv) {
////        Point point = gp.point;
////        Vector lightDirection = l.scale(-1);
////        Ray lightRay = new Ray(gp.point, n, lightDirection);
////        Vector epsVector = n.scale(nv < 0 ? EPS : -EPS);
////        Point pointRay = gp.point.add(epsVector);
////        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay);
//////        if (gp.geometry instanceof FlatGeometry && intersections !=null)
//////            intersections.remove(gp);
////        return intersections == null;
////
////
////    }
package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * basic ray trace heir from the rayTraceBase class the class is to calculate
 * the closest point to the ray from all the intersections and calculate the
 * color in this point
 *
 * @author David and Matan
 */

public class RayTracerBasic extends RayTracerBase {
    /**
     * Ctor - get scene and set it
     *
     * @param scene - body that build from geometries bodies and color and
     *              ambientLight(strong of the color)
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = new Double3(1.0);



    /**
     * For shading test between point and light source
     *
     * @param dirLight     - vector from light
     * @param normal     - normal of body
     * @param gp    - point in geometry body
     * @return
     *         <li>true - if unshaded
     *         <li>false - if shaded
    private boolean unshaded(LightSource light, Vector dirLight, Vector normal, GeoPoint gp) {
    Vector lightDirection = dirLight.scale(-1);
    // Vector epsVector=normal.scale(nv<0?DELTA:-1*DELTA);
    // Point point =gp.point.add(epsVector);
    Vector delta = normal.scale(normal.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
    Point point = gp.point.add(delta);
    Ray lightRay = new Ray(point, lightDirection);
    List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
    if (intersections == null){
    return true;
    }// && gp.geometry.getMaterial().kT.equals(new Double3(0.0))
    double lightDistance = light.getDistance(gp.point);//calculates the Distance between light and point
    for (GeoPoint geoPoint : intersections) {
    //checks if the point is behind light
    if (geoPoint.point.distance(gp.point) <= lightDistance &&  geoPoint.geometry.getMaterial().kT.equals(new Double3(0.0))){
    return false;//the point should be shaded
    }
    }
    return true;//no shade

    }
     */

    /**
     * Implementation for the abstract method traceRay
     */
    @Override
    public  Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        Color color = scene.getBackground();
        if (closestPoint != null) {
            color = calcColor(closestPoint, ray);
        }
        return color;
    }
    /**
     * Calculate the color of a certain point
     *
     * @param intersection
     * @return The color of the point (calculated with local effects)
     */
    public Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        if (intersection == null) {
            return scene.getBackground();
        }
        Color color = intersection.geometry.getEmission();//color is the objects color
        color = color.add(calcLocalEffects(intersection, ray,k));//adding the local effects
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));//adding the global effects
    }
    /**
     * Calculate the color of a certain point
     *
     * @param geoPoint
     * * @param ray
     * @return The color of the point (calculated with local effects)
     */
    private Color calcColor (GeoPoint geoPoint, Ray ray){
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.getAmbientLight().getIntesity());

    }
    /**
     * calcGlobalEffects recursive
     * @param geoPoint
     * @param
     * @param level
     * @param k
     * @return
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;

        Vector normal = geoPoint.geometry.getNormal(geoPoint.point);
        //double vn = alignZero(normal.dotProduct(v));
        Material material = geoPoint.geometry.getMaterial();
        Double3 kr = k.product(material.kR);
        //Conditions for stopping a recursive function, by The contribution of light to a point.
        //if the effect of light on the point color is minimal then stop with the recursion
        if (!kr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = calcRayReflection(normal, geoPoint.point, ray);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null){
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kr).scale(material.kR));
            }
        }
        Double3 kt = k.product(material.kT);
        //Conditions for stopping a recursive function, by The contribution of light to a point.
        //if the effect of light on the point color is minimal then stop with the recursion
        if (!kt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray refractedRay = constructRefractedRay(normal, geoPoint.point, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null){
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kt).scale(material.kT));
            }
        }
        return color;
    }
    /**
     * Calculate the effects of lights
     *
     * @param intersection
     * @param ray
     * @return The color resulted by local effecrs calculation
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
        Color color = Color.BLACK;
        Vector dir = ray.getDir();
        Vector normal = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(normal.dotProduct(dir));
        if (nv == 0) {
            return color;
        }
        int nShininess = (int) intersection.geometry.getMaterial().getnShininess();

        Double3 kd = intersection.geometry.getMaterial().getKd();
        Double3 ks = intersection.geometry.getMaterial().getKs();

        for (LightSource lightSource : scene.getLights()) {
            Vector dirLight = lightSource.getL(intersection.point);
            double nl = alignZero(normal.dotProduct(dirLight));
            // checks if nl == nv
            if (nl * nv > 0) {// checks if nl == nv
                Double3 ktr = transparency(lightSource, dirLight, normal, intersection, nv);
                if (!k.product(ktr).lowerThan(MIN_CALC_COLOR_K)) {

                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, dirLight, normal, lightIntensity),
                            calcSpecular(ks, dirLight, normal, dir, nShininess, lightIntensity));
                }
            }

        }
        return color;
    }
    /**
     * Calculates diffusive light
     * @param kd
     * @param dirLight
     * @param normal
     * @param lightIntensity
     * @return The color of diffusive effects
     */
    private Color calcDiffusive (Double3 kd, Vector dirLight, Vector normal, Color lightIntensity){
        double s = Math.abs(alignZero(dirLight.dotProduct(normal)));
        return lightIntensity.scale(kd.scale(s));
    }

    /**
     * Calculate specular light
     * @param ks
     * @param dirLight
     * @param normal
     * @param dir
     * @param nShininess
     * @param lightIntensity
     * @return The color of specular reflection
     */
    private Color calcSpecular (Double3 ks, Vector dirLight, Vector normal, Vector dir,int nShininess, Color
            lightIntensity){
        Vector reflectedDir = dirLight.add(normal.scale(dirLight.dotProduct(normal) * -2));
        //Vector reflectedDir = dirLight.subtract(normal.scale(dirLight.dotProduct(normal) * 2));
        double t = alignZero(-reflectedDir.dotProduct(dir));
        Color color = Color.BLACK;
        if (t > 0) {
            color = lightIntensity.scale(ks.scale(Math.pow(t, nShininess)));
        }
        return color;
    }
    /**
     * calculate the reflected ray
     *
     * @param normal  - normal to the point on geometry
     * @param point  - point on geometry body
     * @param ray
     * @return reflected ray
     */
    private Ray calcRayReflection (Vector normal, Point point, Ray ray){
        Vector dir = ray.getDir();
        Vector normalDir = normal.scale(-2 * dir.dotProduct(normal));
        Vector result = dir.add(normalDir);
        //double nv = normal.dotProduct(dir);
        // Vector result = normal.scale(nv * 2).subtract(dir);
        //Vector result = dir.subtract(normal.scale(alignZero(2 * (normal.dotProduct(dir)))));

        return new Ray(point, result, normal);
        // Vector result = dir.subtract(normal.scale(-2 * nv)).normalize();
        // return new Ray(point, result);
    }


    // return new Ray(point, r, n);


    /**
     * constructRefractedRay t
     *
     * @param point
     * @param ray
     * @param normal
     * @return ray
     */
    private Ray constructRefractedRay (Vector normal, Point point, Ray ray){
        //double nv = normal.dotProduct(ray.getDir());
        return new Ray(point, ray.getDir(), normal);//creates new ray that is scaled by delta- it is moved a little from surface of geometry
    }
    /**
     * calculates the amount of shadow in the point sometimes we need light shadow
     * and sometimes not
     *
     * @param light - light source
     * @param l     - vector from light
     * @param n     - normal of body
     * @param gp    - point in geometry body
     ** @param nv
     * @return amount of shadow
     */

    protected Double3 transparency (LightSource light, Vector l, Vector n, GeoPoint gp,double nv){
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point,  lightDirection,n);
        double lightDistance = light.getDistance(gp.point);
        var intersections = scene.getGeometries().findGeoIntersections(lightRay);
        Double3 ktr = new Double3(1.0);
        if (intersections == null)
            return ktr;
        for (GeoPoint geopoint : intersections) {
            if(alignZero(geopoint.point.distance(gp.point)-lightDistance)<=0){
//                 if (geopoint.point.distance(gp.point) <= lightDistance &&  geopoint.geometry.getMaterial().kT.equals(new Double3(0.0))){
//                 var  kt = ktr.product(geopoint.geometry.getMaterial().kT);
                var kt=geopoint.geometry.getMaterial().kT;
                ktr=kt.product(ktr);
                if (ktr.lowerThan(MIN_CALC_COLOR_K))
                    return new Double3(0.0);
            }


        }
        return ktr;

    }
    /**
     * @param ray
     * @return The closest intersection point
     */
    private GeoPoint findClosestIntersection (Ray ray)
    {
        if (ray == null) {
            return null;
        }
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null) {
            return null;
        }
        return ray.findClosestGeoPoint(intersections);//find closest intersection geo point
    }

}



