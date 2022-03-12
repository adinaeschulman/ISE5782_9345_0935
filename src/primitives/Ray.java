package primitives;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class Ray {
    private Point p0;
    private Vector dir;

    /**
     * constructor of a ray given 3 different dimensions
     * @param xyz
     * @param x
     * @param y
     * @param z
     */
    public Ray(Double3 xyz,double x,double y,double z){
        p0=new Point(xyz);
        dir=new Vector(x,y,z);

    }

    /**
     * constructor initializes the basic parts of rhe ray
     * @param p
     * @param v
     */
    public Ray(Point p, Vector v){
        p0=p;
        dir=v;

    }


    /**
     * returns p0 - the initial point of the ray
     * @return
     */
    public Point getP0() {
        return p0;
    }

    /**
     * returns the dir- the initial vector in the ray
     * @return
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * prints the ray and its details
     * @return
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * checks if 2 rays are equal to eachother
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(o==null|| getClass()!=o.getClass())
            return false;
        Ray ray=(Ray)o;
        return dir.xyz.equals(ray.dir.xyz.d1) && dir.xyz.equals(ray.dir.xyz.d2)
                                              && dir.xyz.equals(ray.dir.xyz.d3)
                                              && p0==ray.p0;
    }

    /**
     * return the hahscode of the ray
     * @return
     */
    @Override
    //need to fix
    public int hashCode(){
        return Objects.hash(p0 );
    }
}
