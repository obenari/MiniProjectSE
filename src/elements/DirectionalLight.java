package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * represent a Directional Light, the intensity isn't fade
 */
public class DirectionalLight extends Light implements LightSource {
    /**
     * direction vector
     */
    private final Vector _direction;

    /**
     * constructor
     *
     * @param intensity light color
     * @param direction common direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalized();
    }

    /**
     * return the light intensity
     *
     * @return
     */
    @Override
    public Color getIntensity(Point3D point) {
        return _intensity;
    }

    /**
     * return the vector from thr light to the required point
     *
     * @return
     */
    @Override
    public Vector getL(Point3D point) {
        return _direction;
    }

    /**
     * the distance between the light and the point
     *
     * @param point
     * @return
     */
    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }


}
