package renderer;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

import elements.LightSource;
import geometries.Geometries;
import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * this class extends RayTracerBase
 */
public class BasicRayTracer extends RayTracerBase {

    /**
     * initialize the field scene
     * @param scene
     */
    public BasicRayTracer(Scene scene) {
        super(scene);
    }


    /**
     * calculate the color of point according to refraction and reflection
     * @param geoPoint
     * @param ray
     * @param level depth of recursion
     * @param k intensity of the refraction or reflection
     * @return
     */
    protected Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        double kr = material.kR;
        double kkr = k * kr;
        //If the effect of refraction is negligible then do not add the color
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(geoPoint, ray);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        double kt = material.kT;
        double kkt = k * kt;
        //If the effect of refraction is negligible then do not add the color
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(geoPoint, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null) {
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return color;
    }

    /**
     * calculate the Refracted Ray to the point
     * @param geoPoint
     * @param ray
     * @return
     */
    private Ray constructRefractedRay(GeoPoint geoPoint, Ray ray) {
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        return new Ray(geoPoint.point, ray.getDir(), n);
    }

    /**
     * calculate the Reflected Ray to the point
     * @param geoPoint
     * @param ray
     * @return
     */
    private Ray constructReflectedRay(GeoPoint geoPoint, Ray ray) {
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDir();
        //if v is orthogonal to n the  reflected ray direction is in the same  direction of v
        if (alignZero(n.dotProduct(v)) == 0) {
            return new Ray(geoPoint.point, v, n);
        }
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));//the reflected ray
        return new Ray(geoPoint.point, r, n);
    }

}
