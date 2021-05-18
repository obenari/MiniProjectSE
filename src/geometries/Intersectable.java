package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * \interface for geometries that Intersectable
 */
public interface Intersectable {
    /**
     * this class is PDS
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

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
     * @param ray
     * @return
     */
    default List<Point3D> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList .stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }

    /**
     * return list of GeoIntersections with one or more geometries
     * @param ray
     * @return
     */
    List<GeoPoint> findGeoIntersections(Ray ray);

}
