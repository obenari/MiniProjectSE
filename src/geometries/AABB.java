package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @return the union box
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


        xMax = boxes.stream()
                .mapToDouble(e -> e._max.getX())
                .max()
                .orElse(-1);
        xMin = boxes.stream()
                .mapToDouble(e -> e._min.getX())
                .min()
                .orElse(-1);

        yMax = boxes.stream()
                .mapToDouble(e -> e._max.getY())
                .max()
                .orElse(-1);

        yMin = boxes.stream()
                .mapToDouble(e -> e._min.getY())
                .min()
                .orElse(-1);
        zMax = boxes.stream()
                .mapToDouble(e -> e._max.getZ())
                .max()
                .orElse(-1);

        zMin = boxes.stream()
                .mapToDouble(e -> e._min.getZ())
                .min()
                .orElse(-1);

        return new AABB(new Point3D(xMin, yMin, zMin), new Point3D(xMax, yMax, zMax));

    }


    /**
     * https://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-box-intersection
     * this function check if there is intersection between the box and the ray
     * credit for Amy Williams
     *
     * @param ray
     * @return if the ray has intersection with the box
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

        double tmin;
        double tmax;

        if (dirX >= 0) {
            tmin = (_min.getX() - p0X) / dirX;
            tmax = (_max.getX() - p0X) / dirX;
        } else {//if the directionX is negative, then the min tx in the box is maximal
            tmin = (_max.getX() - p0X) / dirX;
            tmax = (_min.getX() - p0X) / dirX;
        }

        double tymin;
        double tymax;
        if (dirY >= 0) {
            tymin = (_min.getY() - p0Y) / dirY;
            tymax = (_max.getY() - p0Y) / dirY;
        } else {//if the directionY is negative, then the min tY in the box is maximal
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

        double tzmin;
        double tzmax;

        if (dirZ >= 0) {
            tzmin = (_min.getZ() - p0Z) / dirZ;
            tzmax = (_max.getZ() - p0Z) / dirZ;
        } else {//if the directionZ is negative, then the min tZ in the box is maximal
            tzmin = (_max.getZ() - p0Z) / dirZ;
            tzmax = (_min.getZ() - p0Z) / dirZ;
        }

        if ((tmin > tzmax) || (tzmin > tmax))
            return false;


        return true;
    }


}
