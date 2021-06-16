package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * the class represent an Axis Aligned Bounding Box (AABB)
 * An algorithm designed to improve running time,
 * instead of checking intersections between ray and all the geometries in the scene.
 * We'll put the geometries in boxes , unify boxes and see if there's an intersection between a ray and a box.
 */
public class AABB {
    /**
     * the minimum point in the box
     */
    final Point3D _min;
    /**
     * the maximum point in the box
     */
    final Point3D _max;
    /**
     * constructor
     *
     * @param min the minimum point in the box
     * @param max the maximum point in the box
     */
    public AABB(Point3D min, Point3D max) {
        _min = min;
        _max = max;
    }

    /**
     * calculate the min and max point to the new box that contain the boxes,and create the box
     *
     * @param boxes
     * @return
     */
    static AABB Union(List<AABB> boxes) {
        if (boxes.size() == 0) {
            return null;
        }
        double xMin = Double.POSITIVE_INFINITY;
        double yMin = Double.POSITIVE_INFINITY;
        double zMin = Double.POSITIVE_INFINITY;
        double xMax = Double.NEGATIVE_INFINITY;
        double yMax = Double.NEGATIVE_INFINITY;
        double zMax = Double.NEGATIVE_INFINITY;


        //Iterate over points and find the biggest and smallest points
        for (AABB b : boxes) {
            if (b._min.getX() < xMin) {
                xMin = b._min.getX();
            }
            if (b._min.getY() < yMin) {
                yMin = b._min.getY();
            }
            if (b._min.getZ() < zMin) {
                zMin = b._min.getZ();
            }
            if (b._max.getX() > xMax) {
                xMax = b._max.getX();
            }
            if (b._max.getY() > yMax) {
                yMax = b._max.getY();
            }
            if (b._max.getZ() > zMax) {
                zMax = b._max.getZ();
            }

        }
        return new AABB(new Point3D(xMin, yMin, zMin), new Point3D(xMax, yMax, zMax));

    }



    /**
     * this function check if there is intersection between the box and the ray
     * @param ray
     * @return
     */
    public boolean hit(Ray ray) {
        Point3D p0 = ray.getP0();
        double p0X = p0.getX();
        double p0Y = p0.getY();
        double p0Z = p0.getZ();
        Vector direction = ray.getDir();
        double dirX = direction.getX();
        double dirY = direction.getY();
        double dirZ = direction.getZ();

        double tmin ;
        double tmax ;

        if (dirX >= 0) {
            tmin = (_min.getX() - p0X) /dirX;
            tmax = (_max.getX() - p0X) / dirX;
        }
        else {//if the directionX is negative, then the min tx in the box is maximal
            tmin = (_max.getX() -p0X) /dirX;
            tmax = (_min.getX() - p0X) /dirX;
        }

        double tymin ;
        double tymax ;
        if (dirY >= 0) {
            tymin = (_min.getY() - p0Y) / dirY;
            tymax = (_max.getY() - p0Y) / dirY;
        }
        else {//if the directionY is negative, then the min tY in the box is maximal
            tymin = (_max.getY() - p0Y) / dirY;
            tymax = (_min.getY() - p0Y) / dirY;
        }
        //        |         |
        //miss!!  *t0x      |
        //     *  |         |
        //--*t1y--|---------|-------------
        //        |         |
        //        |         |
        //--------|---------|---*t0y----------
        //        |         | *   miss!!
        //        |         *t1x
        //        |         |
        if ((tmin > tymax) || (tymin > tmax))
            return false;

        if (tymin > tmin)
            tmin = tymin;

        if (tymax < tmax)
            tmax = tymax;

        double tzmin ;
        double tzmax ;

        if (dirZ >= 0) {
            tzmin = (_min.getZ() - p0Z) / dirZ;
            tzmax = (_max.getZ() - p0Z) / dirZ;
        }
        else {//if the directionZ is negative, then the min tZ in the box is maximal
            tzmin = (_max.getZ()- p0Z) /dirZ;
            tzmax = (_min.getZ() - p0Z) / dirZ;
        }

        if ((tmin > tzmax) || (tzmin > tmax))
            return false;


        return true;
    }



}
