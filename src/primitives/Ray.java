package primitives;
import java.util.Objects;
/**
 *this class represent a ray in 3D Cartesian coordinate
   * system
 * @author Odelia Ben Ari
 */
public class Ray {
   final Point3D _p0;
    final Vector _dir;

    /**
     *
     * @param p0 the ray beginning point
     * @param dir the direction of the ray
     */
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalized();
    }


    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(_p0, ray._p0) && Objects.equals(_dir, ray._dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    /**
     * return new point in the direction of the ray,in the distance t
     * @param t
     * @return
     */
    public Point3D getPoint(double t){
        return _p0.add(_dir.scale(t));
    }
}
