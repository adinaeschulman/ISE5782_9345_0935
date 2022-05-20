/**
 *
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
public class TubeTests {
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Tube tube = new Tube( new Ray(new Point(0, -1, 0),new Vector(0, 0, 1)),1.0);
        Vector normal = tube.getNormal(new Point(0, 0.5, 2)).normalize();
        assertEquals(1, normal.length(), "Bad normal to tube");
    }
    @Test
    void testFindIntersections() {

    }

}
