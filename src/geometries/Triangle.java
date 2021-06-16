package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * this class represent a tringle in 3D Cartesian coordinate
 * system
 *
 * @author Odelia Ben Ari
 */
public class Triangle extends Polygon {
    /**
     * constructor
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
        initBox();
    }

    /**
     * calculate the box that bound the triangle
     */
    @Override
    void initBox() {
        double xMax = -Double.POSITIVE_INFINITY;
        double yMax = -Double.POSITIVE_INFINITY;
        double zMax = -Double.POSITIVE_INFINITY;
        double xMin = Double.POSITIVE_INFINITY;
        double yMin = Double.POSITIVE_INFINITY;
        double zMin = Double.POSITIVE_INFINITY;
        for (Point3D p : vertices) {//find the min and max x,y,z
            if (xMax < p.getX())
                xMax = p.getX();
            if (yMax < p.getY())
                yMax = p.getY();
            if (zMax < p.getZ())
                zMax = p.getZ();

            if (xMin > p.getX())
                xMin = p.getX();
            if (yMin > p.getY())
                yMin = p.getY();
            if (zMin > p.getZ())
                zMin = p.getZ();

        }
        _box = new AABB(new Point3D(xMin, yMin, zMin), new Point3D(xMax, yMax, zMax));
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                '}';
    }

    /**
     * find the geoIntersections between the ray and the triangle that smaller then maxDistance
     *
     * @param ray
     * @param maxDistance
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        var intersection = plane.findGeoIntersections(ray, maxDistance);
        if (intersection == null)//there is no intersections with the plane
            return null;
        //now we check if the intersection with the plane is in the triangle
        Point3D p0 = ray.getP0();
        Point3D p1 = vertices.get(0);
        Point3D p2 = vertices.get(1);
        Point3D p3 = vertices.get(2);
        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector v3 = p3.subtract(p0);
        Vector N1 = v1.crossProduct(v2).normalize();
        Vector N2 = v2.crossProduct(v3).normalize();
        Vector N3 = v3.crossProduct(v1).normalize();
        Vector v = ray.getDir();
        double t1 = N1.dotProduct(v);
        double t2 = N2.dotProduct(v);
        double t3 = N3.dotProduct(v);
        //if t1, t2, t3 ha has the same sign, the intersection with the plane is in triangle
        if (t1 > 0 && t2 > 0 && t3 > 0 || t1 < 0 && t2 < 0 && t3 < 0)
            return List.of(new GeoPoint(this, intersection.get(0).point));
        return null;

    }

}
