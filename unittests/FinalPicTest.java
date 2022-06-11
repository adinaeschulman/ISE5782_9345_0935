import geometries.*;
import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import lighting.LightSource;
import lighting.SpotLight;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import static geometries.Utils.createRectangleY;

class FinalPicTest {
    @Test
    public void Picture() {
        Vector axisY = new Vector(0, 1, 0);
        Geometries constGeometries = new Geometries();
        List<LightSource> constLights = new ArrayList<>();
        Geometries redLightsSurfaces = new Geometries();
        Scene scene = new Scene.SceneBuilder("Test scene").setBackground(new Color(0, 0, 0)).build();
        List<LightSource> redLights = new ArrayList<>();
        Helicopter chopper = new Helicopter(new Point(0, 0, 0), 8);
//add a plane to geometries as a background
        constGeometries.add(new Plane(new Point(0, -100, -200), axisY).setEmission(new Color(155, 118, 83)).setMaterial(new Material().setKd(0.2).setKs(0.5).setShininess(100)));
//draw an H at the floor background
        constGeometries.add(createRectangleY(new Point(0, -100 + 0.09, 25), 80, 18).

                setEmission(new Color(58, 58, 58)).
                setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).

                setShininess(300)));
                constGeometries.add(
                createRectangleY(new Point(0, -100 + 0.09, -25), 80, 18).
                        setEmission(new Color(58, 58, 58)).
                        setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.5).
                        setShininess(300)));
                constGeometries.add(
                createRectangleY(new Point(0, -100 + 0.09, 0), 20, 50).
                        setEmission(new Color(58, 58, 58)).
                        setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.5).
                        setShininess(300)));
                constGeometries.add(
                createRectangleY(new Point(0, -100 + 0.1, 25), 74, 12).
                        setEmission(new Color(150, 150, 150)).
                        setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.5).
                        setShininess(300)));
                constGeometries.add(
                createRectangleY(new Point(0, -100 + 0.1, -25), 74, 12).
                        setEmission(new Color(150, 150, 150)).
                        setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.5).
                        setShininess(300)));
                constGeometries.add(
                createRectangleY(new Point(0, -100 + 0.1, 0), 14, 50).
                        setEmission(new Color(150, 150, 150)).
                        setMaterial(new Material().
                        setKd(0.5).
                        setKs(0.5).
                        setShininess(300)));

//draw a circle around the H at the floor background
        constGeometries.add(new
                Circle(new Point(0, -100 + 0.05, 0), axisY, 81).
                setEmission(new Color(58, 58, 58)).
                setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(300)));
        constGeometries.add(new
                Circle(new Point(0, -100 + 0.06, 0), axisY, 78).
                setEmission(new Color(255, 211, 25)).
                setMaterial(new Material().
                setKd(0.5).
                setKs(0.5).
                setShininess(300)));
        constGeometries.add(new
                Circle(new Point(0, -100 + 0.07, 0), axisY, 68).setEmission(new Color(58, 58, 58)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        constGeometries.add(new
                Circle(new Point(0, -100 + 0.08, 0), axisY, 65).setEmission(new Color(93, 93, 93)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));

        constLights.add(new
                DirectionalLight(new Color(200, 200, 200), new
                Vector(-1, -5, -7)));
        constLights.add(new

                PointLight(new Color(100, 100, 100), new

                Point(0, 110, 50)).setKl(0.0000003).setKq(0.0000001));

        Vector lightsRadius = new Vector(60, 0, 0);
        double lightsSize = 3;
        for (int i = 0; i < 5; i++) {
            redLightsSurfaces.add(new Circle(new Point(0, -100 + 0.2, 0).add(lightsRadius.Roatate(72 * i, axisY)), axisY, lightsSize).setMaterial(new Material().setKs(0.0002).setKT(0.99)));

            redLights.add(new SpotLight(new Color(300, 300, 900), new Point(0, -100 + lightsSize / 2, 0).add(lightsRadius.Roatate(72 * i, axisY)), new Vector(0, -1, 0))); //
        }

        ImageWriter imageWriter = new ImageWriter("final image", 1000, 1000);
        Camera camera = new Camera(new Point(300, 270, 300), new Vector(-1, -1, -1), new Vector(-1, 2, -1))
               // .setPixels(100, 100)//
                .setVPSize(150, 150) //
                .setVPDistance(300).setImageWriter(imageWriter);

        scene.getGeometries().add(constGeometries);
        scene.getGeometries().add(chopper);
        scene.getLights().addAll(constLights);
        scene.getLights().addAll(redLights);
        scene.getGeometries().add(redLightsSurfaces);
        camera.setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.writeToImage();
    }
}