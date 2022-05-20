package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

private final Vector direction;
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction=direction;
    }

    @Override
    public Color getIntesity(Point point) {
        return super.getIntesity();
    }

    @Override
    public Vector getl(Point point) {
        return direction;
    }
}
