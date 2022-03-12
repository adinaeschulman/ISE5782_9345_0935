package primitives;
import primitives.Point;
import java.util.Objects;
public class Vector extends Point {
    /**
     * the constructor of a vector given 3 different values
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x,double y,double z){
        super(x,y,z);
        if(xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("cannot create a vector 0");
    }

    /**
     * constructor given a single pont
     * @param xyz
     */
    public Vector(Double3 xyz){
        super(xyz);
        if(xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("cannot create a vector 0");
    }

    /**
     * adds 2 vectors together
     * @param vector
     * @return
     */
    public Vector add(Vector vector){
        return new Vector(new Point(xyz.add(vector.xyz)).xyz);
    }

    /**
     *
     * @param x
     * @return
     */
    public Vector scale(double x){
      Vector vector=new Vector(x* xyz.d1,x* xyz.d2,x* xyz.d3);
        if(vector.xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("cannot create a vector 0");
        return vector;
    }

    /**
     * checks of 2 vectors are the same
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(o==null|| getClass()!=o.getClass())
            return false;
        Vector vector=(Vector)o;
        return xyz.equals(vector.xyz);
    }

    /**
     * returns the hashcode of a vector
     * @return
     */
    @Override
    //need to fix
    public int hashCode(){
        return Objects.hash(xyz);
    }

    /**
     * finds the vector product between 2 vectors
     * @param v
     * @return
     */
    public Vector crossProduct(Vector v){
        Vector vector=new Vector((v.xyz.d3* xyz.d2-v.xyz.d2* xyz.d3),
                            -(v.xyz.d3* xyz.d1-v.xyz.d1* xyz.d3),
                                   (v.xyz.d2* xyz.d1-v.xyz.d1* xyz.d2));
        return vector;
    }

    /**
     * finds the lengthe of teh vector squared
     * @return
     */
    public double lengthSquared(){
        return xyz.d1*xyz.d1+xyz.d2*xyz.d2+xyz.d3*xyz.d3;
    }

    /**
     * returns the root of a vctor distanced that was squared
     * @return
     */
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * returns th vector that the distance from teh initial point is 1
     * @return
     */
    public Vector normalize(){

        return new Vector(xyz.reduce(length()));
    }

    /**
     * returns the product of the vector
     * @param vector
     * @return
     */
    public double dotProduct(Vector vector){
       return xyz.d1*vector.xyz.d1+xyz.d2*vector.xyz.d2+xyz.d3*vector.xyz.d3;

    }

    /**
     * prints the vector and its details
     * @return
     */
    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }


}
