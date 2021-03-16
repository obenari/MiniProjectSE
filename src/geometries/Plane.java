package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 *this class represent a plane in 3D Cartesian coordinate
 * system
 * @author Odelia Ben Ari
 */
public class Plane implements Geometry{
    Point3D _p0;
    Vector _normal;

    public Plane(Point3D p0, Vector normal) {
        _p0 = p0;
        _normal = normal.normalized();
    }
    public Plane(Point3D p1,Point3D p2, Point3D p3) {
        _p0=p1;
        //calculate the normal
        Vector v=p2.subtract(p1).normalize();
        Vector u=p3.subtract(p1).normalized();
        if(v.equals(u)||v.equals(u.scale(-1)))//because the vectors is normalized, it possible to check if they are equal
            throw new IllegalArgumentException("the plane cannot get 3 point in the same line");
        _normal = v.crossProduct(u).normalize();
    }

    public Point3D getP0() {
        return _p0;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }
    //public Vector getNormal() {
     //   return _normal;
   // }

    @Override
    public String toString() {
        return "Plane{" +
                "_p0=" + _p0 +
                ", _normal=" + _normal +
                '}';
    }
}
