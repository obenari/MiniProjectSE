package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 *this class represent a tringle in 3D Cartesian coordinate
 *  system
 * @author Odelia Ben Ari
 */
public class Triangle extends Polygon{

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1,p2,p3);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                '}';
    }
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        var intersection=plane.findGeoIntersections(ray);
        if (intersection==null)//there is no untersections with the plane
            return null;
        //now we check if the intersection with the plane is in the triangle
        Point3D p0= ray.getP0();
        Point3D p1=vertices.get(0);
        Point3D p2=vertices.get(1);
        Point3D p3=vertices.get(2);
        Vector v1=p1.subtract(p0);
        Vector v2=p2.subtract(p0);
        Vector v3=p3.subtract(p0);
        Vector N1=v1.crossProduct(v2).normalize();
        Vector N2=v2.crossProduct(v3).normalize();
        Vector N3=v3.crossProduct(v1).normalize();
        Vector v=ray.getDir();
        double t1= N1.dotProduct(v);
        double t2= N2.dotProduct(v);
        double t3= N3.dotProduct(v);
        if (t1>0 &&t2>0 &&t3>0 || t1<0 &&t2<0 &&t3<0)
            return List.of(new GeoPoint (this,intersection.get(0).point));
        return null;

    }

}
