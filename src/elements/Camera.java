package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    private final Point3D _p0;
    private final Vector _vTo;
    private final Vector _vUp;
    private final Vector _vRight;
    private double _width;
    private double _height;
    private double _distance;

    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        _p0 = p0;
        //check if vUp and vTo are orthogonal
        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException("vTo and vUp aren't orthogonal ");
        }
        _vTo = vTo.normalized();
        _vUp = vUp.normalized();
        _vRight = _vTo.crossProduct(_vUp).normalize();
    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getvTo() {
        return _vTo;
    }

    public Vector getvUp() {
        return _vUp;
    }

    public Vector getvRight() {
        return _vRight;
    }

    public double getWidth() {
        return _width;
    }

    public double getHeight() {
        return _height;
    }

    public double getDistance() {
        return _distance;
    }

    // setters using  methode chaining

    /**
     * @param width
     * @param height
     * @return "this": camera current instance
     */
    public Camera setViewPlaneSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     * @param distance
     * @return "this": camera current instance
     */
    public Camera setDistance(double distance) {
        if(isZero(distance)){
            throw new IllegalArgumentException("distance cannot be zero");
        }
        _distance = distance;
        return this;
    }

    /**
     * create a ray from camera to pixel ij
     *
     * @param nX amount of columns
     * @param nY amount of rows
     * @param j  index of column
     * @param i  index of row
     * @return ray from camera to pixel ij
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D Pc = _p0.add(_vTo.scale(_distance));//the view plain center
        double Ry = _height / nY;//height pixel
        double Rx = _width / nX;//width pixel

        Point3D Pij = Pc;
        double xj = (j - (nX - 1) / 2d) * Rx;//horizontal  distance from the center (ציר אופקי)
        double yi = -(i - (nY - 1) / 2d) * Ry;//vertical  distance from the center (ציר אנכי)
      //in case that xj is zero, its no need to add xj to Pc
        if (!isZero(xj)) {
            Pij = Pij.add(_vRight.scale(xj));
        }
        //in case that yi is zero, its no need to add yi to Pc
        if (!isZero(yi)) {
            Pij = Pij.add(_vUp.scale(yi));
        }
        return new Ray(_p0, Pij.subtract(_p0));
    }

}
