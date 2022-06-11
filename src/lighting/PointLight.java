package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    private final Point position;
    private double Kc=1d;
    private double Kl=0d;
    private double Kq=0d;

    /**
     * constructer
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     *
     * @param point
     * @return intensity
     */
    @Override
    public Color getIntensity(Point point) {
        Color Ic=intensity;
        double factor=(Kc +Kl* point.distance(position) + Kq * point.distanceSquared(position) );
        return Ic.reduce(factor);
    }

    /**
     *
     * @param point starting point
     * @return l
     */
    @Override
    public Vector getL(Point point) {
        return point.subtract(position).normalize();
    }

    /**
     *
     * @param pnt
     * @return distance
     *
     */
    @Override
    public double getDistance(Point pnt)
    {
        return pnt.distance(position);
    }

    /**
     *
     * @param kl
     * @return l
     */
    public PointLight setKl(double kl) {
        this.Kl = kl;
        return this;
    }

    public double getKl() {
        return Kl;
    }

    /**
     *
     * @param kq
     * @return kq
     */
    public PointLight setKq(double kq) {
        this.Kq = kq;
        return this;
    }


    public double getKq() {
        return Kq;
    }
}
