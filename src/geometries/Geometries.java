package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    private List<Intersectable> _listOfGeometries = null;

    public Geometries() {
        _listOfGeometries=new LinkedList<>();
        //we choose linkedList because we'll have to go through the list iteratively
        //So it is better to save running time of adding an item.
    }

    public Geometries(Intersectable... listOfGeometries) {
        _listOfGeometries=new LinkedList<>();
        add(listOfGeometries);
    }
    public void add(Intersectable... geometries){
        Collections.addAll(_listOfGeometries,geometries );
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result=null;
        for(Intersectable geo: _listOfGeometries){
            List<Point3D> geoPoints=geo.findIntersections(ray);
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
