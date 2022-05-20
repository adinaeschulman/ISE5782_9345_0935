package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.Light;
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
    private final AmbientLight ambientLight;
    private final Geometries geometries;
    private final List<Light> lights;

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

    public List<Light> getLights() {
        return lights;
    }

    public static class SceneBuilder {
        private List<Light> lights=new LinkedList<>();
        private final String name;
        public Color background = Color.BLACK;
        public AmbientLight ambientLight = new AmbientLight();
        public Geometries geometries = new Geometries();

        public SceneBuilder(String name) {
            this.name = name;
        }

        //chaining methods
        public SceneBuilder setLights(List<Light> lights) {
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

        public Scene build() {
            return new Scene(this);
        }
    }


}

