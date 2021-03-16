package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

class VectorTest {
    Vector v1 = new Vector(1d, 2d, 3d);
    Vector v2 = new Vector(-2d, -4d, -6d);
    Vector v3 = new Vector(0d, 3d, -2d);

    @Test
    void testZEROVector() {
        try { // test zero vector
            new Vector(0d, 0d, 0d);
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {
        }
    }

    @Test
    void testDotProduct() {
        //check if dotProduct for orthogonal vectors is 0
        assertEquals(0, alignZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");
        //check  dotProduct for vectors
        assertEquals(-28, v1.dotProduct(v2),0.00001, "ERROR: dotProduct() wrong value");
    }

    @Test
    void testSubtract() {
        //check subtract vector by itself
        try {
           Vector v= v1.subtract(v1);
            fail("ERROR: subtract() for same vector dont throw an exception ");
        }
        catch (Exception e){}

        //check if subtract vector is properly
        Vector v=(new Vector(1,1,1)).subtract(new Vector(2,2,2));
        assertEquals(v,new Vector(-1,-1,-1),"ERROR: subtract() wrong value");
    }

    @Test
    void testLengthSquared() {
        assertTrue(isZero(v1.lengthSquared() - 14),"ERROR: lengthSquared() wrong value");
    }

    @Test
    void testLength() {
      assertTrue(isZero(new Vector(0d, 3d, 4d).length() - 5),"ERROR: length() wrong value");
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(1d, 2d, 3d);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        assertEquals(vCopy,vCopyNormalize,"ERROR: normalize() function creates a new vector");
        assertTrue(isZero(vCopyNormalize.length() - 1),"ERROR: normalize() result is not a unit vector");
    }

    @Test
    void testNormalized() {
        Vector u = v1.normalized();
        assertTrue(isZero(u.length() - 1),"ERROR: normalized() result is not a unit vector");
        assertNotEquals(u , v1,"ERROR: normalizated() function does not create a new vector");
    }


    @Test
    void testCrossProduct() {
        //  Vector v1 = new Vector(1d, 2d, 3d);
        // Vector v2 = new Vector(-2d, -4d, -6d);

        // ============ Equivalence Partitions Tests ==============
        // Vector v3 = new Vector(0d, 3d, -2d);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-product of co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }

    }

    @Test
    void testScale() {
        //check scale vector by 0
        try {
            Vector v = v1.scale(0);
            fail("scale by 0 dont throws exception");
        }
        catch (IllegalArgumentException ex){ }
        //check if scale is work properly
        Vector v=new Vector(1,1,1);
        Vector u=new Vector(-1,-1,-1);
        assertEquals(v,u.scale(-1),"scale vector is not working");

    }
}