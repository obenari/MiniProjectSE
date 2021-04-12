package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * this class represent a tube in 3D Cartesian coordinate
 * system
 *
 * @author Odelia Ben Ari
 */
public class Tube implements Geometry {
    Ray _axisRay;
    double _radius;

    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    public Ray getAxisRay() {
        return _axisRay;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        //  t=v∙(p−p0)
        // p1=p0+t∙v
        //normal=p-p1
        double t = _axisRay.getDir().dotProduct(point.subtract(_axisRay.getP0()));
        if (isZero(t)) {
            return point.subtract(_axisRay.getP0()).normalize();
        }
        Point3D p1 = _axisRay.getP0().add(_axisRay.getDir().scale(t));
        return point.subtract(p1).normalize();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
