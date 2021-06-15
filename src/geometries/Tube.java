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
public class Tube extends Geometry {
    /**
     * the direction of the tube
     */
    Ray _axisRay;
    /**
     * the radius of teh tube
     */
    double _radius;

    /**
     * constructor
     *
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    /**
     * getter
     *
     * @return
     */
    public Ray getAxisRay() {
        return _axisRay;
    }

    /**
     * getter
     *
     * @return
     */
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

    /**
     * clculate the normal to the point in the tube
     *
     * @param point
     * @return
     */
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

    /**
     * find the geoIntersections between the ray and the tube that smaller then maxDistance
     *
     * @param ray
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return null;
    }

    /**
     * calculate the box that bound the tube
     */
    @Override
    void initBox() {
    }
}
