package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 *this class represent a sphere in 3D Cartesian coordinate
 * system
 * @author Odelia Ben Ari
 */
public class Sphere extends Geometry{
    private final double _radius;
    private final Point3D _center;

    public Sphere(double radius, Point3D center) {
        _radius = radius;
        _center = center;
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

        return point.subtract(_center).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        Point3D p0=ray.getP0();
        Point3D O=_center;
        Vector V=ray.getDir();
        if (p0.equals(O)){
            return (List.of(new GeoPoint(this,ray.getPoint(_radius))));
        }
        Vector U=O.subtract(p0);
        double tm= V.dotProduct(U);
        double d=Math.sqrt((U.lengthSquared()-tm*tm));
        if (d>=_radius){ //there is no intersections
            return null;
        }
        double th= Math.sqrt(_radius*_radius-d*d);
        double t1=alignZero(tm-th);
        double t2=alignZero(tm+th);

        if(t1>0 && t2>0 &&alignZero(t1-maxDistance)<=0&&alignZero(t2-maxDistance)<=0){
            Point3D p1= ray.getPoint(t1);
            Point3D p2= ray.getPoint(t2);
            return List.of(new GeoPoint(this,p1),new GeoPoint(this,p2));
        }
        if (t2>0&&alignZero(t2-maxDistance)<=0){
            Point3D p2= ray.getPoint(t2);
            return List.of(new GeoPoint(this,p2));
        }
        if (t1>0 &&alignZero(t1-maxDistance)<=0){
            Point3D p1= ray.getPoint(t1);
            return List.of(new GeoPoint(this,p1));
        }
        return null;
    }

}
