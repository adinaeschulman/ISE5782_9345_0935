package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;
public class Camera {
    final private Point position;
    final private Vector vTo;
    final private Vector vUp;
    final private Vector vRight;

    private double distance;
    private double width;
    private double height;

    public Camera(Point position, Vector vTo, Vector vUp, Vector vRight) {
        this.position = position;
        this.vTo = vTo;
        this.vUp = vUp;
        if(!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("Vector  are not pert")
    }
}
