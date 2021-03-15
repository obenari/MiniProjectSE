package primitives;

import static primitives.Util.*;
import static primitives.Point3D.ZERO;

public class Vector {
    Point3D _head;

    public Vector(Point3D head) {
        if (ZERO.equals(head))
            throw new IllegalArgumentException("cannot create the vector (0,0,0)");
        _head = head;
    }

    /**
     * this ctor dont call to the prev ctor in order to make a good performance
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector(Double x, Double y, Double z) {
        Point3D head = new Point3D(x, y, z);
        if (ZERO.equals(head))
            throw new IllegalArgumentException("cannot create the vector (0,0,0)");
        _head = head;
    }

    /**
     * this ctor dont call to the prev ctor in order to make a good performance
     *
     * @param x the value of x coordinate
     * @param y the value of y coordinate
     * @param z the value of z coordinate
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        Point3D head = new Point3D(x, y, z);
        if (ZERO.equals(head))
            throw new IllegalArgumentException("cannot create the vector (0,0,0)");
        _head = head;
    }

    public Point3D getHead() {
        return _head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }


    public double dotProduct(Vector v) {
        return _head._x.coord * v._head._x.coord +
                _head._y.coord * v._head._y.coord +
                _head._z.coord * v._head._z.coord;
    }

    public Vector subtruct(Vector other) {
        if (other.equals(this))
            throw new IllegalArgumentException("cannot subtract the vector" + other + " by the same vector");
        return new Vector(
                _head._x.coord - other._head._x.coord,
                _head._y.coord - other._head._y.coord,
                _head._z.coord - other._head._z.coord);
    }

    public double lengthSquared() {
        return _head._x.coord * _head._x.coord +
                _head._y.coord * _head._y.coord +
                _head._z.coord * _head._z.coord;
    }

    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * return this vector after dividing by its length
     *
     * @return this vector after change its length to 1
     */
    public Vector normalize() {
        double len = this.length();
        if (isZero(len))
            throw new ArithmeticException("the length is o");
        double x = _head._x.coord;
        double y = _head._y.coord;
        double z = _head._z.coord;
        x /= len;
        y /= len;
        z /= len;
        _head = new Point3D(x, y, z);
        return this;
    }
    /**
     * return new vector after dividing this vector by its length
     *
     * @return this vector after change its length to 1
     */
    public Vector normalized() {
//        double len = this.length();
//        if (isZero(len))
//            throw new ArithmeticException("the length is o");
//        double x = _head._x.coord;
//        double y = _head._y.coord;
//        double z = _head._z.coord;
//        x /= len;
//        y /= len;
//        z /= len;
//        return new Vector(x,y,z);
        Vector result=new Vector(_head);
        return result.normalize();
    }
    public Vector crossProduct(Vector v){
        double x=_head._y.coord*v._head._z.coord-v._head._y.coord*_head._z.coord;
        double y=-_head._x.coord*v._head._z.coord+v._head._x.coord*_head._z.coord;
        double z=_head._x.coord*v._head._y.coord-v._head._x.coord*_head._y.coord;
        Point3D point3D=new Point3D(x,y,z);
        if(ZERO.equals(point3D))
            throw new IllegalArgumentException("the crossProduct return the zero vector");
        return new Vector(point3D);

    }

}
