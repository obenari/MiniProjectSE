package renderer;

import elements.LightSource;
import static geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

public class ImproveSoftShadowRayTracer extends RayTracerBase{
    /**
     * the radius size of the target circle through construct the beam of rays
     */
    private double RADIUS = 10;
    /**
     * the distance size between the point and the target circle through construct the beam of rays
     */
    private double DISTANCE = 30;
    /**
     * the amount of rays in the beam
     */
    private double AMOUNT_OF_RAYS = 50;
    /**
     * consructor
     *
     * @param scene
     */
    public ImproveSoftShadowRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * chaining methode
     *
     * @param RADIUS
     * @return
     */
    public ImproveSoftShadowRayTracer setRADIUS(double RADIUS) {
        this.RADIUS = RADIUS;
        return this;
    }

    /**
     * chaining methode
     *
     * @param DISTANCE
     * @return
     */
    public ImproveSoftShadowRayTracer setDISTANCE(double DISTANCE) {
        this.DISTANCE = DISTANCE;
        return this;
    }

    /**
     * chaining methode
     *
     * @param AMOUNT_OF_RAYS
     * @return
     */
    public ImproveSoftShadowRayTracer setAMOUNT_OF_RAYS(double AMOUNT_OF_RAYS) {
        this.AMOUNT_OF_RAYS = AMOUNT_OF_RAYS;
        return this;
    }
    @Override
    protected double transparency(LightSource lightSource, Vector lightDirection, Vector n, GeoPoint geoPoint) {
        //We'll create the center point of the circle through which construct beam of rays
        Point3D Pc = geoPoint.point.add(lightDirection.scale(DISTANCE));
        //Vector lightDirection=l.scale(-1);
        //create two vectors that orthogonal to refractedVector and they are also orthogonal
        Vector X = new Vector(-lightDirection.getZ(), 0, lightDirection.getX()).normalize();
        Vector Y = X.crossProduct(lightDirection).normalize();
        List<Vector> vectorList = new LinkedList<>();
        vectorList.add(lightDirection);
        for (int i = 0; i < AMOUNT_OF_RAYS; i++) {
            double cos = Math.random() * 2 - 1;//value between -1 to 1
            double sin = Math.sqrt(1 - cos * cos);
            double d = Math.random() * 2 * RADIUS - RADIUS;//value between -Radius to Radius
            double x = cos * d;
            double y = sin * d;
            Point3D targetPoint = Pc;
            if (alignZero(x) != 0) {
                targetPoint = Pc.add(X.scale(x));
            }
            if (alignZero(y) != 0) {
                targetPoint = Pc.add(Y.scale(y));
            }
            Vector v =  targetPoint.subtract(geoPoint.point);
            //check if the new ray is a reflection ray
            double t = lightDirection.dotProduct(n) * v.dotProduct(n);
            if (alignZero(t) >0) {
                vectorList.add(v);
            }
        }


        double ktr=0;
        for (Vector v:vectorList) {
           ktr+= super.transparency(lightSource,v,n,geoPoint);
        }
        return ktr/vectorList.size();
    }

}
