package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void testFindIntersections() {
        //TC01= the list is empty
        Geometries geometries=new Geometries();
        List<Point3D> result=geometries.findIntersections(new Ray(new Point3D(0,0,1), new Vector(3,4,5)));
        assertNull(result, "wrong number of points TC01");
        //TC02= there is no intersections
        geometries.add(new Sphere(1, new Point3D(1,0,0)),
                new Plane(new Point3D(0,0,1),
                        new Vector(0,0,1)),
                new Triangle(new Point3D(1,0,0), new Point3D(0,1,0), new Point3D(0,0,1)));
        result=geometries.findIntersections(new Ray(new Point3D(100, 100, 100), new Vector(0,0,1)));
        assertNull(result, "wrong number of points TC02");
        //TC03= one geometry has intersection
        geometries=new Geometries(new Sphere(1, new Point3D(1,0,0)),
                new Plane(new Point3D(0,0,-100),
                        new Vector(0,0,1)),
                new Triangle(new Point3D(1,0,-100), new Point3D(0,1,-100), new Point3D(0,0,-100)));
        result=geometries.findIntersections((new Ray(new Point3D(0.5, 0, 0),
                new Vector(0.07, 0.69, 0.58))));
        assertEquals(result.size(),1, "wrong number of points TC03");
        //TC04= few geometries has intersections but not all of them
        geometries=new Geometries(new Sphere(1, new Point3D(1,0,0)),
                new Plane(new Point3D(0,0,1),
                        new Vector(0,0,1)),
                new Triangle(new Point3D(1,0,-100), new Point3D(0,1,-100), new Point3D(0,0,-100)));
        result=geometries.findIntersections((new Ray(new Point3D(0.5, 0, 0),
                new Vector(0.07, 0.69, 0.58))));
        assertEquals(result.size(),2, "wrong number of points TC04");
        //TC05= all the geometries has intersections
        geometries=new Geometries(new Sphere(1, new Point3D(1,0,0)),
                new Plane(new Point3D(0,0,1),
                        new Vector(0,0,1)),
                new Triangle(new Point3D(100,50,1), new Point3D(-50,100,1), new Point3D(50,-100,1)));
        result=geometries.findIntersections((new Ray(new Point3D(0.5, 0, 0),
                new Vector(0.07, 0.69, 0.58))));
        assertEquals(result.size(),3, "wrong number of points TC05");
    }
}