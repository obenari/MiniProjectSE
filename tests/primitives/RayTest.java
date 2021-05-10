package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testGetClosestPoint() {
        Ray ray=new Ray(new Point3D(1,1,1),new Vector(1,0,0));
        List<Point3D> lst=new LinkedList<Point3D>();
        lst.add(new Point3D(1.5,1,1));
        lst.add(new Point3D(1.2,1,1));
        lst.add(new Point3D(2,1,1));
        Point3D result=ray.getClosestPoint(lst);
        //test 01 closest point is in the middle of the list.
        assertEquals(new Point3D(1.2,1,1),result,"fail test 01");
        // BVA
        //test 02 lst is null
        lst=null;
         result=ray.getClosestPoint(lst);
        assertNull(result,"empty list doesn't return null");
        //test 03 closest point is in the first place of the list.
        lst=new LinkedList<Point3D>();
        lst.add(new Point3D(1.2,1,1));
        lst.add(new Point3D(1.5,1,1));
        lst.add(new Point3D(2,1,1));
        result=ray.getClosestPoint(lst);
        assertEquals(new Point3D(1.2,1,1),result,"fail test 03");
        //test 04 closest point is in the last place of the list.
        lst=new LinkedList<Point3D>();
        lst.add(new Point3D(1.5,1,1));
        lst.add(new Point3D(2,1,1));
        lst.add(new Point3D(1.2,1,1));
        result=ray.getClosestPoint(lst);
        assertEquals(new Point3D(1.2,1,1),result,"fail test 04");

    }
}