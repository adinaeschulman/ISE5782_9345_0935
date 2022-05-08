package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import unittests.renderer.RayTracerBase;
import unittests.renderer.RayTracerBasic;

import java.util.MissingResourceException;

import static primitives.Util.isZero;


public class Camera {
    private Point P0;
    private Vector v_t0;
    private Vector v_up;
    private Vector v_right;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    /**
     * constructor
     * @param P0 a point on the camera
     * @param v_t0 a vector the goes from the camera straight to the view plane.
     * @param v_up a vector that goes from the camera upwards
     */
    public Camera(Point P0, Vector v_t0, Vector v_up) {
        if (!isZero(v_up.dotProduct(v_t0)))
            throw new IllegalArgumentException("the 2 vectors are not verticals to each other");
        this.P0 = P0;
        this.v_t0 = v_t0.normalize();
        this.v_up = v_up.normalize();
        this.v_right = this.v_t0.crossProduct(this.v_up);
    }

    /**
     * getter
     * @return a P0
     */
    public Point getP0() {
        return P0;
    }

    /**
     * getter
     * @return a v_to
     */
    public Vector getV_t0() {
        return v_t0;
    }

    /**
     * getter
     * @return a v_up
     */
    public Vector getV_up() {
        return v_up;
    }

    /**
     * getter
     * @return a v_right
     */
    public Vector getV_right() {
        return v_right;
    }

    /**
     * getter
     * @return a height
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter
     * @return a width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter
     * @return a distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * sets the size of the view plane
     * @param width width of the view plane
     * @param height height of the view plane
     * @return the size of the view plane
     */
    public Camera setVPSize(double width, double height){
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * sets the distance of the view plane from the camera
     * @param distance the distance of the view plane from the camera
     * @return the distance of the view plane from the camera
     */
    public Camera setVPDistance(double distance){
        this.distance = distance;
        return this;
    }

    /**
     * constructs the ray through the pixels
     * @param nX number of columns
     * @param nY number of lines
     * @param j index of pixels
     * @param i index of pixels
     * @return a constructed ray through the pixels
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point Pc = P0.add(v_t0.scale(distance));
        //ratio
        double Ry = height / nY;
        double Rx = width / nX;
        double Yi = -(i-(nY-1)/2d)*Ry;
        double Xj = (j-(nX-1)/2d)*Rx;
        //Pixel[i,j] center:
        Point Pij = Pc;
        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(P0, Pij.subtract(P0));
        }
        if (isZero(Xj)) {
            Pij = Pij.add(v_up.scale(Yi));
            return new Ray(P0, Pij.subtract(P0));
        }
        if (isZero(Yi)) {
            Pij = Pij.add(v_right.scale(Xj));
            return new Ray(P0, Pij.subtract(P0));
        }
        Pij = Pc.add(v_right.scale(Xj).add(v_up.scale(Yi)));
        return new Ray(P0, Pij.subtract(P0));
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return  this;
    }

    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer=rayTracer;
        return this;
    }

    public void writeToImage() {
        imageWriter.writeToImage();
    }

    public void printGrid(int gap, Color intervalColor) {
        imageWriter.printGrid(gap,intervalColor);
    }

    public void renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", rayTracer.getClass().getSimpleName(),"");
            }

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    castRay(nX, nY, i, j);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    private void castRay(int nX, int nY, int i, int j) {
        Ray ray = constructRay(nX, nY, j, i);
        Color pixelColor = rayTracer.traceRay(ray);
        imageWriter.writePixel(j, i, pixelColor);
    }
}