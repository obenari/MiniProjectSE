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
     * @param point the required point
     * @return Vector from the light source to the point
     */
    public Vector getL(Point3D point);

    /**
     * calculate the distance between the light source and the required point
     * @param point   required point
     * @return the distance
     */
    double getDistance(Point3D point);
}
