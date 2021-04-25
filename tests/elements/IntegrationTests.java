package elements;

import elements.Camera;
import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {
    /**
     * the function create rays through all the pixels in the view plain, and calculate all the intersections with geometry
     * @param size view plain width and height
     * @param camera
     * @param geometry
     * @return list of intersections with geometry
     */
    List<Point3D> listOfIntersectionsThroughAllPixels(int size, Camera camera, Geometry geometry) {
        List<Point3D> allPoints = null;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Ray ray = camera.constructRayThroughPixel(size, size, j, i);
                List<Point3D> lst = geometry.findIntersections(ray);
                if (lst != null) {
                    if (allPoints == null)
                        allPoints = new LinkedList<Point3D>();
                    allPoints.addAll(lst);
                }
            }
        }
        return allPoints;
    }

    @Test
    void TestSphereWithCamera() {
        //view plain size is odd

        //TC01= camera is before the sphere and one ray has intersection with the sphere(2 points)
        Sphere sphere = new Sphere(new Point3D(0, 0, -3), 1);
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
        List<Point3D> allPoints = listOfIntersectionsThroughAllPixels(3, camera, sphere);
        assertEquals(allPoints.size(), 2, "wrong number of points TC01");

        //TC02= camera is before the sphere and all rays has 2 intersection with the sphere(18 points)
        sphere = new Sphere(new Point3D(0, 0, -2.5), 2.5);
        camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
        allPoints = listOfIntersectionsThroughAllPixels(3, camera, sphere);
        assertEquals(allPoints.size(), 18, "wrong number of points TC02");

        //TC03= camera is before the sphere some of rays has 2 intersection with the sphere(10 points)
        sphere = new Sphere(new Point3D(0, 0, -2), 2);
        camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
        allPoints = listOfIntersectionsThroughAllPixels(3, camera, sphere);
        assertEquals(allPoints.size(), 10, "wrong number of points TC03");
//TC04= camera is in the sphere all rays has 1 intersection with the sphere(9 points)
        sphere = new Sphere(new Point3D(0, 0, 0), 4);
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
        allPoints = listOfIntersectionsThroughAllPixels(3, camera, sphere);
        assertEquals(allPoints.size(), 9, "wrong number of points TC04");

//TC05= camera is after the sphere all rays hasn't  intersection with the sphere(0 points)
        sphere = new Sphere(new Point3D(0, 0, 1), 0.5);
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
        allPoints = listOfIntersectionsThroughAllPixels(3, camera, sphere);
        assertNull(allPoints, "wrong number of points TC05");

        //------ view plain size is even--------------

        //TC06= camera is before the sphere and all rays has 2 intersection with the sphere(18 points)
        sphere = new Sphere(new Point3D(0, 0, -100), 100);
        camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(4, 4);
        allPoints = listOfIntersectionsThroughAllPixels(4, camera, sphere);
        assertEquals(allPoints.size(), 32, "wrong number of points TC06");

    }

    @Test
    void TestPlaneWithCamera() {
        //view plain size is odd

     //TC11= camera is before the plane,the plane is parallel to view plain, all rays hasn  intersection (9 points)
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
        Plane plane = new Plane(new Point3D(0, 0, -2), new Vector(0, 0, -1));
        List<Point3D> allPoints = listOfIntersectionsThroughAllPixels(3, camera, plane);
        assertEquals(allPoints.size(), 9, "wrong number of points TC11");
   //TC12= camera is before the plane,the plane isn't parallel to view plain, all rays hasn  intersection (9 points)
         camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
         plane = new Plane(new Point3D(0, 0, -2), new Vector(0.25, 0.25, -1));
        allPoints = listOfIntersectionsThroughAllPixels(3, camera, plane);
        assertEquals(allPoints.size(), 9, "wrong number of points TC12");
//TC13= camera is before the plane,the plane isn't parallel to view plain, 6 rays has an  intersection (6 points)
         camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
         plane = new Plane(new Point3D(0, 0, -2), new Vector(1, -1, 1));
        allPoints = listOfIntersectionsThroughAllPixels(3, camera, plane);
        assertEquals(allPoints.size(), 6, "wrong number of points TC13");
//TC14= camera is after the plane,the plane is parallel to view plain, all rays hasn't an  intersection (0 points)
         camera = new Camera(new Point3D(0,0,-3), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
         plane = new Plane(new Point3D(0, 0, -2), new Vector(0, 0, 1));
        allPoints = listOfIntersectionsThroughAllPixels(3, camera, plane);
        assertNull(allPoints, "wrong number of points TC14");



       //------ view plain size is even--------------
//TC15= camera is before the plane,the plane is parallel to view plain, all rays has an  intersection (16 points)
         camera = new Camera(new Point3D(0,0,0), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(4, 4);
         plane = new Plane(new Point3D(0, 0, -2), new Vector(0, 0, 1));
        allPoints = listOfIntersectionsThroughAllPixels(4, camera, plane);
        assertEquals(allPoints.size(),16, "wrong number of points TC15");

    }
    @Test
    void TestTriangleWithCamera() {
        //view plain size is odd

        //TC21= camera is before the triangle,the triangle is parallel to view plain,one ray has an  intersection (1 points)
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
        Triangle triangle = new Triangle(new Point3D(0, 1, -2), new Point3D(1, -1, -2),new Point3D(-1,-1,-2));
        List<Point3D> allPoints = listOfIntersectionsThroughAllPixels(3, camera, triangle);
        assertEquals(allPoints.size(), 1, "wrong number of points TC21");
//TC22= camera is before the triangle,the triangle is parallel to view plain,2 rays has an  intersection (2 points)
         camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
         triangle = new Triangle(new Point3D(0, 20, -2), new Point3D(1, -1, -2),new Point3D(-1,-1,-2));
         allPoints = listOfIntersectionsThroughAllPixels(3, camera, triangle);
         assertEquals(allPoints.size(), 2, "wrong number of points TC22");
//TC23= camera is after the triangle, all  rays hasn't an  intersection (0 points)
         camera = new Camera(new Point3D(0,0,-5), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
         triangle = new Triangle(new Point3D(0, 20, -2), new Point3D(1, -1, -2),new Point3D(-1,-1,-2));
         allPoints = listOfIntersectionsThroughAllPixels(3, camera, triangle);
         assertNull(allPoints, "wrong number of points TC23");
//------ view plain size is even--------------
        //TC24= camera is before the triangle, all  rays hasn an  intersection (16 points)
        camera = new Camera(new Point3D(0,0,0), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(4, 4);
        triangle = new Triangle(new Point3D(100, 20, -2), new Point3D(-100, 20, -2),new Point3D(50,-100,-2));
        allPoints = listOfIntersectionsThroughAllPixels(4, camera, triangle);
        assertEquals(allPoints.size(),16, "wrong number of points TC24");

    }


}
