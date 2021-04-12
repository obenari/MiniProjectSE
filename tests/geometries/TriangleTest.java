package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {


    @Test
    void testFindIntersections() {

        Triangle triangle=new Triangle(new Point3D(0.711155977046377, -1.594304883357804, 2),
                new Point3D(-2.699822723486178, 2.151036259656399, 2),
                new Point3D(2.464586377565094, 2.754580052806656, 2));
        // ============ Equivalence Partitions Tests ==============
        //TC01=  Inside triangle (1 point)
        Point3D p1=new Point3D(0.5, 0.5, 2);
        List<Point3D> result = triangle.findIntersections(new Ray(new Point3D(0.5,0.5,1),
                new Vector(0,0,1)));
        assertEquals(result.get(0), p1, "wrong intersection TC01");

        //TC02= Outside against edge
        result = triangle.findIntersections(new Ray(new Point3D(0.1, 2.8, 1),
                new Vector(0,0,1)));
        assertNull(result, "wrong number of point TC02");
        //TC03=  Outside against vertex
        result = triangle.findIntersections(new Ray(new Point3D(2.5,2.8,1),
                new Vector(0,0,1)));
        assertNull(result, "wrong number of point TC03");

        // =============== Boundary Values Tests ==================
        //the ray begins "before" the plane
        //TC04= The intersection is on edge
        result = triangle.findIntersections(new Ray(new Point3D(1.571285887193745,0.539002494156465,1),
                new Vector(0,0,1)));
        assertNull(result, "wrong number of point TC04");

        //TC05= The intersection is in vertex
        result = triangle.findIntersections(new Ray(new Point3D(0.711155977046377, -1.594304883357804, 1),
                new Vector(0,0,1)));
        assertNull(result, "wrong number of point TC05");
        //TC06= The intersection is  on edge's continuation
        result = triangle.findIntersections(new Ray(new Point3D(-0.214921513619997, -3.891176460175766, 1),
                new Vector(0,0,1)));
        assertNull(result, "wrong number of point TC06");
    }
}