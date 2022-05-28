package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    private final Point position;
    private Double3 Kc =Double3.ONE;
    private Double3 Kl=Double3.ZERO;
    private Double3 Kq = Double3.ZERO;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point point) {
        Color Ic=intensity;
        Double3 factor=(Kc.add(Kl.scale(point.distance(position))).add( Kq.scale(point.distanceSquared(position)) ));
        return Ic.reduce(factor);
    }

    @Override
    public Vector getL(Point point) {
        return point.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return point.distance(this.position);
    }

    public Double3 getKc() {
        return Kc;
    }

    public PointLight setKc(double kc) {
        this.Kc = new Double3(kc);
        return this;
    }

    public PointLight setKl(double kl) {
        this.Kl =  new Double3(kl);
        return this;
    }

    public Double3 getKl() {
        return Kl;
    }

    public PointLight setKq(double kq) {
        this.Kq = new Double3(kq);
        return this;
    }


    public Double3 getKq() {
        return Kq;
    }
}
