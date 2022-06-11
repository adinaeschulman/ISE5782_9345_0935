package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight {

    private final Vector direction;
    private double narrowBeam=0d;

    /**
     * constructer
     * @param intensity
     * @param position
     * @param direction
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     *
     * @param point
     * @return intensity
     */
    @Override
    public Color getIntensity(Point point) {
        double max=Math.max(0, getL(point).dotProduct(direction));
        return  super.getIntensity(point).scale(max);

    }

    /**
     *
     * @param narrowBeam
     * @return narrow beam
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return  this;
    }



}


