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




}
