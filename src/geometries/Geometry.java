package geometries;
import primitives.*;

import java.util.List;

//interface
public abstract class Geometry extends Intersectable {
    protected  Color emission = Color.BLACK;

    public Color getEmission() {
        return emission;
    }

    public void setEmission(Color emission) {
        this.emission = emission;
    }

    //function that calculates a normal
    public abstract  Vector getNormal(Point xyz);

}
