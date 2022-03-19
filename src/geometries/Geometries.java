package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{


    private List<Intersectable> intersectables;
    /**
     * constructor
     * @param intersectables
     */
    public Geometries(List<Intersectable> intersectables) {
        this.intersectables = new LinkedList<>();
    }
    public Geometries(Intersectable... geos) {
        add(geos);
    }

    public void add(Intersectable... geos) {
        Collections.addAll(intersectables, geos);
    }
    /**
     * finds the intersections between the ray and the shapes.
     * @param ray {@link Ray} pointing towards the object
     * @return a list of intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        for (Intersectable item : intersectables) {
            //get intersections points of a particular item from intersectables
            List<Point> itempoints = item.findIntersections(ray);
            if(itempoints!= null){
                //first time initialize result to new LinkedList
                if(result== null){
                    result= new LinkedList<>();
                }
                //add all item points to the resulting list
                result.addAll(itempoints);
            }
        }
        return result;
    }


}
