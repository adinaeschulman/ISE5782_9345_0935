package renderer;

import java.util.LinkedList;
import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;


public class Camera {
    public static boolean print;
    private Point P0;
    private Vector v_t0;
    private Vector v_up;
    private Vector v_right;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private int amountRowPixels;
    private int amountColumnPixels;
    private int threadsCount = 0;
    private static final int SPARE_THREADS = 2;


    /**
     * constructor
     *
     * @param P0   a point on the camera
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
        amountRowPixels = 0;
        amountColumnPixels = 0;
    }

    /**
     * getter
     *
     * @return a P0
     */
    public Point getP0() {
        return P0;
    }

    /**
     * getter
     *
     * @return a v_to
     */
    public Vector getV_t0() {
        return v_t0;
    }

    /**
     * getter
     *
     * @return a v_up
     */
    public Vector getV_up() {
        return v_up;
    }

    /**
     * getter
     *
     * @return a v_right
     */
    public Vector getV_right() {
        return v_right;
    }

    /**
     * getter
     *
     * @return a height
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter
     *
     * @return a width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter
     *
     * @return a distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * sets the size of the view plane
     *
     * @param width  width of the view plane
     * @param height height of the view plane
     * @return the size of the view plane
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * :TODO add comment
     *
     * @param amountRowPixels
     * @param amountColumnPixels
     * @return
     */
    public Camera setPixels(int amountRowPixels, int amountColumnPixels) {
        this.amountRowPixels = amountRowPixels;
        this.amountColumnPixels = amountColumnPixels;
        return this;
    }

    /**
     * sets the distance of the view plane from the camera
     *
     * @param distance the distance of the view plane from the camera
     * @return the distance of the view plane from the camera
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * constructs the ray through the pixels
     *
     * @param nX number of columns
     * @param nY number of lines
     * @param j  index of pixels
     * @param i  index of pixels
     * @return a constructed ray through the pixels
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point Pc = P0.add(v_t0.scale(distance));
        //ratio
        double Ry = height / nY;
        double Rx = width / nX;
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
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

    /**
     * :TODO add comment
     *
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        if (amountColumnPixels <= 0 || amountRowPixels <= 0) {
            return List.of(constructRay(nX, nY, j, i));
        }
        Point Pc = P0.add(v_t0.scale(distance));
        List<Ray> rays = new LinkedList<>();
        //ratio
        double Ry = height / nY;
        double Rx = width / nX;
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
        //Pixel[i,j] center:
        Point Pij = Pc;

        if (!isZero(Yi)) {
            Pij = Pij.add(v_up.scale(Yi));
        }
        if (!isZero(Xj)) {
            Pij = Pij.add(v_right.scale(Xj));
        }

        Ry = Ry / amountColumnPixels;
        Rx = Rx / amountRowPixels;


        for (int k = 0; k < amountRowPixels; k++) {
            for (int l = 0; l < amountColumnPixels; l++) {
                Point point = Pij;
                double Yii = -(k - (amountColumnPixels - 1) / 2d) * Ry;
                double Xjj = -(l - (amountRowPixels - 1) / 2d) * Rx;
                if (!isZero(Yii)) {
                    point = point.add(v_up.scale(Yii));
                }
                if (!isZero(Xjj)) {
                    point = point.add(v_right.scale(Xjj));
                }
                rays.add(new Ray(P0, point.subtract(P0)));
            }
        }

        return rays;
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    public Camera writeToImage() {
        imageWriter.writeToImage();
        return this;
    }

    public void printGrid(int gap, Color intervalColor) {
        imageWriter.printGrid(gap, intervalColor);
    }

    public Camera renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", rayTracer.getClass().getSimpleName(), "");
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
        return this;
    }

    private void castRay(int nX, int nY, int i, int j) {
        List<Ray> rays = constructRays(nX, nY, j, i);
        Color pixelColor = rayTracer.traceRays(rays);
        imageWriter.writePixel(j, i, pixelColor);
    }

    public Camera setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
        if (threads != 0)
            this.threadsCount = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            this.threadsCount = cores <= 2 ? 1 : cores;
        }
        return this;
    }

    private void ImageThreaded() {
        final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();
        final Pixel thePixel = new Pixel(nY, nX);
        // Generate threads
        Thread[] threads = new Thread[threadsCount];
        for (int i = threadsCount - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel))
                    castRay(nX, nY, pixel.col, pixel.row);
            });
        }
        // Start threads
        for (Thread thread : threads)
            thread.start();

        // Print percents on the console
        thePixel.print();

        // Ensure all threads have finished
        for (Thread thread : threads)
            try {
                thread.join();
            } catch (Exception e) {
            }

        if (print)
            System.out.print("\r100%");
    }

    public Camera setDebugPrint() {
        print = true;
        return this;
    }


}