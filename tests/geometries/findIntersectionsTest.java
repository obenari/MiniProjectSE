package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class findIntersectionsTest {
    @Test
    void findGeoIntersectionsTest() {
        Geometries geometries = new Geometries(new Sphere(0.5, new Point3D(2, 0, 0)),
                new Plane(new Point3D(3, 0, 0), new Vector(1, 0, 0)),
                new Triangle(new Point3D(5, 0, 3), new Point3D(5, -3, -3), new Point3D(5, 3, -3)));
        Ray ray = new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0));
        var intersections = geometries.findGeoIntersections(ray, 6);

        assertEquals(4, intersections.size(), "wrong number of points");

         ray = new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0));
        intersections = geometries.findGeoIntersections(ray, 6);

        assertNull(intersections,"the function dosent return null");
    }

}
