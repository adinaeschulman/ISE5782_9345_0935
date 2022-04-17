package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray ray = (Ray)obj;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
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
    /**
     * Get point at specific distance in the ray direction
     * @param t distance for reaching new Point
     * @return new {@link Point}
     */
    public Point getPoint(double t)
    {
        if(isZero(t)){
            throw new IllegalArgumentException("t equal 0 cause illegal vector ZERO");
        }
        return p0.add(dir.scale(t));
    }
}
