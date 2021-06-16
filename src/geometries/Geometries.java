package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * this class keep a list of geometries.
 */
public class Geometries extends Intersectable {
    /**
     * keep a list of geometries.
     */
    private List<Intersectable> _listOfGeometries = null;

    /**
     * this constructor create an empty linkList
     */
    public Geometries() {
        _listOfGeometries = new LinkedList<>();
        //we choose linkedList because we'll have to go through the list iteratively
        //So it is better to save running time of adding an item.
    }

    /**
     * constructor that get unbounded amount geometries
     *
     * @param listOfGeometries
     */
    public Geometries(Intersectable... listOfGeometries) {
        _listOfGeometries = new LinkedList<>();
        add(listOfGeometries);
    }


    /**
     * this methode add new geometries to the list of geometries.
     *
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(_listOfGeometries, geometries);
        initBox();
    }


    /**
     * this methode calculate the intersections between ray and geometries
     * and has an information about the geometry.
     *
     * @param ray
     * @param maxDistance the maxDistance between the start of the ray and the geometries
     * @return list of geoPoint intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;
        for (Intersectable geo : _listOfGeometries) {
            if (improvementBVHIsOff || geo._box.hit(ray)) {//if the ray don't hit the box,no need to find intersection points
                List<GeoPoint> geoPoints = geo.findGeoIntersections(ray, maxDistance);
                if (geoPoints != null) {
                    if (result == null) {
                        result = new LinkedList<>();
                    }
                    result.addAll(geoPoints);
                }
            }
        }
        return result;
    }

    /**
     * initialize the new box from the listOfGeometries
     */
    @Override
    void initBox() {
        List<AABB> boxes = new LinkedList<AABB>();
        for (Intersectable g : _listOfGeometries) {
            boxes.add(g._box);
        }
        _box = AABB.Union(boxes);
    }

    /**
     * create BVH tree from the geometries
     */
    public void createBVHTree() {
        //remove all the infinity geometries from  _listOfGeometries, and add them in the end
        List<Intersectable> planeList = new LinkedList<>();
        for (Intersectable geo : _listOfGeometries) {
            if (geo instanceof Plane) {
                planeList.add(geo);
            }
        }
        _listOfGeometries.removeAll(planeList);

        //seek every time the closest boxes, and union them into a big box
        //repeat until there is one box left
        double distance = 0;
        Intersectable son1 = null;
        Intersectable son2 = null;
        while (_listOfGeometries.size() > 1) {//
            double minDistance = Double.POSITIVE_INFINITY;
            Intersectable geo1 = _listOfGeometries.get(0);
                for (Intersectable geo2 : _listOfGeometries) {
                    //find the minimum distance between 2 boxes
                    if (geo1 != geo2 && (distance = distance(geo1, geo2)) < minDistance) {
                        minDistance = distance;
                        son1 = geo1;
                        son2 = geo2;
                    }
                }

            Geometries tempGeometries = new Geometries(son1, son2);//union the two closest geometries
            _listOfGeometries.remove(son1);
            _listOfGeometries.remove(son2);
            _listOfGeometries.add(tempGeometries);

        }

        _listOfGeometries.addAll(planeList);//add the infinity geometries to the list
    }


    /**
     * calculate the distance between two middle point of boxes
     *
     * @param geo1
     * @param geo2
     * @return the distance
     */
    private double distance(Intersectable geo1, Intersectable geo2) {
        Point3D min1 = geo1._box._min;
        Point3D max1 = geo1._box._max;
        Point3D min2 = geo2._box._min;
        Point3D max2 = geo2._box._max;
        //calculate the middle point for each box
        double mid1X = (min1.getX() + max1.getX()) / 2d;
        double mid1Y = (min1.getY() + max1.getY()) / 2d;
        double mid1Z = (min1.getZ() + max1.getZ()) / 2d;
        double mid2X = (min2.getX() + max2.getX()) / 2d;
        double mid2Y = (min2.getY() + max2.getY()) / 2d;
        double mid2Z = (min2.getZ() + max2.getZ()) / 2d;

        Point3D midPoint1 = new Point3D(mid1X, mid1Y, mid1Z);
        Point3D midPoint2 = new Point3D(mid2X, mid2Y, mid2Z);
        //calculate the distance between the points
        return midPoint1.distance(midPoint2);

    }

}
