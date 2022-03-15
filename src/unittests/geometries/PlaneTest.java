

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

    @Test
    void testFindIntersections() {
        Ray ray=new Ray(new Point(1,0,0),new Vector(2,2,2));
        Plane plane=new Plane(new Point(1,0,0),new Point(0,1,0),new Point(0,0,1));
       assertNull(plane.findIntersections(ray),"write somthing....");
       assertEquals(List.of(new Point(1,1,1)),plane.findIntersections(ray), "Write somthing...");
    }
}
