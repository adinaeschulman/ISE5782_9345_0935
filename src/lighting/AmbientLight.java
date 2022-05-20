package lighting;

import primitives.Color;
import primitives.Double3;
/**
 * Ambient Light to give basic illumination
 */
public class AmbientLight extends Light {

    /**
     * primary constructor
     * @param Ia color or intensity
     * @param Ka intensity
     */
    public AmbientLight(Color Ia, Double3 Ka){
        super(Ia.scale(Ka));
    }

    /**
     * default constructor
     */
    public AmbientLight(){
        super(Color.BLACK);
    }

}
