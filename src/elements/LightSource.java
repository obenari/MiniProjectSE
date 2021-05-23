package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface that represent the light source in the scene
 */
public interface LightSource {
    /**
     *
     * @param point
     * @return
     */
    public Color getIntensity(Point3D point);

    /**
     *
     * @param point
     * @return Vector from the light source to the point
     */
    public Vector getL(Point3D point);

    double getDistance(Point3D point);
}
