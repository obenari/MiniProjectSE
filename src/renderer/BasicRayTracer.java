package renderer;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

import elements.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * this class extends RayTracerBase
 */
public class BasicRayTracer extends RayTracerBase{
    private static final double DELTA = 0.1;

    /**
     * initialize the field scene
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
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return _scene.backGroundColor;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    /**
     * calculate the color of the point
     * @param geoPoint
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {

        return _scene.ambientLight.getIntensity()
                .add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint, ray));
    }

    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir ();
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
                if (unshaded(lightSource,geoPoint)) {
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    private boolean unshaded(LightSource lightSource, GeoPoint geoPoint) {
        Point3D point=geoPoint.point;
        Vector l=lightSource.getL(point).scale(-1).normalized();
        Vector n=geoPoint.geometry.getNormal(point);
        Vector delta=n.scale(n.dotProduct(l)>0 ? DELTA :-DELTA);
        point=point.add(delta);
        Ray lightRay=new Ray(point,l);//ray from the point to the ray
        List<GeoPoint > intersections = _scene.geometries.findGeoIntersections(lightRay,lightSource.getDistance(point));
        return intersections == null;
    }

    /**
     *calculate the specular color of specific light source
     * @param ks specular factor
     * @param l vector from the light source to the intersection
     * @param n normal to the intersection
     * @param v vector from the camera to the intersection
     * @param nShininess Shininess factor
     * @param lightIntensity the light color
     * @return the specular color
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

        Vector r=l.subtract(n.scale(2*(l.dotProduct(n)))).normalize();
        double minusVR=alignZero(v.normalized().scale(-1).dotProduct(r));////
        return lightIntensity.scale(ks*Math.max(0, Math.pow(minusVR,nShininess)));
    }

    /**
     * calculate the diffusive color of specific light source
     * @param kd diffusive factor
     * @param l vector from the light source to the intersection
     * @param n normal to the intersection
     * @param lightIntensity the light color
     * @return the diffusive color
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {

        return lightIntensity.scale(kd*Math.abs(l.dotProduct(n)));
    }
}
