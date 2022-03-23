package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;
public class Camera {
     private Point position;
     private Vector vTo;
     private Vector vUp;
     private Vector vRight;

    private double distance;
    private double width;
    private double height;

    public Camera(Point position, Vector vTo, Vector vUp) {
        this.position = position;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        if(!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("Vector  are not pert");
        this.vRight=vTo.crossProduct(vUp);
    }
    public Camera setVPSize(double width, double height){
        this.width=width;
        this.height=height;
        return this;
    }
    public Camera setVPDistance(double distance){
        this.distance=distance;
        return this;
    }
    public Ray constructRay(int nX, int nY, int j, int i){
        return null;
    }
}
