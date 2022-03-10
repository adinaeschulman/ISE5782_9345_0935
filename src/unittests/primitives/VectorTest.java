package unittests.primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
import primitives.Point;
import primitives.Vector;

class VectorTest {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    @Test
    void testAdd() {
        assertEquals(new Vector(2,4,6),v1.add(v1), "ERROR: add function does not work proper");
        //assertEquals(new Vector(2,4,6),v1.add(v1), "ERROR: add function does not work proper");
///helppppppppppp
        assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1,-2,-3)), "add()- not so sure what to write");


    }

    /**
     * helpppppppp
     */
    @Test
     void testTestEquals() {
        Point p1= new Point(1,2,3);
         assertEquals(v1,v1.equals(v1),"ERROR: Vector equals function doesn't work properly");
        assertEquals (new Vector(1, 1, 1),(new Point(2, 3, 4).subtract(p1)),"ERROR: Vector equals function doesn't work properly");

    }

    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 0, 0);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3), "crossProduct() for parallel vectors does not throw an exception");
    }


    @Test
    void testLengthSquared() {
    assertEquals(14,v1.lengthSquared(),"ERROR: LenghthSquared function does not work properly");
    }

    @Test
    void testLength() {
        assertEquals(5,new Vector(0,3,4).length(),"ERROR: length function does not work properly");
    }

    @Test
    void testNormalize() {
        Vector v= new Vector(1,2,3);
        Vector n=v.normalize();
        assertEquals(1d,n.length(),0.00001,"ERROR:the normalized vector is not the unit vector");
        assertThrows(IllegalArgumentException.class,
                ()->v.crossProduct(n),"ERROR: the normalized vector is not parallel to the original one");
        assertFalse(v.dotProduct(n)<0," ERROR: the normalized vector is opposite to the original one");
    }

    @Test
    void testDotProduct() {
        assertEquals(10,new Vector(0,3,4).dotProduct(new Vector(1,2,1)),"ERROR:  DotProduct function does not work properly");
        assertEquals (0,v1.dotProduct(v3),"ERROR: dotProduct() for orthogonal vectors is not zero");
        //if (!isZero(v1.dotProduct(v2) + 28))
        //    out.println("ERROR: dotProduct() wrong value");
    }

    @Test
    public void testScaleEP() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(2, 4, 6),
                new Vector(1, 2, 3).scale(2),
                "Wrong vector scale");
    }

    @Test
    public void testScaleBVA() {
        // =============== Boundary Values Tests ==================
        // TC11: test scaling to 0
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(1, 2, 3).scale(0d),
                "Scale by 0 must throw exception");
    }
}

