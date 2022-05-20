package unittests.geometries;
import geometries.Plane;
import geometries.Polygon;
import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class PlaneTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: when all if the given products are correct

            new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        // =============== Boundary Values Tests ==================

        // TC11: the 2 points are the same
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0)), //
                "Constructed a Plane with 2 od the same points");

        // TC12: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(2, 1, 0), new Point(5, 0, 3), new Point(3.5, 0.5, 1.5)), //
                "Constructed a Plane that all of the given points are on the same line");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormalPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }

//    @Test
//    void testFindIntersections() {
//        Ray ray=new Ray(new Point(1,0,0),new Vector(2,2,2));
//        Plane plane=new Plane(new Point(1,0,0),new Point(0,1,0),new Point(0,0,1));
//       assertNull(plane.findIntersections(ray),"write somthing....");
//       assertEquals(List.of(new Point(1,1,1)),plane.findIntersections(ray), "Write somthing...");
//    }
    /**
     *tests for {@link geometries.Plane#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {

        Plane pl = new Plane(new Point(0, 0, 1), new Vector(1, 1, 1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
        assertEquals(List.of(new Point(1, 0, 0)),
                pl.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(1, 0, 0))),
                "Bad plane intersection");

        // TC02: Ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                "Must not be plane intersection");

        // =============== Boundary Values Tests ==================
        // TC11: Ray parallel to plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(0, 1, -1))),
                "Must not be plane intersection");

        // TC12: Ray in plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, .5), new Vector(0, 1, -1))),
                "Must not be plane intersection");

        // TC13: Orthogonal ray into plane
        assertEquals(List.of(new Point(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1))),
                "Bad plane intersection");

        // TC14: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC15: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC16: Orthogonal ray from plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC17: Ray from plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 0))),
                "Must not be plane intersection");

        // TC18: Ray from plane's Q point
        assertNull(pl.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 0))),
                "Must not be plane intersection");

    }
}
