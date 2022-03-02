package primitives;
//i think i need to dd all the distance funcs
//figure iuit the hashcode/2string story
//ask about gets
import java.util.Objects;
public class Point {
    protected final Double3 xyz;
    public Point(double x,double y,double z){
        xyz=new Double3(x,y,z);
    }
    public Point(Double3 xyz){
        this.xyz=xyz;
    }
    public Point add(Vector vector){
        return new Point(xyz.add(vector.xyz));
    }
    public Vector subtract(Point point){
        return new Vector(xyz.subtract(point.xyz));
    }
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


}
