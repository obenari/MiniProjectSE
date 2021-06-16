package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * this interface represent a geometry
 *
 * @author
 */
public abstract class Geometry extends Intersectable {
    /**
     * the basic color of the geometry
     */
    protected Color _emission = Color.BLACK;
    /**
     * the material of the geometry
     */
    protected Material _material = new Material();

    /**
     * getter
     *
     * @return emission
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * getter
     *
     * @return material
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * chaining methode
     *
     * @param emission color emitted by the Geometry
     * @return the Geometry object for chaining
     */
    public Geometry setEmission(Color emission) {
        _emission = emission;
        return this;
    }

    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    /**
     * return the normal to the required point in the geometry
     *
     * @param point
     * @return
     */
    public abstract Vector getNormal(Point3D point);

}
