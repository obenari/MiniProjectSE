package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * this class keep a list of geometries.
 */
public class Geometries implements Intersectable{
    /**
     * keep a list of geometries.
     */
    private List<Intersectable> _listOfGeometries = null;
    /**
     * this constructor create an empty linkList
     */
    public Geometries() {
        _listOfGeometries=new LinkedList<>();
        //we choose linkedList because we'll have to go through the list iteratively
        //So it is better to save running time of adding an item.
    }

    /**
     * constructor that get unbounded amount geometries
     * @param listOfGeometries
     */
    public Geometries(Intersectable... listOfGeometries) {
        _listOfGeometries=new LinkedList<>();
        add(listOfGeometries);
    }

    /**
     * this methode add new geometries to the list of geometries.
     * @param geometries
     */
    public void add(Intersectable... geometries){
        Collections.addAll(_listOfGeometries,geometries );
    }


    /**
     * this methode calculate the intersections between ray and geometries
     * \and has an information about the geometry.
     * @param ray
     * @return list of geoPoint intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> result=null;
        for(Intersectable geo: _listOfGeometries){
            List<GeoPoint> geoPoints=geo.findGeoIntersections(ray);
            if(geoPoints != null){
                if (result==null){
                    result=new LinkedList<>();
                }
                result.addAll(geoPoints);
            }
        }
        return result;
    }


}
