
import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

/**
 * this is our first image
 * we create a sunrise scene
 * including grass, birds and the sun
 * using plane, sphere and triangles
 */
public class FirstImageTest {
    @Test
    public void test()
    {

        //creating the scene
        Scene scene1 = new Scene.SceneBuilder("Test scene").build();
        Camera camera1 = new Camera(new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0))
                .setVPSize(150, 150)
                .setVPDistance(1000);


        //creating the elements

        //SKY (we chose to create it from sphere  to give the feeling of a sky dome)
        Geometry sky = new Sphere(new Point(0,0,-1000),1000)
                .setEmission(new Color(0,0,255))
                .setMaterial(new Material()
                        .setKd(0.5)
                        .setKs(0.5)
                        .setShininess(300));
        //adding the sky into the picture
        scene1.getGeometries().add(sky);

        //SUN
        Geometry sun=new Sphere(new Point(20,50,-50),85)
                .setEmission(new Color(255,255,0))
                .setMaterial(new Material()
                        .setKd(0.0)
                        .setKs(1.0)
                        .setShininess(300));
//                        .setKT(new Double3(1.5))
//                        .setKR(new Double3(0.4)));
        //adding the sun into the picture
        scene1.getGeometries().add(sun);

        //Grass
        Geometry grass=new Plane(new Point(0,-15,2),
                new Vector(0,1,2))
                .setEmission(new Color(0,204,0))
                .setMaterial(new Material()
                        .setKd(0.0)
                        .setKs(0.5)
                        .setShininess(300));
//                        .setKT(0.06)
//                        .setkR(new Double3(0.2)));
        //adding the grass into the picture
        scene1.getGeometries().add(grass);
//        //flower
//        for(int j=0;j<2;j++) {
//            for (int i = 0; i < 5; i++) {
//                Geometry flower = new Sphere(new Point(-30 + i * 30, -30+j*20, 10), 10)
//                        .setEmission(new Color(255, 192, 203))
//                        .setMaterial(new Material()
//                                .setKd(0.0)
//                                .setKs(1.0)
//                                .setShininess(300)
//                                .setKT(new Double3(0.6))
//                                .setKR(new Double3(0.4)));
//                //adding the sun into the picture
//                scene1.getGeometries().add(flower);
//            }
//        }

        //BIRDS
        //FIRST BIRD
        //left wing
        Geometry bird1left= new Triangle(new Point(20,25,2),
                new Point(20,30,2),
                new Point(1,40,2))
                .setEmission(new Color(java.awt.Color.black));

        //right wing
        Geometry bird1right= new Triangle(new Point(20,25,2),
                new Point(20,30,2),
                new Point(35,37,2))
                .setEmission(new Color(java.awt.Color.black));

        //SECOND BIRD
        //left wing
        Geometry bird2left= new Triangle(new Point(35,43.75,0),
                new Point(35,48,0),
                new Point(15,62,0))
                .setEmission(new Color(java.awt.Color.black));

        //right wing
        Geometry bird2right= new Triangle(new Point(35,43.75,0),
                new Point(35,48,0),
                new Point(55,57.35,0))
                .setEmission(new Color(java.awt.Color.black));

        //THIRD BIRD
        //left wing
        Geometry bird3left= new Triangle(new Point(48,18,-2),
                new Point(48,23.8,-2),
                new Point(35,30,0))
                .setEmission(new Color(java.awt.Color.black));

        //right wing
        Geometry bird3right= new Triangle(new Point(35,18,-2),
                new Point(35,23.8,-2),
                new Point(52,30,0))
                .setEmission(new Color(java.awt.Color.black));

        //adding the birds into the picture
        scene1.getGeometries().add(bird1left,bird1right,bird2left,bird2right,bird3left,bird3right);



        //light addition
        //SUNLIGHT
        scene1.getLights().add(
                new SpotLight(new Color(800, 400, 400),
                        new Point(0, 30, 50),
                        new Vector(0, -3, -2))
                        .setKl(4E-4).setKq(2E-5));

        scene1.getLights().add(
                new SpotLight(new Color(255,192,203),
                        new Point(0, 30, 50),
                        new Vector(0, -3, -2))
                        .setKl(4E-4).setKq(2E-5));

        //DAYLIGHT
        scene1.getLights().add(
                new SpotLight(new Color(0, 0, 800),
                        new Point(150, -30, 50),
                        new Vector(0, 3, -2))
                        .setKl(4E-4).setKq(2E-5));
        //SEA COLOR
        scene1.getLights().add(
                new SpotLight(new Color(java.awt.Color.cyan),
                        new Point(0,-10,20),
                        new Vector(0,3,-2)));


        //creating the picture
        ImageWriter imageWriter = new ImageWriter("Garden", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage(); //
        camera1.writeToImage(); //
    }
}