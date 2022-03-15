package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    private List<Intersectable> intersectables= new LinkedList<>();
    public Geometries(Intersectable... geos) {
        add(geos);
    }

    public void add(Intersectable... geos) {
        Collections.addAll(intersectables, geos);
    }

}
