package lighting;

import primitives.Color;

public abstract  class Light {
    protected final Color intensity;//intensity as color

    protected Light(Color intensity) {

        this.intensity = intensity;
    }

    /**
     * getter for the ambient light intensity
     *
     * @return the intensity after the scale)
     */
    public Color getIntesity() {
        return intensity;
    }
}


