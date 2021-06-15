package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * this class represent spot light
 * has attenuation according to the distance and direction
 */
public class SpotLight extends PointLight {

    /**
     * the spot light direction
     */
    private final Vector _centerDirection;

    /**
     * constructor
     *
     * @param intensity
     * @param position
     * @param centerDirection
     */
    public SpotLight(Color intensity, Point3D position, Vector centerDirection) {
        super(intensity, position);
        _centerDirection = centerDirection.normalized();
    }

    /**
     * @param point3D
     * @return the light intensity on the point
     */
    @Override
    public Color getIntensity(Point3D point3D) {
        Vector l = getL(point3D);
        Color intensity = super.getIntensity(point3D);
        //calculate the attenuation according to the direction
        return intensity.scale(Math.max(0, l.dotProduct(_centerDirection)));

    }
}
