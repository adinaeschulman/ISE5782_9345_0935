package geometries;
import primitives.*;

import java.util.List;

//interface
public abstract class Geometry extends Intersectable {
    protected  Color emission = Color.BLACK;
    private Material material = new Material();

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    //function that calculates a normal
    public abstract  Vector getNormal(Point xyz);

    public Geometry setMaterial(Material material) {
        this.material = material;
        return  this;
    }

    public Material getMaterial() {
        return material;
    }

    //public abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
