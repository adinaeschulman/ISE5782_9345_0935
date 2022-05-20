package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * grt intensity of the light coming from a point
 */
public interface LightSource {
    public  Color getIntesity(Point point);

    /**
     * get loght direction vector
     * @param point starting point
     * @return dorection of light
     */
    public Vector getL(Point point);

}
