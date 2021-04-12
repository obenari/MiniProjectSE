package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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

    @Test
    void testFindIntersections() {
        Plane plane= new Plane(new Point3D(0,1,0), new Vector(1,2,3));
        // ============ Equivalence Partitions Tests ==============
        //TC01= Ray intersects the plane (1 point)
        Point3D p1= new Point3D(-1.287162782969052, 1.09327382164018, 0.366871713229564);
        List<Point3D> result = plane.findIntersections(new Ray(new Point3D(-1.243658897979703, 2.519353947069239, 0),
                new Vector (-0.043503884989349,-1.426080125429058,0.366871713229564)));
        assertEquals(result.get(0), p1, "wrong intersection TC01");

        //TC02= Ray does not intersect the plane (0 points)
        result=plane.findIntersections(new Ray(new Point3D(-1.477355951220289, 2.08680247661885, 0),
                new Vector (-1.521156062490721, 2.735012975439876, 0)));
        assertNull(result, "wrong number of points TC02");
        // =============== Boundary Values Tests ==================
        //TC03= Ray is parallel to the plane and included in the plane (0 points)
        result=plane.findIntersections(new Ray(new Point3D(1,0.5,0),
                new Vector (0,-3,2)));
        assertNull(result, "wrong number of points TC03");
        //TC04=Ray is parallel to the plane and not included in the plane (0 points)
        result=plane.findIntersections(new Ray(new Point3D(1,1,1),
                new Vector (0,-3,2)));
        assertNull(result, "wrong number of points TC04");
        //TC05= Ray is orthogonal to the plane and start before the plane (1 point)
        Point3D p2= new Point3D(0,1,0);
        result=plane.findIntersections(new Ray(new Point3D(-1,-1,-3),
                new Vector (1,2,3)));
        assertEquals(result.get(0), p2, "wrong intersection TC05");
        //TC06= Ray is orthogonal to the plane and start in the plane
        result=plane.findIntersections(new Ray(new Point3D(1,0.5,0),
                new Vector (1,2,3)));
        assertNull(result, "wrong number of points TC06");
        //TC07= Ray is orthogonal to the plane and start after the plane
        result=plane.findIntersections(new Ray(new Point3D(1,3,3),
                new Vector (1,2,3)));
        assertNull(result, "wrong number of points TC07");
        //TC08= Ray is neither orthogonal nor parallel to and begins at the plane
        result=plane.findIntersections(new Ray(new Point3D(1,0.5,0),
                new Vector (1,2,5)));
        assertNull(result, "wrong number of points TC08");
        //TC09= Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (Q)
        result=plane.findIntersections(new Ray(new Point3D(0,1,0),
                new Vector (1,2,5)));
        assertNull(result, "wrong number of points TC09");
    }
}