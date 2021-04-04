package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testGetNormal() {
        Sphere sphere = new Sphere(new Point3D(1,2,3),2);
        Point3D p1=new Point3D(3,2,3);
        Vector result=new Vector(2,0,0).normalize();
        Vector normal=sphere.getNormal(p1);
        assertEquals(result, normal,"Error - the normal to the sphere is not correct");
    }
}