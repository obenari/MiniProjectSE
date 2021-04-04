package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void testGetNormal() {
        //check if an exception throws when two points on the plain is the same
        try {
            Plane plane = new Plane(new Point3D(1, 2, 3), new Point3D(1, 2, 3), new Point3D(1, 0, 0));
            fail("the constructor not throw an exception when two points on the plain is the same");
        } catch (IllegalArgumentException ex) {
        }
        //check if an exception throws when all the points are on  the same line
        try {
            Plane plane = new Plane(new Point3D(1, 1, 1), new Point3D(2, 2, 2), new Point3D(3, 3, 3));
            fail("the constructor not throw an exception when all the points are on  the same line");
        } catch (IllegalArgumentException ex) {
        }
        Plane plane = new Plane(new Point3D(1, 1, 1), new Point3D(1, 2, 3), new Point3D(-1, 2, 3));
        Vector normal = plane.getNormal(null);
        Vector result = new Vector(0, -4, 2).normalize();
        assertTrue(normal.equals(result) || normal.equals(result.scale(-1)), "Error- the normal is not correct");
    }
}