package primitives;

import java.util.Objects;
public class Point {
    protected final Double3 xyz;



    /**
     * prints the point
     * @return
     */
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * constructor - intializes the point given 3 different dimensions
     * @param x
     * @param y
     * @param z
     */
    public Point(double x, double y, double z){

        xyz=new Double3(x,y,z);

    }

    /**
     * constructor given a ready point
     * @param xyz
     */
    public Point(Double3 xyz){
        this.xyz=xyz;
    }

    /**
     * adds the new point to a vector
     * @param vector
     * @return
     */
    public double add(Vector vector){
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * substract a point from vector
     * @param point
     * @return
     */
    public Vector subtract(Point point){return new Vector(xyz.subtract(point.xyz));}

    /**
     * checks if 2 points are eql tp eachother
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(o==null|| getClass()!=o.getClass())
            return false;
        Point point=(Point)o;
        return xyz.equals(point.xyz);
    }

    /**
     * retuns a hash code of a point
     * @return
     */
    @Override
    public int hashCode(){
        return Objects.hash(xyz);
    }

    /**
     * returns the squared distance between 2 different points
     * @param point
     * @return
     */
    public double distanceSquared(Point point){
        final double x1=xyz.d1;
        final double y1=xyz.d2;
        final double z1=xyz.d3;
        final double x2=point.xyz.d1;
        final double y2=point.xyz.d2;
        final double z2=point.xyz.d3;
        return((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1))+((z2-z1)*(z2-z1));

    }

    /**
     * returns the root of the given distance
     * @param point
     * @return
     */
    public double distance(Point point){
        return Math.sqrt(distanceSquared(point));
    }







}
