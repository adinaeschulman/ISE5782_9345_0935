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
        Point Pc = position.add(vTo.scale(distance));

        double Rx = width / nX;
        double Ry = height / nY;

        Point Pij = Pc;

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(position, Pij.subtract(position));
        }
        if (isZero(Xj)) {
            Pij = Pij.add(vUp.scale(Yi));
            return new Ray(position, Pij.subtract(position));
        }
        if (isZero(Yi)) {
            Pij = Pij.add(vRight.scale(Xj));
            return new Ray(position, Pij.subtract(position));
        }

        Pij = Pij.add(vRight.scale(Xj).add(vUp.scale(Yi)));
        return new Ray(position, Pij.subtract(position));


    }

}