package primitives;

import java.util.Objects;
public class Point {
     final Double3 xyz;

    public Double3 getXyz() {
        return xyz;
    }
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    public Point(double x, double y, double z){
        xyz=new Double3(x,y,z);
    }
    public Point(Double3 xyz){
        this.xyz=xyz;
    }
    public Point add(Vector vector){
        return new Point(xyz.add(vector.xyz));
    }
    public Vector subtract(Point point){return new Vector(xyz.subtract(point.xyz));}

    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(o==null|| getClass()!=o.getClass())
            return false;
        Point point=(Point)o;
        return xyz.equals(point.xyz);
    }
    @Override
    public int hashCode(){
        return Objects.hash(xyz);
    }
    public double distanceSquared(Point point){
        final double x1=xyz.d1;
        final double y1=xyz.d2;
        final double z1=xyz.d3;
        final double x2=point.xyz.d1;
        final double y2=point.xyz.d2;
        final double z2=point.xyz.d3;
        return((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1))+((z2-z1)*(z2-z1));

    }
    public double distance(Point point){
        return Math.sqrt(distanceSquared(point));
    }


}
