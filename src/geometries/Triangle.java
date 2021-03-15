package geometries;

import primitives.Point3D;
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
}
