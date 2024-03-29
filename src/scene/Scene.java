package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.Light;
import lighting.LightSource;
import primitives.Color;
import primitives.Point;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene for holding all the elemnts
 */
public class Scene {
    private final String name;
    private final Color background;
    private AmbientLight ambientLight;
    private final Geometries geometries;
    private final List<LightSource> lights;

    /**
     * the scene is the object that holds all of the geometries in the scene of teh camera
     * the scene recievs a scene builder in order for it to build
     * @param builder
     */
    public Scene(SceneBuilder builder) {

        this.name = builder.name;
        this.background = builder.background;
        this.ambientLight = builder.ambientLight;
        this.geometries = builder.geometries;
        lights = builder.lights;
    }

    public String getName() {
        return name;
    }

    public Color getBackground() {
        return background;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public List<LightSource> getLights() {
        return lights;
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight=ambientLight;
    }

    /**
     * you can set the sciene only through using the object of scene builder
     */
    public static class SceneBuilder {
        private List<LightSource> lights=new LinkedList<>();
        private final String name;
        public Color background = Color.BLACK;
        public AmbientLight ambientLight = new AmbientLight();
        public Geometries geometries = new Geometries();

        public SceneBuilder(String name) {
            this.name = name;
        }

        //chaining methods
        public SceneBuilder setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * returning the scene itself
         * @return
         */
        public Scene build() {
            return new Scene(this);
        }
    }


}

