package unittests;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Dan
 */
public class CameraRayIntersectionsIntegrationTests {

    private static final Point ZERO_POINT =new Point(0,0,0) ;

    /**
     * Test helper function to count the intersections and compare with expected value
     *
     * @param expected amount of intersections
     * @param cam      camera for the test
     * @param geo      3D body to test the integration of the camer with
     * @param nX
     * @param nY
     * @author Dan Zilberstein
     */
    private void assertCountIntersections(int expected, Camera cam, Intersectable geo, int nX, int nY) {
        int count = 0;

        List<Point> allpoints = null;
        cam.setVPSize(3, 3);
        cam.setVPDistance(1);
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersections(cam.constructRay(nX, nY, j, i));
                if (intersections != null) {
                    if (allpoints == null) {
                        allpoints = new LinkedList<>();
                    }
                    allpoints.addAll(intersections);
                }
                count += intersections == null ? 0 : intersections.size();
            }
        }

        assertEquals(expected, count, "Wrong amount of intersections");
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    public void cameraRaySphereIntegration() {
        Camera cam1 = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0));

        Camera cam2 = new Camera((new Point(0, 0, 0.5)), new Vector(0, 0, -1), new Vector(0, -1, 0));


        // TC01: Small Sphere 2 points
        assertCountIntersections(2, cam1, new Sphere( new Point(0, 0, -3),1), 3, 3);

        // TC02: Big Sphere 18 points
        assertCountIntersections(18, cam2, new Sphere( new Point(0, 0, -2.5),2.5), 3, 3);

        // TC03: Medium Sphere 10 points
        assertCountIntersections(10, cam2, new Sphere(new Point(0, 0, -2),2), 3, 3);

        // TC04: Inside Sphere 9 points
        assertCountIntersections(9, cam2, new Sphere( new Point(0, 0, -1),4), 3, 3);

        // TC05: Beyond Sphere 0 points
        assertCountIntersections(0, cam1, new Sphere(new Point(0, 0, 1),0.5), 3, 3);

        assertCountIntersections(24, cam2, new Sphere( new Point(0, 0, -2.5),2.5), 4, 4);

    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void cameraRayPlaneIntegration() {
        Camera cam = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0))
                ;

        // TC01: Plane against camera 9 points
        assertCountIntersections(9, cam, new Plane(new Point(0, 0, -5), new Vector(0, 0, 1)), 3, 3);

        // TC02: Plane with small angle 9 points
        assertCountIntersections(9, cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 2)), 3, 3);

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(6, cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 3, 3);

        // TC04: Beyond Plane 0 points
        assertCountIntersections(6, cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 3, 3);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void cameraRayTriangleIntegration() {
        Camera cam = new Camera(ZERO_POINT ,new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small triangle 1 point
        assertCountIntersections(1, cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2)), 3, 3);

        // TC02: Medium triangle 2 points
        assertCountIntersections(2, cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)), 3, 3);
    }
}