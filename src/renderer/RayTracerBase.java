package renderer;
import java.util.List;
import primitives.Color;
import primitives.Ray;
import scene.Scene;
public abstract class RayTracerBase {

    protected Scene scene;

    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    public abstract Color traceRays(List<Ray> rays);

}