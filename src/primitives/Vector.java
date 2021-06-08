package primitives;

import static primitives.Util.*;
import static primitives.Point3D.ZERO;
/**
 **this class represent a vector in 3D Cartesian coordinate
 *  * system
 * @author Odelia Ben Ari and Talya Shmuelian
 */

public class Vector {
    /**
     * the head of teh vector
     */
    Point3D _head;

    /**
     * this constructor get the vector head
     * @param head the vector head
     */
    public Vector(Point3D head) {
        if (ZERO.equals(head))
            throw new IllegalArgumentException("cannot create the vector (0,0,0)");
        _head = head;
    }

    /**
     * this constructor don't call to the prev constructor in order to make  good performance
     * @param x the value of x coordinate in the vector
     * @param y the value of y coordinate in the vector
     * @param z the value of z coordinate in the vector
     */
    public Vector(double x, double y, double z) {
        Point3D head = new Point3D(x, y, z);
        if (ZERO.equals(head)) {
            throw new IllegalArgumentException("cannot create the vector (0,0,0)");
        }
        _head = head;
    }

    /**
     * this constructor don"t call to the prev constructor in order to make  good performance
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

    /**
     * get methode
     * @return _head
     */
    public Point3D getHead() {
        return _head;
    }

    /**
     * get methode
     * @return
     */
    public double getX() {

        return _head.getX();
    }

    /**
     * get methode
     * @return
     */
    public double getY() {

        return _head.getY();
    }

    /**
     * get methode
     * @return
     */
    public double getZ() {

        return _head.getZ();
    }

    /**
     * equal methode
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    @Override
    public String toString() {
        return  _head.toString();
    }

    /**
     * this method return the value of Scleric product between the request vector and this vector
     * @param v the vector to product with this vector
     * @return
     */
    public double dotProduct(Vector v) {
        //the calculate is x1*x2+y1*y2+z1*z2
        return _head._x.coord * v._head._x.coord +
                _head._y.coord * v._head._y.coord +
                _head._z.coord * v._head._z.coord;
    }

    /**
     * this method return a new vector that received  after subtract the request vector from this vector
     * @param other
     * @return
     */
    public Vector subtract(Vector other) {
        if (other.equals(this))
            throw new IllegalArgumentException("cannot subtract the vector" + other + " by the same vector");
        return new Vector(
                _head._x.coord - other._head._x.coord,
                _head._y.coord - other._head._y.coord,
                _head._z.coord - other._head._z.coord);
    }

    /**
     * this method return the vector length squared
     * @return
     */
    public double lengthSquared() {
        //calculate x*x+y*y+z*z
        return  _head._x.coord * _head._x.coord +
                _head._y.coord * _head._y.coord +
                _head._z.coord * _head._z.coord;
    }

    /**
     * this method return the vector length
     * @return
     */
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
        double x = _head._x.coord/len;
        double y = _head._y.coord/len;
        double z = _head._z.coord/len;
        _head = new Point3D(x, y, z);
        return this;
    }

    /**
     * return new vector in the same direction to this, but with length 1
     * @return this vector after change its length to 1
     */
    public Vector normalized() {
        Vector result = new Vector(_head);
        return result.normalize();
    }

    /**
     *this method return a new vector that vertical to this vector and the request vector
     * @param v
     * @return
     */
    public Vector crossProduct(Vector v) {
        //UxV= (Uy*Vz-Vy*Uz,-Ux*Vz+Vx*Uz,Ux*Vy-Vx*Uy)
        double x = _head._y.coord * v._head._z.coord - v._head._y.coord * _head._z.coord;
        double y = -_head._x.coord * v._head._z.coord + v._head._x.coord * _head._z.coord;
        double z = _head._x.coord * v._head._y.coord - v._head._x.coord * _head._y.coord;
        Point3D point3D = new Point3D(x, y, z);
        if (ZERO.equals(point3D))
            throw new IllegalArgumentException("the crossProduct return the zero vector");
        return new Vector(point3D);

    }

    /**
     * return a new vector after scale this vector by the request number
     * @param a the value to multiply
     * @return new vector after scale this vector by the request number
     */
    public Vector scale(double a) {
        if (isZero(a)) {
            throw new IllegalArgumentException("cannot scale by 0");
        }
        double x = _head._x.coord * a;
        double y = _head._y.coord * a;
        double z = _head._z.coord * a;
        return new Vector(x, y, z);

    }
}
