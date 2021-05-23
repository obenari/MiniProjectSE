package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import primitives.*;

import static primitives.Util.alignZero;

/**
 * this class represent a plane in 3D Cartesian coordinate
 * system
 *
 * @author Odelia Ben Ari
 */
public class Plane extends Geometry {
    Point3D _p0;
    Vector _normal;

    public Plane(Point3D p0, Vector normal) {
        _p0 = p0;
        _normal = normal.normalized();
    }

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        Vector v;
        Vector u;
        try {
            _p0 = p1;
            //calculate two vector on the plain
            v = p2.subtract(p1);
            u = p3.subtract(p1);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("two or more point are the same");
        }
        try {
            //calculate the normal
            _normal = v.crossProduct(u).normalize();
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("the plane cannot get 3 point in the same line");
        }
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

    /**
     * return list of GeoIntersections with one or more geometries
     * @param ray
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        if (_p0.equals(ray.getP0()))//if the start of the ray equals to the plane point there is no intersections
            return null;
        Vector V=_p0.subtract(ray.getP0());
        double denominator=alignZero(_normal.dotProduct(ray.getDir()));
        if (denominator==0)//the ray is parallel to the plane
            return null;
        double t=(alignZero(_normal.dotProduct(V)))/(denominator);
        if (t>0 && alignZero (t-maxDistance ) <= 0 )
            return List.of(new GeoPoint(this,ray.getPoint(t)));

        return null;
    }

}
