package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * the class represent a point light
 */
public class PointLight extends Light implements LightSource {
    /**
     * the position of the point light
     */
    private final Point3D _position;
    //-------Factors (k c , k l , k q ) for attenuation with distance -------
    /**
     * const factor
     */
    private double _kc = 1d;
    /**
     * linear factor
     */
    private double _kl = 0d;
    /**
     * qube factor
     */
    private double _kq = 0d;

    /**
     * constructor
     *
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        _position = position;
    }


    /**
     * chaining methods
     *
     * @param kc
     */
    public PointLight setKc(double kc) {
        _kc = kc;
        return this;
    }

    /**
     * chaining methods
     *
     * @param kl
     * @return
     */
    public PointLight setKl(double kl) {
        _kl = kl;
        return this;
    }

    /**
     * chaining methods
     *
     * @param kq
     * @return
     */
    public PointLight setKq(double kq) {
        _kq = kq;
        return this;
    }

    /**
     * getter
     *
     * @return kc
     */
    public double getKc() {
        return _kc;
    }

    /**
     * getter
     *
     * @return kl
     */
    public double getKl() {
        return _kl;
    }

    /**
     * getter
     *
     * @return kq
     */
    public double getKq() {
        return _kq;
    }

    /**
     * getter
     *
     * @return Position
     */
    public Point3D getPosition() {
        return _position;
    }


    /**
     * the light intensity on the point
     *
     * @param point
     * @return the light intensity on the point
     */
    @Override
    public Color getIntensity(Point3D point) {
        double d = point.distance(_position);
        //calculate the attenuation according to the distance
        double attenuationFactor = 1d / (_kc + _kl * d + _kq * d * d);
        return _intensity.scale(attenuationFactor);
    }

    /**
     * @param point
     * @return vector from the light source to the point
     */
    @Override
    public Vector getL(Point3D point) {

        return point.subtract(_position).normalize();
    }

    /**
     * calculate the distance between the light source and the required point
     *
     * @param point
     * @return
     */
    @Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }
}
