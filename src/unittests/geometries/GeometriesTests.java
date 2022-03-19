package unittests.geometries;



import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

    class GeometriesTests {

        @Test
        void testAdd() {
        }

        /**
         * Unit tests for geometries.Geometries class
         * @author Eliana Grajower & Mikhal Levy
         */
        class GeometriesTest {
            Geometries geometries = new Geometries(
                    new Sphere(new Point(1, 0.5, 1), 2),
                    new Plane(
                            new Point(-2, 0, 0),
                            new Point(0, 0, 4),
                            new Point(0, -2, 0)),
                    new Triangle(
                            new Point(1, 0, 0),
                            new Point(0.1, 0.5, 2.5),
                            new Point(-2, 0, 0)));

            /**
             * tests for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
             */
            // ============ Equivalence Partitions Tests ==============
            //TC01 ray intersect some of the geometries
            @Test
            void findIntersections1() {
                Ray ray = new Ray(new Point(0, -10, 5), new Vector(1, 10.5, -4));
                assertEquals(3, geometries.findIntersections(ray).size());
            }


            // =============== Boundary Values Tests ==================
            //TC11 ray intersect all the geometries
            @Test
            void findIntersections2() {
                Ray ray = new Ray(new Point(0, -10, 5), new Vector(0, 10, -4));
                assertEquals(4, geometries.findIntersections(ray).size());
            }

            //TC12 ray intersect only one of the geometries
            @Test
            void findIntersections3() {
                Ray ray = new Ray(new Point(0, -10, 5), new Vector(0, 10, -1));
                assertEquals(1, geometries.findIntersections(ray).size());
            }

            //TC13 ray not intersect the geometries
            @Test
            void findIntersections4() {
                Ray ray = new Ray(new Point(6, 2, 2), new Vector(0, 3, 1));
                assertNull(geometries.findIntersections(ray));
            }

            //TC14 geometries is empty
            @Test
            void findIntersections5() {
                assertNull(geometries, "empty geometries");
            }
        }
    }

