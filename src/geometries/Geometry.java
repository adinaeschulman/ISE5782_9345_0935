package geometries;
import primitives.*;

import java.util.List;

//interface
public abstract class Geometry extends Intersectable {
    protected  Color emission;

    public Color getEmission() {
        return emission=Color.BLACK;
    }

    public void setEmission(Color emission) {
        this.emission = emission;
    }

    public Geometry(Color emission) {
        this.emission = emission;
    }

    //function that calculates a normal
    public abstract  Vector getNormal(Point xyz);

}
