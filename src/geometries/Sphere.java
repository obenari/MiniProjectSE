package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 *this class represent a sphere in 3D Cartesian coordinate
 * system
 * @author Odelia Ben Ari
 */
public class Sphere implements Geometry{
 Point3D _center;
 double _radius;

    public Sphere(Point3D center, double radius) {
        _center = center;
        _radius = radius;
    }

    public Point3D getCenter() {
        return _center;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
