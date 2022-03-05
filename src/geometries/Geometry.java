package geometries;
import primitives.*;
import static java.lang.System.out;
import static primitives.Util.*;
//interface
public interface Geometry {
    //function that calculates a normal
    public Vector getNormal(Point xyz);
}
