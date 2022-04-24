package elements;

import primitives.Color;
import primitives.Double3;
/**
 * Ambient Light to give basic illumination
 */
public class AmbientLight {
    private final Color intensity;//intensity as color

    /**
     * primary constructor
     * @param Ia color or intensity
     * @param Ka intensity
     */
    public AmbientLight(Color Ia, Double3 Ka){
         intensity = Ia.scale(Ka);
    }

    /**
     * default constructor
     */
    public AmbientLight(){
        intensity=Color.BLACK;
    }

    /**
     * getter for the ambient light intensity
     * @return the intensity after the scale)
     */
    public Color getIntensity() {
        return intensity;
    }
}
