package geometries;
import primitives.*;

import java.util.List;

//interface
public abstract class Geometry extends Intersectable {
    protected  Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     *
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     *
     * @param emission
     * @return
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     *
     * @param xyz
     * @return normalized
     */
    public abstract  Vector getNormal(Point xyz);

    /**
     *
     * @param material
     * @return material
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return  this;
    }

    /**
     *
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    //public abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
