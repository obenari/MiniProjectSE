package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testGetNormal() {
        Sphere sphere = new Sphere(2, new Point3D(1,2,3));
        Point3D p1=new Point3D(3,2,3);
        Vector result=new Vector(2,0,0).normalize();
        Vector normal=sphere.getNormal(p1);
        assertEquals(result, normal,"Error - the normal to the sphere is not correct");
    }

    @Test
    void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))),"Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(),"Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result,"Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Point3D p3=new Point3D(0.57011620801911,0.69114547904559,0.58096286644412);
        result=sphere.findIntersections((new Ray(new Point3D(0.5, 0, 0),
                new Vector(0.07, 0.69, 0.58))));
        assertEquals(1, result.size(),"Wrong number of points TC03");
        assertEquals(p3, result.get(0),"wrong intesection TC03");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(4.5, -3.22, -0.23), new Vector(2.25, -1.61, -0.23))),"Ray's line out of sphere, TC04");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        Point3D p4=new Point3D(1.9093453613512,0.340052210444,0.239698785897807);
        result=sphere.findIntersections((new Ray(new Point3D(0.28,0.47,-0.51),
                new Vector(1.63, -0.13, 0.75))));
        assertEquals(1, result.size(),"Wrong number of points TC11");
        assertEquals(p4, result.get(0),"wrong intesection TC11");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(0.819710041202114,-0.30812218306596,0.93410719462989), new Vector(0.24, -4.14, -0.93))),"Ray's line out of sphere, TC12");


        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        Point3D p5=new Point3D(1,1,0);
        Point3D p6=new Point3D(1,-1,0);
        result=sphere.findIntersections((new Ray(new Point3D(1,2,0),
                new Vector(0,-1,0))));
        assertEquals(2, result.size(),"Wrong number of points TC13");
        assertEquals(p5, result.get(0),"wrong intesection TC13");
        assertEquals(p6, result.get(1),"wrong intesection TC13");

        // TC14: Ray starts at sphere and goes inside (1 points)
        Point3D p7=new Point3D(1,-1,0);
        result=sphere.findIntersections((new Ray(new Point3D(1,1,0),
                new Vector(0,-1,0))));
        assertEquals(1, result.size(),"Wrong number of points TC14");
        assertEquals(p7, result.get(0),"wrong intesection TC14");
        // TC15: Ray starts inside (1 points)
        Point3D p8=new Point3D(1,-1,0);
        result=sphere.findIntersections((new Ray(new Point3D(1,0.5,0),
                new Vector(0,-1,0))));
        assertEquals(1, result.size(),"Wrong number of points TC15");
        assertEquals(p8, result.get(0),"wrong intesection TC15");
        // TC16: Ray starts at the center (1 points)
        Point3D p9=new Point3D(1,-1,0);
        result=sphere.findIntersections((new Ray(new Point3D(1,0,0),
                new Vector(0,-1,0))));
        assertEquals(1, result.size(),"Wrong number of points TC16");
        assertEquals(p9, result.get(0),"wrong intesection TC16");
        // TC17: Ray starts at sphere and goes outside (0 points)
        result=sphere.findIntersections((new Ray(new Point3D(1,1,0),
                new Vector(0,1,0))));
        assertNull(result,"Wrong number of points TC17");
        // TC18: Ray starts after sphere (0 points)
        result=sphere.findIntersections((new Ray(new Point3D(1,2,0),
                new Vector(0,1,0))));
        assertNull( result,"Wrong number of points TC18");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result=sphere.findIntersections((new Ray(new Point3D(1,1,-1),
                new Vector(0,0,1))));
        assertNull(result,"Wrong number of points TC19");
        // TC20: Ray starts at the tangent point
        result=sphere.findIntersections((new Ray(new Point3D(1,1,0),
                new Vector(0,0,1))));
        assertNull(result,"Wrong number of points TC20");
        // TC21: Ray starts after the tangent point
        result=sphere.findIntersections((new Ray(new Point3D(1,1,1),
                new Vector(0,0,1))));
        assertNull(result,"Wrong number of points TC21");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result=sphere.findIntersections((new Ray(new Point3D(1,2,0),
                new Vector(0,0,1))));
        assertNull(result,"Wrong number of points TC22");

    }
}