package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * \interface for geometries that Intersectable
 */
public interface Intersectable {
    /**
     * return list of Intsersections with one or more geometries
     * @param ray
     * @return
     */
    List<Point3D> findIntersections(Ray ray);
}
