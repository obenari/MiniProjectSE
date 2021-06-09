package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * todo
 */
public class PointLight extends Light implements LightSource {
    private final Point3D _position;
    private double _kc =1d;
    private double _kl =0d;
    private double _kq =0d;



    /**
     * chaining methods
     * @param kc
     */
    public PointLight setKc(double kc) {
        _kc = kc;
        return this;
    }
    public PointLight setKl(double kl) {
        _kl = kl;
        return this;
    }
    public PointLight setKq(double kq) {
        _kq = kq;
        return this;
    }
    public double getKc() {
        return _kc;
    }
    public double getKl() {
        return _kl;
    }

    public double getKq() {
        return _kq;
    }



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
    public Point3D getPosition() {
        return _position;
    }

    /**
     *
     * @param point
     * @return the light intensity on the point
     */
    @Override
    public Color getIntensity(Point3D point) {
        double d=point.distance(_position);
        //calculate the attenuation according to the distance
        double attenuationFactor=1d/(_kc+_kl*d+_kq*d*d);
        return _intensity.scale(attenuationFactor);
    }

    /**
     *
     * @param point
     * @return vector from the light source to the point
     */
    @Override
    public Vector getL(Point3D point) {

        return point.subtract(_position).normalize();
    }

    @Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }
}
