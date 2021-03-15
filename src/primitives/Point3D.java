package primitives;


public class Point3D {
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    public final static Point3D ZERO = new Point3D(0d, 0d, 0d);

    /**
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
     * this ctor dont call to the ctor that get Coordinate in order to make the best performance
     *
     * @param x value for creating X coordinate
     * @param y value for creating Y coordinate
     * @param z value for creating Z coordinate
     */
    public Point3D(Double x, Double y, double z) {
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

    public Point3D add(Vector vector) {
        Double x = vector._head._x.coord + _x.coord;
        Double y = vector._head._y.coord + _y.coord;
        Double z = vector._head._y.coord + _z.coord;

        return new Point3D(x, y, z);
    }

    /**
     * this method return the point that we get after substract the request vector
     *
     * @param point3D the vector to subtract
     * @return
     */
    public Vector subtract(Point3D point3D) {
        Double x = _x.coord - point3D._x.coord;
        Double y = _y.coord - point3D._y.coord;
        Double z = _z.coord - point3D._y.coord;
        return new Vector(x, y, z);
    }

    /**
     * @param point3D
     * @return
     */
    public double distanceSquared(Point3D point3D) {
        return (_x.coord - point3D._x.coord) * (_x.coord - point3D._x.coord) +
                (_y.coord - point3D._y.coord) * (_y.coord - point3D._y.coord) +
                (_z.coord - point3D._z.coord) * (_z.coord - point3D._z.coord);
    }


    public double distance(Point3D point3D) {
        return Math.sqrt(this.distanceSquared(point3D));
    }

}
