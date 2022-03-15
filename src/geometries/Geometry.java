package geometries;
import primitives.*;

import java.util.List;

//interface
public interface Geometry extends Intersectable {
    //function that calculates a normal
    public Vector getNormal(Point xyz);

}
