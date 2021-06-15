package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * interface for geometries that Intersectable
 */
public abstract class Intersectable {
    /**
     * button to turn on/ off the improvement in run time
     */
    static public boolean improvementIsOff = false;
    /**
     * the box that bounded the Intersectable
     */
    protected AABB _box;


    /**
     * this class is PDS
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * constructor
         *
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

    }

    /**
     * return list of Intersections with one or more geometries
     *
     * @param ray
     * @return
     */
    public List<Point3D> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }

    /**
     * return list of GeoIntersections with one or more geometries
     *
     * @param ray
     * @return
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * return list of GeoIntersections with one or more geometries
     * and  distance from the start of the ray is less than maxDistance
     *
     * @param ray
     * @param maxDistance
     * @return
     */
    abstract List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

    /**
     * initialize the box that bound the Intersectable
     */
    abstract void initBox();
}
