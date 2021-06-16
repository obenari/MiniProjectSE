package renderer;

import elements.LightSource;

import static geometries.Intersectable.GeoPoint;

import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * this is an abstract class of rayTracer
 */
public abstract class RayTracerBase {
    /**
     * const for the first time in the calColor recursion
     */
    protected static final double INITIAL_K = 1.0;
    /**
     * how much times  calColor recursion will run
     */
    protected static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * the minimum attenuation factor that we wil calculate the color
     */
    protected static final double MIN_CALC_COLOR_K = 0.001;
    /**
     * the scene
     */
    protected final Scene _scene;

    /**
     * constructor
     *
     * @param scene the scene
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     * this methode get ray, and calculate the color of the closest intersection
     *
     * @param ray the ray from the camera to the specific pixel
     * @return the color of the closest point
     */
    public abstract Color traceRay(Ray ray);


}
