package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * this class represent a sphere in 3D Cartesian coordinate
 * system
 *
 * @author
 */
public class Sphere extends Geometry {
    /**
     * the radius of the sphere
     */
    private final double _radius;
    /**
     * the center point of the sphere
     */
    private final Point3D _center;

    /**
     * constructor
     *
     * @param radius radius
     * @param center center
     */
    public Sphere(double radius, Point3D center) {
        _radius = radius;
        _center = center;
        initBox();
    }

    /**
     * initialize the box that bound the sphere
     */
    @Override
    void initBox() {
        Vector vector = new Vector(_radius, _radius, _radius);
        Point3D max = _center.add(vector);
        Point3D min = _center.add(vector.scale(-1));
        _box = new AABB(min, max);
    }

    /**
     * getter
     *
     * @return center
     */
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

    /**
     * calculate the normal to the required point on the sphere
     *
     * @param point the point on the sphere and on the normal
     * @return the normal to the point
     */
    @Override
    public Vector getNormal(Point3D point) {

        return point.subtract(_center).normalize();
    }

    /**
     * find the geoIntersections between the ray and the sphere that smaller then maxDistance
     *
     * @param ray ray
     * @param maxDistance the maximum distance
     * @return list of geoPoint intersection
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Point3D p0 = ray.getP0();
        Point3D O = _center;
        Vector V = ray.getDir();
        if (p0.equals(O)) {//if the ray start is in the center of the sphere
            return (List.of(new GeoPoint(this, ray.getPoint(_radius))));
        }
        Vector U = O.subtract(p0);
        double tm = V.dotProduct(U);
        double d = Math.sqrt((U.lengthSquared() - tm * tm));
        if (d >= _radius) { //there is no intersections
            return null;
        }
        double th = Math.sqrt(_radius * _radius - d * d);
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        //if t1 or t2 is negative, the intersection is before the ray start
        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
            Point3D p1 = ray.getPoint(t1);
            Point3D p2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
        }
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
            Point3D p2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this, p2));
        }
        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
            Point3D p1 = ray.getPoint(t1);
            return List.of(new GeoPoint(this, p1));
        }
        return null;
    }


}
