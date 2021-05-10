package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;
import primitives.Point3D;

import java.awt.*;

/**
 * include the picture properties
 */
public class Scene {
    private final String _name;
    /**
     * the the intensity of the basic light
     */
    public AmbientLight ambientLight;
    /**
     * backGround Color
     */
    public Color backGroundColor;
    /**
     * collection of geometries in scene
     */
    public Geometries geometries;

    /**
     * initialize all the field to default values except the name
     * @param name
     */
    public Scene(String name) {
        _name = name;
        geometries = new Geometries();
        backGroundColor= Color.BLACK;
        ambientLight=new AmbientLight();//initialize to black
    }
//          setters for chaining methode
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight=ambientLight;
        return this;
    }

    public Scene setBackground(Color color) {
        this.backGroundColor=color;
        return this;
    }
}
