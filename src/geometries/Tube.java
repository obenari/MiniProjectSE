package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
*this class represent a tube in 3D Cartesian coordinate
 * system
 * @author Odelia Ben Ari
 */
public class Tube implements Geometry{
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
        return null;
    }
}
