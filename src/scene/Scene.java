package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * Scene for holding all the elemnts
 */
public class Scene {
    private final String name;
    private final Color background;
    private final AmbientLight ambientLight;
    private final Geometries  geometries;
    public Scene(SceneBuilder builder){

        this.name = builder.name;
        this.background = builder.background;
        this.ambientLight = builder.ambientLight;
        this.geometries = builder.geometries;
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

    public static   class SceneBuilder{
        private final String name;
        public Color background =Color.BLACK;
        public AmbientLight ambientLight= new AmbientLight();
        public Geometries geometries = new Geometries();

        public SceneBuilder(String name) {
            this.name = name;
        }
        //chaining methods

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
        public Scene build(){
            return new Scene(this );
        }
    }


}

