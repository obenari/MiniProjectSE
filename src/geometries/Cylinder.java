package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *this class represent a finite cylinder in 3D Cartesian coordinate
 * system
 * @author Odelia Ben Ari
 */
public class Cylinder  extends Tube{
    double _height;

    /**
     * the constructor call to Tube constructor
     * @param axisRay
     * @param radius
     * @param height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        _height = height;
    }

    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                ", _axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

}
