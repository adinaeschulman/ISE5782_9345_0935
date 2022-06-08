import org.junit.jupiter.api.Test;

import renderer.Camera;
import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.RayTracerBasic;
import scene.Scene;

import javax.swing.*;

public class FinalPersonTest {
    @Test
    public void ourPicture() {
        Scene scene = new Scene.SceneBuilder("Test scene")//
                .setBackground(new Color(0, 0, 0)).build();
        Triangle triangle = new Triangle(new Point(-125, -225, -260)
                , new Point(-225, -125, -260), new Point(-225, -225, -270));
        scene.getGeometries().add(triangle);
//        Geometry sphere = new Sphere(new Point(0.0, 0.0, -1000), 600).setEmission(new Color(0, 0, 255)).setMaterial(new Material().setKT(1).setShininess(20));
//        scene.getGeometries().add(sphere);
//      Geometry sphere2 = new Sphere(new Point(0.0, 0.0, -1000), 100).setEmission(new Color(0, 0, 0)).setMaterial(new Material().setKT(0).setShininess(1000));
//        scene.getGeometries().add(sphere2);
//        Geometry sphere3 = new Sphere(new Point(0.0, 0.0, -1000), 300).setEmission(new Color(102, 0, 102)).setMaterial(new Material().setKT(0).setShininess(20));
//        scene.getGeometries().add(sphere3);
        scene.getLights().add(
                new SpotLight(new Color(100, 100, 100), new Point(-200, -200, -150), new Vector(2, 2, -3)));



        //head
        Geometry sphere4 = new Sphere(new Point(0.0, 0.0, -1000), 300).setEmission(new Color(0, 204, 0)).setMaterial(new Material().setKT(1).setShininess(1000));
        scene.getGeometries().add(sphere4);

        scene.getLights().add(
                new SpotLight(new Color(100, 100, 100), new Point(1000, 1000, 1000), new Vector(-1000, -1000, -2000)));


        //body
        Geometry sphere5 = new Sphere(new Point(20, -600, -1000), 300).setEmission(new Color(0, 204, 0)).setMaterial(new Material().setKT(1).setShininess(20));
        scene.getGeometries().add(sphere5);


        //arms
        //left arm
        Geometry sphere6 = new Sphere(new Point(290, -480, -1000), 90).setEmission(new Color(0, 204, 0)).setMaterial(new Material().setKT(1).setShininess(20));
        scene.getGeometries().add(sphere6);
        Geometry sphere7 = new Sphere(new Point(310, -510, -1000), 90).setEmission(new Color(0, 204, 0)).setMaterial(new Material().setKT(1).setShininess(20));
        scene.getGeometries().add(sphere7);
        Geometry sphere8 = new Sphere(new Point(330, -540, -1000), 90).setEmission(new Color(0, 204, 0)).setMaterial(new Material().setKT(1).setShininess(20));
        scene.getGeometries().add(sphere8);
        Geometry sphere9 = new Sphere(new Point(40, -570, -1000), 90).setEmission(new Color(0, 204, 0)).setMaterial(new Material().setKT(1).setShininess(20));
        scene.getGeometries().add(sphere9);
        Geometry sphere10 = new Sphere(new Point(350, -600, -1000), 90).setEmission(new Color(0, 204, 0)).setMaterial(new Material().setKT(1).setShininess(20));
        scene.getGeometries().add(sphere10);
        Geometry sphere11 = new Sphere(new Point(370, -650, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere11);
        Geometry sphere12 = new Sphere(new Point(390, -650, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere12);
        Geometry sphere13 = new Sphere(new Point(400, -650, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere13);
        Geometry sphere14 = new Sphere(new Point(410, -660, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere14);
        Geometry sphere15 = new Sphere(new Point(430, -640, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere15);
        Geometry sphere16 = new Sphere(new Point(450, -610, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere16);
        Geometry sphere17= new Sphere(new Point(470, -590, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere17);
        Geometry sphere18 = new Sphere(new Point(490, -570, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere18);
        Geometry sphere19 = new Sphere(new Point(510, -550, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere19);
        Geometry sphere20 = new Sphere(new Point(530, -530, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere20);
        Geometry sphere21 = new Sphere(new Point(550, -510, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20));
        scene.getGeometries().add(sphere21);


        //right arm
        Geometry sphere22 = new Sphere(new Point(-290, -460, -1000), 90).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere22);
        Geometry sphere23 = new Sphere(new Point(-310, -490, -1000), 90).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere23);
        Geometry sphere24 = new Sphere(new Point(-330, -520, -1000), 90).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere24);
        Geometry sphere25 = new Sphere(new Point(-350, -550, -1000), 90).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere25);
        Geometry sphere26 = new Sphere(new Point(-370, -580, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere26);
        Geometry sphere27 = new Sphere(new Point(-400, -600, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere27);
        Geometry sphere28 = new Sphere(new Point(-400, -620, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere28);
        Geometry sphere29 = new Sphere(new Point(-400, -650, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere29);
        Geometry sphere30 = new Sphere(new Point(-400, -670, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere30);
        Geometry sphere31 = new Sphere(new Point(-400, -700, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere31);
        Geometry sphere32 = new Sphere(new Point(-400, -730, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere32);
        Geometry sphere33 = new Sphere(new Point(-400, -760, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere33);
        Geometry sphere34 = new Sphere(new Point(-400, -790, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere34);
        Geometry sphere35 = new Sphere(new Point(-400, -820, -1000), 70).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere35);

        //legs

        //right leg


        Geometry sphere36 = new Sphere(new Point(-160, -1100, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere36);
        Geometry sphere37 = new Sphere(new Point(-160, -1090, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere37);
        Geometry sphere38 = new Sphere(new Point(-160, -1080, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere38);
        Geometry sphere39 = new Sphere(new Point(-160, -1070, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere39);
        Geometry sphere40 = new Sphere(new Point(-160, -1040, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere40);
        Geometry sphere41 = new Sphere(new Point(-160, -1010, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere41);
        Geometry sphere42 = new Sphere(new Point(-160, -980, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere42);
        Geometry sphere43 = new Sphere(new Point(-160, -1150, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere43);
        Geometry sphere44 = new Sphere(new Point(-160, -1190, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere44);


        //left leg

        Geometry sphere50 = new Sphere(new Point(160, -1100, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere50);
        Geometry sphere51 = new Sphere(new Point(160, -1090, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere51);
        Geometry sphere52 = new Sphere(new Point(160, -1080, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere52);
        Geometry sphere53 = new Sphere(new Point(160, -1070, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere53);
        Geometry sphere55 = new Sphere(new Point(160, -1040, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere55);
        Geometry sphere56 = new Sphere(new Point(160, -1010, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere56);
        Geometry sphere57 = new Sphere(new Point(160, -980, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere57);
        Geometry sphere58 = new Sphere(new Point(160, -1150, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere58);
        Geometry sphere59 = new Sphere(new Point(160, -1190, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere59);
        Geometry sphere60 = new Sphere(new Point(160, -950, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere60);
        Geometry sphere61 = new Sphere(new Point(160, -920, -1000), 80).
                setEmission(new Color(0, 204, 0)).
                setMaterial(new Material().setShininess(20).setKT(0));
        scene.getGeometries().add(sphere61);
        Triangle triangle1 = new Triangle(new Point(3500, 3500, -2000),
                new Point(-3500, -3500, -1000),
                new Point(3500, -3500, -2000));
        Color c2 = new Color(0, 0, 0);
        triangle1.setEmission(c2);
        Triangle triangle2 = new Triangle(new Point(3500, 3500, -2000),
                new Point(-3500, 3500, -1000),
                new Point(-3500, -3500, -1000));
        Color c3 = new Color(0, 0, 0);
        triangle2.setEmission(c3);
        Triangle triangle3 = new Triangle(new Point(500, -1000, 1000),
                new Point(750, 2000, -2000),
                new Point(750, -1000, -2000));
        triangle3.setEmission(new Color(20, 20, 20));
        Material m3 = new Material();
        m3.setKR(0.3);
        triangle3.setMaterial(m3);
       //scene.getGeometries().add(sphere);
        scene.getGeometries().add(triangle1);
        scene.getGeometries().add(triangle2);
        scene.getGeometries().add(triangle3);
        scene.getLights().add(
                new SpotLight(new Color(100, 100, 100), new Point(-200, -200, -150),
                new Vector(2, 2, -3)).setNarrowBeam(0.00001).setKl(0.1).setKq(0.000005));//not sure if the set narrowbeam and the set kq is accurate the
                                                                                              //the values might have to be swaped
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPDistance(600).setVPSize(1000, 1000);
        camera.setImageWriter(new ImageWriter("blue person", 1000, 1000)) //
                //.setPixels(5, 5)
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();

    }
}