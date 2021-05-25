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
     *
     * @param ray
     * @return the colour of the closest point
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return _scene.backGroundColor;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    /**
     * calculate the color of the point
     *
     * @param geoPoint
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = geoPoint.geometry.getEmission();
        color = color.add(calcLocalEffects(geoPoint, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));

    }

        private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = Color.BLACK;
        //Vector n = geoPoint.geometry.getNormal( geoPoint.point
        Material material = geoPoint.geometry.getMaterial();
        double kr = material.kR;
        double kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(geoPoint, ray);
            GeoPoint reflectedPoint = reflectedRay.findClosestGeoPoint(_scene.geometries.findGeoIntersections(reflectedRay));
            if (reflectedPoint != null) {
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        double kt = material.kT;
        double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(geoPoint, ray);
            GeoPoint refractedPoint = refractedRay.findClosestGeoPoint(_scene.geometries.findGeoIntersections(refractedRay));
            if (refractedPoint != null) {
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return color;
    }
//    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, double k) {
//        Color color = Color.BLACK;
//        Vector n = gp.geometry.getNormal(gp.point);
//        Material material = gp.geometry.getMaterial();
//        double kkr = k * material.kR;
//        if (kkr > MIN_CALC_COLOR_K) {
//            color = calcGlobalEffect(constructReflectedRay(gp, ray),
//                    level, material.kR, kkr);
//        }
//        double kkt = k * material.kT;
//        if (kkt > MIN_CALC_COLOR_K) {
//            color = color.add(calcGlobalEffect(constructRefractedRay(gp, ray), level, material.kT, kkt));
//        }
//        return color;
//    }
//
//    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
//        GeoPoint gp = ray.findClosestGeoPoint(_scene.geometries.findGeoIntersections(ray));
//        //findClosestIntersection (ray);
//        return (gp == null ? _scene.backGroundColor : calcColor(gp, ray, level - 1, kkx)).scale(kx);
//    }

    private Ray constructRefractedRay(GeoPoint geoPoint, Ray ray) {
        Vector n=geoPoint.geometry.getNormal(geoPoint.point);
        return new Ray(geoPoint.point, ray.getDir(),n);
    }

    private Ray constructReflectedRay(GeoPoint geoPoint, Ray ray) {
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDir();
        //if v is orthogonal to n the  reflected ray direction is in the same  direction of v
        if (alignZero(n.dotProduct(v)) == 0) {
            return new Ray(geoPoint.point, v,n);
        }
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));//the reflected ray
        return new Ray(geoPoint.point, r,n);
    }
//    private Color calcColor(GeoPoint geoPoint, Ray ray) {
//
//        return _scene.ambientLight.getIntensity()
//                .add(geoPoint.geometry.getEmission())
//                .add(calcLocalEffects(geoPoint, ray));
//    }

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
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
                if (unshaded(lightSource, geoPoint)) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    private boolean unshaded(LightSource lightSource, GeoPoint geoPoint) {
        Point3D point = geoPoint.point;
        Vector l = lightSource.getL(point).scale(-1).normalized();
        Vector n = geoPoint.geometry.getNormal(point);
//        Vector delta = n.scale(n.dotProduct(l) > 0 ? DELTA : -DELTA);
//        point = point.add(delta);
        Ray lightRay = new Ray(point, l,n);//ray from the point to the ray
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay, lightSource.getDistance(point));
        if (intersections == null) {
            return true;
        }
        for (var gp : intersections) {//if all kt is 0, the point is unshaded
            if (gp.geometry.getMaterial().kT != 0)
                return true;
        }
        return false;
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
}
