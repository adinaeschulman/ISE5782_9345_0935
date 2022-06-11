package geometries;

import primitives.Point;
import primitives.Vector;

public class Utils {
    /**
     *
     * @param canter
     * @param width
     * @param height
     * @return builds a rectangle
     */
    public static Polygon createRectangleY(Point canter, double width, double height) {
        return new Polygon(new Point[] {
                canter.add(new Vector(width/2, 0, height/2)),
                canter.add(new Vector(width/2, 0, -height/2)),
                canter.add(new Vector(-width/2, 0, -height/2)),
                canter.add(new Vector(-width/2, 0, height/2)),
        });
    }

    /**
     * 
     * @param center
     * @param width
     * @param height
     * @return
     */
    public static Polygon createRectangleZ(Point center, double width, double height) {
        return new Polygon(new Point[] {
                center.add(new Vector(width/2, height/2, 0)),
                center.add(new Vector(width/2, -height/2, 0)),
                center.add(new Vector(-width/2, -height/2, 0)),
                center.add(new Vector(-width/2, height/2, 0)),
        });
    }

}
