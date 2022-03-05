package primitives;
import primitives.Point;

import java.util.Objects;
public class Vector extends Point {
    public Vector(double x,double y,double z){
        super(x,y,z);
        if(xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("cannot create a vector 0");
    }
    public Vector(Double3 xyz){
        super(xyz);
        if(xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("cannot create a vector 0");
    }
    public Vector add(Vector vector){
        return new Vector(new Point(xyz.add(vector.xyz)).xyz);
    }
    public Vector scale(double x){
      Vector vector=new Vector(x* xyz.d1,x* xyz.d2,x* xyz.d3);
        if(vector.xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("cannot create a vector 0");
        return vector;
    }
    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(o==null|| getClass()!=o.getClass())
            return false;
        Vector vector=(Vector)o;
        return xyz.equals(vector.xyz);
    }
    @Override
    //need to fix
    public int hashCode(){
        return Objects.hash(xyz);
    }
    public Vector crossProduct(Vector v){
        Vector vector=new Vector((v.xyz.d3* xyz.d2-v.xyz.d2* xyz.d3),
                                  -(v.xyz.d3* xyz.d1-v.xyz.d1* xyz.d3),
                                   (v.xyz.d2* xyz.d1-v.xyz.d1* xyz.d2));
        if(vector.xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("cannot create a vector 0");
        return vector;
    }
    public double lengthSquared(){
        return xyz.d1*xyz.d1+xyz.d2*xyz.d2+xyz.d3*xyz.d3;
    }
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
    public Vector normalize(){

        return new Vector(xyz.reduce(length()));


    }
    public double dotProduct(Vector vector){
       return xyz.d1*vector.xyz.d1+xyz.d2*vector.xyz.d2+xyz.d3*vector.xyz.d3;

    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

}
