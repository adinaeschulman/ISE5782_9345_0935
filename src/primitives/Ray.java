package primitives;
import primitives.Point;
import primitives.Vector;
import java.util.Objects;

import java.util.Objects;

public class Ray {
    private Point p0;
    private Vector dir;
    public Ray(Double3 xyz,double x,double y,double z){
        p0=new Point(xyz);
        dir=new Vector(x,y,z);

    }
    public Ray(Double3 p,Double3 v){
        p0=new Point(p);
        dir=new Vector(v);

    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

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
    @Override
    //need to fix
    public int hashCode(){
        return Objects.hash(p0 );
    }
}
