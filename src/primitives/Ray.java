package primitives;

import static geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

/**
 * this class represent a ray in 3D Cartesian coordinate
 * system
 *
 * @author Odelia Ben Ari
 */
public class Ray {
    private static final double DELTA = 0.1;
    final Point3D _p0;
    final Vector _dir;

    /**
     * @param p0  the ray beginning point
     * @param dir the direction of the ray
     */
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalized();
    }

    public Ray(Point3D head, Vector direction, Vector normal){
        Vector delta = normal.scale(normal.dotProduct(direction) > 0 ? DELTA : -DELTA);
        _p0 = head.add(delta);
        _dir=direction;
    }
    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(_p0, ray._p0) && Objects.equals(_dir, ray._dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    /**
     * return new point in the direction of the ray,in the distance t
     *
     * @param t
     * @return
     */
    public Point3D getPoint(double t) {
        return _p0.add(_dir.scale(t));
    }

    public Point3D getClosestPoint(List<Point3D> intersections) {
        Point3D result = null;
        if (intersections == null) {
            return null;
        }
        double distance = Double.MAX_VALUE;
        for (Point3D p : intersections) {
            double newDis = _p0.distance(p);
            if (newDis < distance) {
                distance = newDis;
                result = p;
            }
        }
        return result;
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {

        GeoPoint result = null;
        if (geoPointList == null) {
            return null;
        }
        double distance = Double.MAX_VALUE;
        for (GeoPoint gp : geoPointList) {
            double newDis = _p0.distance(gp.point);
            if (newDis < distance) {
                distance = newDis;
                result = gp;
            }
        }
        return result;
    }
}
