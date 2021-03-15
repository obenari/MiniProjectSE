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
        _normal = null;
    }

    public Point3D getP0() {
        return _p0;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_p0=" + _p0 +
                ", _normal=" + _normal +
                '}';
    }
}
