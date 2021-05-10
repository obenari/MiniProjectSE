package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * this class extends RayTracerBase
 */
public class BasicRayTracer extends RayTracerBase{
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
        List<Point3D> intersections = _scene.geometries.findIntersections(ray);
        if (intersections == null) return _scene.backGroundColor;
        Point3D closestPoint = ray.getClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * calculate the color of the point
     * @param point
     * @return the color of the point
     */
    private Color calcColor(Point3D point) {
        return _scene.ambientLight.getIntensity();
    }
}
