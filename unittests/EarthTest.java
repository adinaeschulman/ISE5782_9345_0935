import geometries.Sphere;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.BLUE;

public class EarthTest {
    @Test
    public void EarthPicture() {

        Scene scene = new Scene.SceneBuilder("Test scene").build();

        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000)
                .setRayTracer(new RayTracerBasic(scene));

        scene.getGeometries().add(  new Sphere(new Point(0, 0, -200), 60d) //
                .setEmission(new Color(BLUE)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
        Material trMaterial = new Material().setKd(0.5).setKs(0.5).setShininess(30);
        scene.getLights().add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));


        scene.getLights().add(
                new PointLight(new Color(255, 255, 153), new Point(90, 150, -300)) //
                        .setKl(0.00001).setKq(0.000005)
        );

        scene.getLights().add(
                new SpotLight(new Color(255, 204, 0), new Point(90, 150, -300), new Vector(15, 12, -19)) //
                        .setKl(0.00001).setKq(0.000005)
        );

        camera.setImageWriter(new ImageWriter("Earth", 1000, 1000)) //
                .setPixels(100, 40)
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage();
        camera.writeToImage();

    }
}
