package primitives;

/**
 * this class represent a point with 3 coordinate in 3D Cartesian coordinate
 * system
 *
 * @author
 */
public class Point3D {
    /**
     * the x value of the point
     */
    final Coordinate _x;
    /**
     * the y value of the point
     */
    final Coordinate _y;
    /**
     * the x value of the point
     */
    final Coordinate _z;
    /**
     * start of the axis
     */
    public final static Point3D ZERO = new Point3D(0d, 0d, 0d);

    /**
     * constructor
     *
     * @param x value for creating X coordinate
     * @param y value for creating Y coordinate
     * @param z value for creating Z coordinate
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = x;
        _y = y;
        _z = z;
    }

    /**
     * this constructor don't call to the constructor that get Coordinate in order to make  best performance
     *
     * @param x value for creating X coordinate
     * @param y value for creating Y coordinate
     * @param z value for creating Z coordinate
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) && _y.equals(point3D._y) && _z.equals(point3D._z);
    }

    @Override
    public String toString() {
        return "(" + _x + "," + _y + "," + _z + ')';
    }

    /**
     * this method calculate the point that received after adding the vector to thos point
     *
     * @param vector the vector to add to this point
     * @return new point after adding the vector from this point
     */
    public Point3D add(Vector vector) {
        Double x = vector._head._x.coord + _x.coord;
        Double y = vector._head._y.coord + _y.coord;
        Double z = vector._head._z.coord + _z.coord;

        return new Point3D(x, y, z);
    }

    /**
     * getter
     *
     * @return the value of x
     */
    public double getX() {
        return _x.coord;
    }

    /**
     * getter
     *
     * @return the value of y
     */
    public double getY() {
        return _y.coord;
    }

    /**
     * getter
     *
     * @return the value of z
     */
    public double getZ() {
        return _z.coord;
    }

    /**
     * this method return the point that we get after subtract the request vector
     *
     * @param point3D the vector to subtract
     * @return new point after subtracting the vector from this point
     */
    public Vector subtract(Point3D point3D) {
        Double x = _x.coord - point3D._x.coord;
        Double y = _y.coord - point3D._y.coord;
        Double z = _z.coord - point3D._z.coord;
        return new Vector(x, y, z);
    }

    /**
     * this method return the distance squared from the request point to this point
     *
     * @param point3D the point to calculate the distance from this point
     * @return the distance squared from the request point to this point
     */
    public double distanceSquared(Point3D point3D) {
        //the calculate is (x-x0)^2+(y-y0)^2+(z-z0)^2
        return (_x.coord - point3D._x.coord) * (_x.coord - point3D._x.coord) +
                (_y.coord - point3D._y.coord) * (_y.coord - point3D._y.coord) +
                (_z.coord - point3D._z.coord) * (_z.coord - point3D._z.coord);
    }

    /**
     * this method return the distance  from the request point to this point
     *
     * @param point3D the point to calculate the distance from this point
     * @return the distance  from the request point to this point
     */
    public double distance(Point3D point3D) {
        return Math.sqrt(this.distanceSquared(point3D));
    }


}
