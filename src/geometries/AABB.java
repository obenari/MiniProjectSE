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
     *
     * @param ray
     * @return
     */
    public boolean hit(Ray ray) {
        Point3D p0 = ray.getP0();
        double p0X = p0.getX();
        double p0Y = p0.getY();
        double p0Z = p0.getZ();
        Vector direction = ray.getDir();
        double directionX = direction.getX();
        double directionY = direction.getY();
        double directionZ = direction.getZ();

        double maxX;
        double minX;
//if the directionX is negative, then the min x in the box is maximal
        if (directionX < 0) {
            maxX = (_min.getX() - p0X) / directionX;
            //check if the geometry is behind the camera
            if (maxX <= 0)
                return false;
            minX = (_max.getX() - p0X) / directionX;
        } else if (directionX > 0) {//
            maxX = (_max.getX() - p0X) / directionX;
            if (maxX <= 0)
                return false;
            minX = (_min.getX() - p0X) / directionX;
        } else {
            if (p0X >= _max.getX() || p0X <= _min.getX())
                return false;
            else {
                maxX = Double.POSITIVE_INFINITY;
                minX = Double.NEGATIVE_INFINITY;
            }
        }

        double maxY;
        double minY;

//if the directionY is negative, then the min Y in the box is maximal
        if (directionY < 0) {
            maxY = (_min.getY() - p0Y) / directionY;
            //check if the geometry is behind the camera
            if (maxY <= 0)
                return false;
            minY = (_max.getY() - p0Y) / directionY;
        } else if (directionY > 0) {
            maxY = (_max.getY() - p0Y) / directionY;
            if (maxX <= 0)
                return false;
            minY = (_min.getY() - p0Y) / directionY;
        } else {
            if (p0Y >= _max.getY() || p0Y <= _min.getY())
                return false;
            else {
                maxY = Double.POSITIVE_INFINITY;
                minY = Double.NEGATIVE_INFINITY;
            }
        }

        double tempMax = maxY < maxX ? maxY : maxX;
        double tempMin = minY > minX ? minY : minX;
        tempMin = tempMin > 0 ? tempMin : 0;
        if (tempMax < tempMin)
            return false;

        double maxZ;
        double minZ;

//if the directionZ is negative, then the min Z in the box is maximal
        if (directionZ < 0) {
            maxZ = (_min.getZ() - p0Z) / directionZ;
            //check if the geometry is behind the camera
            if (maxZ <= 0)
                return false;
            minZ = (_max.getZ() - p0Z) / directionZ;
        } else if (directionZ > 0) {
            maxZ = (_max.getZ() - p0Z) / directionZ;
            if (maxX <= 0)
                return false;
            minZ = (_min.getZ() - p0Z) / directionZ;
        } else {
            if (p0Z >= _max.getZ() || p0Z <= _min.getZ())
                return false;
            else {
                maxZ = Double.POSITIVE_INFINITY;
                minZ = Double.NEGATIVE_INFINITY;
            }
        }

        tempMax = maxZ < tempMax ? maxZ : tempMax;
        tempMin = minZ > tempMin ? minZ : tempMin;
        if (tempMax < tempMin)
            return false;

        return true;

    }


}
