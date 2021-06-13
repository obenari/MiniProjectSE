package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class AABB {
    final Point3D _min;
    final Point3D _max;


    public AABB(Point3D min, Point3D max) {
        _min = min;
        _max = max;
    }

    AABB
    Union(List<Point3D> points) {

        //  AABB bounds = new AABB();
        double xMin = 0;
        double yMin = 0;
        double zMin = 0;
        double xMax = 0;
        double yMax = 0;
        double zMax = 0;
        if (points.size() > 0) {
            Point3D point = points.get(0);
            xMin = point.getX();
            yMin = point.getY();
            zMin = point.getZ();
            xMax = point.getX();
            yMax = point.getY();
            zMax = point.getZ();

            //Iterate over points and log the biggest and smallest dimensions

            for (Point3D p : points) {
                if (p.getX() < xMin) {
                    xMin = p.getX();
                }
                if (p.getY() < yMin) {
                    yMin = p.getY();
                }
                if (p.getZ() < zMin) {
                    zMin = p.getZ();
                }

                if (p.getX() > xMax) {
                    xMax = p.getX();
                }
                if (p.getY() > yMax) {
                    yMax = p.getY();
                }
                if (p.getZ() > zMax) {
                    zMax = p.getZ();
                }

            }

        }

        return new AABB(new Point3D(xMin, yMin, zMin), new Point3D(xMax, yMax, zMax));
    }

    double surfaceArea(AABB A) {
        Vector d = A._max.subtract(A._min);
        return 2.d * (d.getX() * d.getY() + d.getY() * d.getZ() + d.getZ() * d.getX());
    }

    /**
     * this function check if there are intersection between the box and the ray
     * @param ray
     * @return
     */
    public boolean hit(Ray ray) {
        Point3D p0 = ray.getP0();
        Vector dir = ray.getDir();
        double[] xt = CheckAxis(1, p0.getX(), dir.getX());
        double[] yt = CheckAxis(2, p0.getY(), dir.getY());
        double[] zt = CheckAxis(3, p0.getZ(), dir.getZ());

        double tMin = Math.max(Math.max(xt[0], yt[0]), zt[0]);
        double tMax = Math.min(Math.min(xt[1], yt[1]), zt[1]);

        // List<Intersection> xs = new List<Intersection>();

        //Box not hit
        if (tMin > tMax) {
            return false;
        }

        //Box hit
        return true;
    }

    public double[] CheckAxis(int axis, double origin, double direction) {
        double[] t = new double[2];

        double tMinNumerator = 0.0;
        double tMaxNumerator = 0.0;

        switch (axis) {
            case 1:
                tMinNumerator = (_min.getX() - origin);
                tMaxNumerator = (_max.getX() - origin);
                break;
            case 2:
                tMinNumerator = (_min.getY() - origin);
                tMaxNumerator = (_max.getZ() - origin);
                break;
            case 3:
                tMinNumerator = (_min.getZ() - origin);
                tMaxNumerator = (_max.getZ() - origin);
                break;
        }

        //Infinities might pop here due to division by zero
        if (alignZero(direction) != 0) {
            t[0] = tMinNumerator / direction;
            t[1] = tMaxNumerator / direction;
        } else {
            t[0] = tMinNumerator * Double.POSITIVE_INFINITY;
            t[1] = tMaxNumerator * Double.POSITIVE_INFINITY;
        }

        if (t[0] > t[1]) {
            double temp = t[0];
            t[0] = t[1];
            t[1] = temp;
        }

        return t;
    }

}
