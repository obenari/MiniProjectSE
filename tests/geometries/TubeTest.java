package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() {
        //check the boundary case that the point and the ray head  is orthogonal to the axis ray
        try {
            Tube tube = new Tube(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)), 1);
            Vector v = tube.getNormal(new Point3D(1, 0, 0));
        }
        catch(Exception exception){
            fail("the tube getNormal is not work");
        }
        //check if getNormal() is working properly
        Tube tube = new Tube(new Ray(new Point3D(1, 1, 1), new Vector(0, 0, 1)), 1);
        Vector v = tube.getNormal(new Point3D(2, 2, 2));

    }
}