package renderer;

import elements.LightSource;
import geometries.Intersectable;

import static geometries.Intersectable.GeoPoint;

import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static primitives.Util.alignZero;

public class ImproveRayTracer extends RayTracerBase {
    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double RADIUS = 0.5;
    private static final double DISTANCE = 30;
    private static final double AMOUNT_OF_RAYS = 50;

    /**
     * initialize the field scene
     *
     * @param scene
     */
    public ImproveRayTracer(Scene scene) {

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
     *
     * @param geoPoint
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(Intersectable.GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = geoPoint.geometry.getEmission();
        color = color.add(calcLocalEffects(geoPoint, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));

    }

    /**
     * calculate the color of point according to refraction and reflection
     *
     * @param geoPoint
     * @param ray
     * @param level    depth of recursion
     * @param k        intensity of the refraction or reflection
     * @return
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        double kr = material.kR;
        double kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            List<Ray> reflectedRayList = constructReflectedRay(geoPoint, ray);
            List<Color> colorList = new LinkedList<>();
            for (Ray r : reflectedRayList) {
                GeoPoint reflectedPoint = findClosestIntersection(r);
                if (reflectedPoint != null) {
                    Color color1 = calcColor(reflectedPoint, r, level - 1, kkr).scale(kr);
                    colorList.add(color1);
                } else {
                    colorList.add(Color.BLACK);
                }
            }
            Color averageColor = Color.average(colorList);
            color = color.add(averageColor);//.scale(kr));
        }
        double kt = material.kT;
        double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            List<Ray> refractedRayList = constructRefractedRay(geoPoint, ray);
            List<Color> colorList = new LinkedList<>();
            for (Ray r : refractedRayList) {
                GeoPoint refractedPoint = findClosestIntersection(r);
                if (refractedPoint != null) {
                    Color color1 = calcColor(refractedPoint, r, level - 1, kkt).scale(kt);
                    colorList.add(color1);
                } else {
                    colorList.add(Color.BLACK);
                }
            }
            Color averageColor = Color.average(colorList);
            color = color.add(averageColor);//.scale(kt));
        }
        return color;
    }

    /**
     * calculate beam of Refracted Rays to the point
     *
     * @param geoPoint
     * @param ray
     * @return
     */
    private List<Ray> constructRefractedRay(GeoPoint geoPoint, Ray ray) {
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Ray refractedRay = new Ray(geoPoint.point, ray.getDir(), n);
        Point3D deltaPoint = refractedRay.getP0();//the same point in geoPoint with Sliding in the Delta
        Vector refractedVector = ray.getDir();
        //We'll create the center point of the circle through which construct beam of rays
        Point3D Pc = deltaPoint.add(ray.getDir().scale(DISTANCE));
        //create two vectors that orthogonal to refractedVector and they are also orthogonal
        Vector X = new Vector(-refractedVector.getZ(), 0, refractedVector.getX()).normalize();
        Vector Y = X.crossProduct(refractedVector).normalize();
        List<Ray> rayList = new LinkedList<>();
        rayList.add(refractedRay);
        for (int i = 0; i < AMOUNT_OF_RAYS; i++) {
            double cos = Math.random() * 2 - 1;//value between -1 to 1
            double sin = Math.sqrt(1 - cos * cos);
            double d = Math.random() * 2 * RADIUS - RADIUS;//value between -Radius to Radius
            double x = cos * d;
            double y = sin * d;
            Point3D point = Pc;
            if (alignZero(x) != 0) {
                point = Pc.add(X.scale(x));
            }
            if (alignZero(y) != 0) {
                point = Pc.add(Y.scale(y));
            }
            refractedRay = new Ray(deltaPoint, point.subtract(geoPoint.point));////////////////
            //check if the new ray is a reflection ray
            double t = ray.getDir().dotProduct(n) * refractedRay.getDir().dotProduct(n);
            if (alignZero(t) > 0) {
                rayList.add(refractedRay);
            }
        }
        return rayList;
    }

    /**
     * calculate beam of Reflected Rays to the point
     *
     * @param geoPoint
     * @param ray
     * @return
     */
    private List<Ray> constructReflectedRay(GeoPoint geoPoint, Ray ray) {
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDir();
        LinkedList<Ray> rayList = new LinkedList<Ray>();
        //if v is orthogonal to n the  reflected ray direction is in the same  direction of v
        //therefore there is no beam of rays
        if (alignZero(n.dotProduct(v)) == 0) {
            rayList.add(new Ray(geoPoint.point, v, n));
            return rayList;
        }
        Vector reflectedVector = v.subtract(n.scale(2 * v.dotProduct(n)));//the reflected vector
        Ray reflectedRay = new Ray(geoPoint.point, reflectedVector, n);//
        rayList.add(reflectedRay);
        Point3D deltaPoint = reflectedRay.getP0();//the same point in geoPoint with Sliding in the Delta
        //We'll create the center point of the circle through which construct beam of rays
        Point3D Pc = deltaPoint.add(reflectedVector.scale(DISTANCE));
        //create two vectors that orthogonal to refractedVector and they are also orthogonal
        Vector X = new Vector(-reflectedVector.getZ(), 0, reflectedVector.getX()).normalize();
        Vector Y = X.crossProduct(reflectedVector).normalize();
        for (int i = 0; i < AMOUNT_OF_RAYS; i++) {
            double cos = Math.random() * 2 - 1;//value between -1 to 1
            double sin = Math.sqrt(1 - cos * cos);
            double d = Math.random() * 2 * RADIUS - RADIUS;//value between -Radius to Radius
            double x = cos * d;
            double y = sin * d;
            Point3D point = Pc;
            if (alignZero(x) != 0) {
                point = Pc.add(X.scale(x));
            }
            if (alignZero(y) != 0) {
                point = Pc.add(Y.scale(y));
            }
            Ray ray1 = new Ray(deltaPoint, point.subtract(geoPoint.point));///////////
            //check if the new ray is a refraction ray
            double t = ray.getDir().dotProduct(n) * ray1.getDir().dotProduct(n);
            if (alignZero(t) < 0) {
                rayList.add(ray1);
            }
            //  rayList.add(new Ray(deltaPoint, point.subtract(point)));
        }
        return rayList;
    }


    /**
     * calculate the color of point according to diffusion, specular and shininess
     *
     * @param geoPoint
     * @param ray
     * @param k
     * @return
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, double k) {
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
                double ktr = transparency(lightSource, l, n, geoPoint);
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
     *
     * @param lightSource
     * @param l           light direction
     * @param n           normal of the point
     * @param geoPoint
     * @return the intensity of lightSource on point
     */
    private double transparency(LightSource lightSource, Vector l, Vector n, GeoPoint geoPoint) {
        Point3D point = geoPoint.point;
        Vector lightDirection = l.scale(-1).normalize();
        Ray lightRay = new Ray(point, lightDirection, n);//ray from the point to the ray
        double lightDistance = lightSource.getDistance(point);
        List<Intersectable.GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay, lightDistance);
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
