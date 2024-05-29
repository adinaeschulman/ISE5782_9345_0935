/**
 *
 */
package geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import geometries.*;
import primitives.*;

import java.util.List;

public class SphereTests {

@Test
public void testGetNormal() {
    // ============ Equivalence Partitions Tests ==============
    // TC01: There is a simple single test here
    Sphere sph = new Sphere(new Point(0, 0, 1), 1.0);
    assertEquals(new Vector(0, 0, 1), sph.getNormal(new Point(0, 0, 2)), "Bad normal to sphere");
}
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> list = List.of(p1, p2);


        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "must have 2 points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(list, result, "wrong intersections values");


        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(List.of(p2), sphere.findIntersections(new Ray(new Point(0.5, 0.5, 0), new Vector(3, 1, 0))),
                "there is 1 point, the ray begins inside the sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(3, 1, 0))),
                "there is 0 point, the ray begins after the sphere");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point(2, 0, 0)), sphere.findIntersections(new Ray(new Point(1, -1, 0), new Vector(1, 1, 0))),
                "there is 1 point, the ray begins at the sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 1, 0))),
                "there are 0 points, the ray begins at sphere and goes outside");


        // **** Group: Ray's line goes through the center

        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point(1, -2, 0), new Vector(0, 1, 0)));
        assertEquals(2, result.size(), "must have 2 points");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(1, -1, 0), new Point(1, 1, 0)),
                result, "there are 2 point, the ray begins before the sphere");


        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point(1, 1, 0)), sphere.findIntersections(new Ray(new Point(1, -1, 0), new Vector(0, 1, 0))),
                "there is 1 point, the ray begins before the sphere");

        // TC15: Ray starts inside (1 points)
        assertEquals(List.of(new Point(1, 1, 0)), sphere.findIntersections(new Ray(new Point(1, 0.5, 0), new Vector(0, 1, 0))),
                "there is 1 point, the ray begins inside the sphere");

        // TC16: Ray starts at the center (1 points)
        assertEquals(List.of(new Point(1, 1, 0)), sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0))),
                "there is 1 point, the ray begins at the center");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, 0))),
                "there is 0 point, the ray begins at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 2, 0), new Vector(0, 1, 0))),
                "there are 0 points, the ray begins after the sphere and goes outside");


        // **** Group: The ray is on the tangent line, 0 point

        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0))),
                "there are 0 points, the ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 0))),
                "there are 0 points, the ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 0, 0))),
                "there are 0 points, the ray starts after the tangent point");


        // **** Group: Ray is orthogonal to ray head

        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
        // center line
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(0, 0, 1))),
                "there are 0 points, the ray is orthogonal to ray head");

    }
}
