package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PointTest {
    Point p1=new Point(1,1,1);
    Point p2=new Point(2,2,2);
    Point p3=new Point(0,0,0);
    Point p4=new Point(-2,-2,-2);
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that operation 'add' with points and vectors works correctly
        assertEquals(new Point(3,3,3),p1.add((Vector) p2),"ERROR: Point + Vector does not work correctly");
        Point p = new Point(11, 22, 23);
        assertTrue(isZero(p.add(new Vector(-11, -22, -23))),"\"ERROR: Point- add function doesn't work properly\"");

    }

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that operation 'add' with points and vectors works correctly
        Point p = new Point(11, 22, 23);
        assertEquals(new Vector(1, 1, 1),new Point(12, 23, 34).subtract(p),"ERROR: Point- subtract function doesn't work properly");
    }

    /**
     * helpppppppppp
     */
    @Test
    void testTestEquals() {
        Vector v=new Vector(1,1,1);
        Point p1=new Point(1,1,1);
        Point p2=new Point(1,1,1);
        int x=6;
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that operation 'equals'works correctly
        assertTrue(p1.equals(p2),"ERROR: Point- equals function doesn't work properly");
        // =============== Boundary Values Tests ==================
        // TC11: test checking if the operation 'equals' works for other types
        assertTrue(p1.equals(v),"ERROR: Point- equals function allows point to equal a different type ");
        assertTrue(p1.equals(x),"ERROR: Point- equals function allows point to equal a different type ");
       // out.println("If there were no any other outputs - equals tests succeeded!");

    }

    @Test
    void testDistanceSquared() {
        Point p1=new Point(1,1,1);
        Point p2=new Point(2,2,2);
        Point p3=new Point(0,0,0);
        Point p4=new Point(-2,-2,-2);
        assertEquals(3,p2.distanceSquared(p1),"ERROR: Point- distanceSquared function doesn't work properly");
        assertEquals(12,p2.distanceSquared(p3),"ERROR: Point- distanceSquared function doesn't work properly");
        assertEquals(0,p2.distanceSquared(p2),"ERROR: Point- distanceSquared function doesn't work properly");
        assertEquals(48,p2.distanceSquared(p4),"ERROR: Point- distanceSquared function doesn't work properly");
    }

    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that operation 'distance' with points works correctly
        Point p1=new Point(1,1,1);
        Point p2=new Point(2,2,2);
        Point p4=new Point(-2,-2,-2);
        assertEquals(Math.sqrt(3),p2.distanceSquared(p1),"ERROR: Point- distanceSquared function doesn't work properly");
        assertEquals(Math.sqrt(48),p2.distanceSquared(p4),"ERROR: Point- distanceSquared function doesn't work properly");
        // =============== Boundary Values Tests ==================
        // TC11: test checking if the operation 'equals' works for other types
        assertEquals(0,p2.distanceSquared(p2), "ERROR: Point- distanceSquared function doesn't work properly");
    }
}