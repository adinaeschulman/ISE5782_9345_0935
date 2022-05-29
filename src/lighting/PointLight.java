package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    private final Point position;
    private double Kc=1d;
    private double Kl=0d;
    private double Kq=0d;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point point) {
        Color Ic=intensity;
        double factor=(Kc +Kl* point.distance(position) + Kq * point.distanceSquared(position) );
        return Ic.reduce(factor);
    }

    @Override
    public Vector getL(Point point) {
        return point.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point pnt)
    {
        return pnt.distance(position);
    }

    public PointLight setKl(double kl) {
        this.Kl = kl;
        return this;
    }

    public double getKl() {
        return Kl;
    }

    public PointLight setKq(double kq) {
        this.Kq = kq;
        return this;
    }


    public double getKq() {
        return Kq;
    }
}
