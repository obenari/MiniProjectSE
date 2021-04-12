package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 *this interface represent a geometry
 * @author Odelia Ben Ari
 */
public interface Geometry extends Intersectable{
     Vector getNormal(Point3D point) ;
}
