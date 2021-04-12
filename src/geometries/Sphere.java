package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import primitives.*;
import java.util.List;

import static primitives.Util.alignZero;

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

        return point.subtract(_center).normalize();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0=ray.getP0();
        Point3D O=_center;
        Vector V=ray.getDir();
        if (p0.equals(O)){
            return (List.of(ray.getPoint(_radius)));
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

        if(t1>0 && t2>0){
            Point3D p1= ray.getPoint(t1);
            Point3D p2= ray.getPoint(t2);
            return List.of(p1,p2);
        }
        if (t2>0){
            Point3D p2= ray.getPoint(t2);
            return List.of(p2);
        }
        //it impossible t2<0 and t1>0
        return null;
    }

}
