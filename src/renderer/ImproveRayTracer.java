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

/**
 * rayTracer class that Improve glossy and blurry
 */
public class ImproveRayTracer extends RayTracerBase {
    /**
     * the radius size of the target circle through construct the beam of rays
     */
    private double RADIUS = 0.5;
    /**
     * the distance size between the point and the target circle through construct the beam of rays
     */
    private double DISTANCE = 30;
    /**
     * the amount of rays in the beam
     */
    private double AMOUNT_OF_RAYS = 50;

    /**
     * chaining methode
     *
     * @param RADIUS
     * @return
     */
    public ImproveRayTracer setRADIUS(double RADIUS) {
        this.RADIUS = RADIUS;
        return this;
    }

    /**
     * chaining methode
     *
     * @param DISTANCE
     * @return
     */
    public ImproveRayTracer setDISTANCE(double DISTANCE) {
        this.DISTANCE = DISTANCE;
        return this;
    }

    /**
     * chaining methode
     *
     * @param AMOUNT_OF_RAYS
     * @return
     */
    public ImproveRayTracer setAMOUNT_OF_RAYS(double AMOUNT_OF_RAYS) {
        this.AMOUNT_OF_RAYS = AMOUNT_OF_RAYS;
        return this;
    }

    /**
     * initialize the field scene
     *
     * @param scene
     */
    public ImproveRayTracer(Scene scene) {
        super(scene);
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
    protected Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        double kr = material.kR;
        double kkr = k * kr;
        //If the effect of reflection is negligible then do not add the color
        if (kkr > MIN_CALC_COLOR_K) {
            List<Ray> reflectedRayList = constructReflectedRay(geoPoint, ray);//create beam of reflected rays
            List<Color> colorList = new LinkedList<>();//create a list of colors that returned by these rays
            for (Ray r : reflectedRayList) {//run all over the rays and add the color of the ray intersection
                GeoPoint reflectedPoint = findClosestIntersection(r);
                if (reflectedPoint != null) {
                    Color color1 = calcColor(reflectedPoint, r, level - 1, kkr).scale(kr);
                    colorList.add(color1);
                } else {
                    colorList.add(_scene.backGroundColor);
                }
            }
            //calculate the average color
            Color averageColor = Color.average(colorList);
            color = color.add(averageColor);
        }
        double kt = material.kT;
        double kkt = k * kt;
        //If the effect of refraction is negligible then do not add the color
        if (kkt > MIN_CALC_COLOR_K) {
            List<Ray> refractedRayList = constructRefractedRay(geoPoint, ray);//create beam of refracted rays
            List<Color> colorList = new LinkedList<>();//create a list of colors that returned by these rays
            for (Ray r : refractedRayList) {//run all over the rays and add the color of the ray intersection
                GeoPoint refractedPoint = findClosestIntersection(r);
                if (refractedPoint != null) {
                    Color color1 = calcColor(refractedPoint, r, level - 1, kkt).scale(kt);
                    colorList.add(color1);
                } else {
                    colorList.add(Color.BLACK);
                }
            }
            //calculate the average color
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
            refractedRay = new Ray(deltaPoint, point.subtract(geoPoint.point));
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
            Ray ray1 = new Ray(deltaPoint, point.subtract(geoPoint.point));
            //check if the new ray is a refraction ray
            double t = ray.getDir().dotProduct(n) * ray1.getDir().dotProduct(n);
            if (alignZero(t) < 0) {
                rayList.add(ray1);
            }
            //  rayList.add(new Ray(deltaPoint, point.subtract(point)));
        }
        return rayList;
    }


}
