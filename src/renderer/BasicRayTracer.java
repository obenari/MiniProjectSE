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
    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * initialize the field scene
     *
     * @param scene
     */
    public BasicRayTracer(Scene scene) {

        super(scene);
    }

    /**
     * this methode get ray, and calculate the color of the closest intersection
     * @param ray
     * @return the colour of the closest point
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint == null) return _scene.backGroundColor;
        return calcColor(closestPoint, ray);
    }

    /**
     * calculate the ambientLight of the point
     *
     * @param geoPoint
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    /**
     * calculate the color of the point according to local and global effects
     * @param geoPoint
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = geoPoint.geometry.getEmission();
        color = color.add(calcLocalEffects(geoPoint, ray,k));
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));

    }

    /**
     * calculate the color of point according to refraction and reflection
     * @param geoPoint
     * @param ray
     * @param level depth of recursion
     * @param k intensity of the refraction or reflection
     * @return
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        double kr = material.kR;
        double kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(geoPoint, ray);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);//.findClosestGeoPoint(_scene.geometries.findGeoIntersections(reflectedRay));
            if (reflectedPoint != null) {
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        double kt = material.kT;
        double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(geoPoint, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);//.findClosestGeoPoint((_scene.geometries.findGeoIntersections(refractedRay));
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


    /**
     * calculate the color of point according to diffusion, specular and shininess
     * @param geoPoint
     * @param ray
     * @param k
     * @return
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray,double k) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                double ktr=transparency(lightSource,l,n,geoPoint);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

//    private boolean unshaded(LightSource lightSource, GeoPoint geoPoint) {
//        Point3D point = geoPoint.point;
//        Vector l = lightSource.getL(point).scale(-1).normalized();
//        Vector n = geoPoint.geometry.getNormal(point);
//        Ray lightRay = new Ray(point, l, n);//ray from the point to the ray
//        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay, lightSource.getDistance(point));
//        if (intersections == null) {
//            return true;
//        }
//        for (var gp : intersections) {//if all kt is 0, the point is unshaded
//            if (gp.geometry.getMaterial().kT != 0)
//                return true;
//        }
//        return false;
//    }

    /**
     * calculate the intensity of lightSource on point
     * @param lightSource
     * @param l light direction
     * @param n normal of the point
     * @param geoPoint
     * @return the intensity of lightSource on point
     */
    private double transparency(LightSource lightSource, Vector l, Vector n, GeoPoint geoPoint) {
        Point3D point = geoPoint.point;
        Vector lightDirection = l.scale(-1).normalize();
        Ray lightRay = new Ray(point, lightDirection, n);//ray from the point to the ray
        double lightDistance = lightSource.getDistance(point);
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay, lightDistance);
        if (intersections == null) {
            return 1d;
        }
        double ktr = 1d;
        for (var gp : intersections) {//if all kt is 0, the point is unshaded
            ktr *= gp.geometry.getMaterial().kT;
            if (ktr < MIN_CALC_COLOR_K) {
                return 0d;
            }
        }
        return ktr;
    }

    /**
     * calculate the specular color of specific light source
     *
     * @param ks             specular factor
     * @param l              vector from the light source to the intersection
     * @param n              normal to the intersection
     * @param v              vector from the camera to the intersection
     * @param nShininess     Shininess factor
     * @param lightIntensity the light color
     * @return the specular color
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

        Vector r = l.subtract(n.scale(2 * (l.dotProduct(n)))).normalize();
        double minusVR = alignZero(v.normalized().scale(-1).dotProduct(r));////
        return lightIntensity.scale(ks * Math.max(0, Math.pow(minusVR, nShininess)));
    }

    /**
     * calculate the diffusive color of specific light source
     *
     * @param kd             diffusive factor
     * @param l              vector from the light source to the intersection
     * @param n              normal to the intersection
     * @param lightIntensity the light color
     * @return the diffusive color
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {

        return lightIntensity.scale(kd * Math.abs(l.dotProduct(n)));
    }

    /**
     * @param ray
     * @return the closest geoPoint intersection
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(_scene.geometries.findGeoIntersections(ray));
    }
}
