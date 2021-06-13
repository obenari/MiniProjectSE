package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
/**
 *this interface represent a geometry
 * @author Odelia Ben Ari
 */
public abstract class Geometry extends Intersectable{

     protected Color _emission=Color.BLACK;



     protected Material _material=new Material();

//     public Geometry(AABB box) {
//          super(box);
//     }

     public Color getEmission() {
          return _emission;
     }

     /**
      * chaining methode
      * @param emission
      * @return
      */
     public Geometry setEmission(Color emission) {
          _emission = emission;
          return this;
     }
     public Geometry setMaterial(Material material) {
          _material = material;
          return this;
     }
     public Material getMaterial() {
          return _material;
     }


     public abstract Vector getNormal(Point3D point) ;

}
