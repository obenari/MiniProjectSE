package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() {
        //check the boundary case that the point and the ray head  is orthogonal to the axis ray

        Tube tube1 = new Tube(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)), 1);
        Vector result = tube1.getNormal(new Point3D(1, 0, 0));
        Vector normal = new Vector(1, 0, 0);
        assertEquals(result, normal, "the tube getNormal is not work");

        //check if getNormal() is working properly
        Tube tube2 = new Tube(new Ray(new Point3D(1, 1, 1), new Vector(0, 0, 1)), 1);
        normal = tube2.getNormal(new Point3D(1, 2, 2));
        result = new Vector(0, 1, 0);
        assertEquals(result, normal, "the tube getNormal is not work");
    }
}