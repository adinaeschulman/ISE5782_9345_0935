package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testAdd() {
      Point p = new Point(11, 22, 23);
//        if (!(p.add(new Vector(-11, -22, -33)).equals(new Point(0, 0, 0))))
//            out.println("ERROR: Point- add function doesn't work properly");
        assertEquals(new Vector(-11, -22, -33),p.add(new Vector(-11, -22, -33)).equals(new Point(0, 0, 0)),"\"ERROR: Point- add function doesn't work properly\"");
       // out.println("If there were no any other outputs - add test succeeded!");

    }

    @Test
    void testSubtract() {
        Point p = new Point(11, 22, 23);
//        if (!new Vector(1, 1, 1).equals(new Point(12, 23, 34).subtract(p)))
//            out.println("ERROR: Point- subtract function doesn't work properly");
        assertEquals(new Vector(1, 1, 1),new Point(12, 23, 34).subtract(p),"ERROR: Point- subtract function doesn't work properly");

//            out.println("If there were no any other outputs - subtract test succeeded!");
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
        if(!p1.equals(v))
            out.println("ERROR: Point- equals function doesn't work properly");
        if(!p1.equals(p2))
            out.println("ERROR: Point- equals function doesn't work properly");
        if(p1.equals(x))
            out.println("ERROR: Point- equals function doesn't work properly");
        out.println("If there were no any other outputs - equals tests succeeded!");

    }

    @Test
    void testDistanceSquared() {
        Point p1=new Point(1,1,1);
        Point p2=new Point(2,2,2);
        Point p3=new Point(0,0,0);
        Point p4=new Point(-2,-2,-2);
//        if(p2.distanceSquared(p1)!=3)
//            out.println("ERROR: Point- distanceSquared function doesn't work properly");
//        if(p2.distanceSquared(p3)!=12)
//            out.println("ERROR: Point- distanceSquared function doesn't work properly");
//        if(p2.distanceSquared(p2)!=0)
//            out.println("ERROR: Point- distanceSquared function doesn't work properly");
//        if(p2.distanceSquared(p4)!=48)
//            out.println("ERROR: Point- distanceSquared function doesn't work properly");
//        out.println("If there were no any other outputs - equals tests succeeded!");

        assertEquals(3,p2.distanceSquared(p1),"ERROR: Point- distanceSquared function doesn't work properly");
        assertEquals(12,p2.distanceSquared(p3),"ERROR: Point- distanceSquared function doesn't work properly");
        assertEquals(0,p2.distanceSquared(p2),"ERROR: Point- distanceSquared function doesn't work properly");
        assertEquals(48,p2.distanceSquared(p4),"ERROR: Point- distanceSquared function doesn't work properly");
        //out.println("If there were no any other outputs - equals tests succeeded!");

    }

    @Test
    void testDistance() {
        Point p1=new Point(1,1,1);
        Point p2=new Point(2,2,2);
        Point p3=new Point(0,0,0);
        Point p4=new Point(-2,-2,-2);
//        if(p2.distanceSquared(p1)!=Math.sqrt(3))
//            out.println("ERROR: Point- distanceSquared function doesn't work properly");
//        if(p2.distanceSquared(p3)!=Math.sqrt(12))
//            out.println("ERROR: Point- distanceSquared function doesn't work properly");
//        if(p2.distanceSquared(p2)!=0)
//            out.println("ERROR: Point- distanceSquared function doesn't work properly");
//        if(p2.distanceSquared(p4)!=Math.sqrt(48))
//            out.println("ERROR: Point- distanceSquared function doesn't work properly");
//        out.println("If there were no any other outputs - equals tests succeeded!");
       assertEquals(Math.sqrt(3),p2.distanceSquared(p1),"ERROR: Point- distanceSquared function doesn't work properly");
        assertEquals(Math.sqrt(12),p2.distanceSquared(p3),"ERROR: Point- distanceSquared function doesn't work properly");
        assertEquals(0,p2.distanceSquared(p2),"ERROR: Point- distanceSquared function doesn't work properly");
        assertEquals(Math.sqrt(48),p2.distanceSquared(p4),"ERROR: Point- distanceSquared function doesn't work properly");
    }
}