package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;
import primitives.Point3D;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * include the picture properties
 */
public class Scene {
    /**
     * the scene name
     */
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
     * collection of lights in scene
     */
    public List<LightSource> lights = new LinkedList<LightSource>();

    /**
     * initialize all the field to default values except the name
     *
     * @param name the scene name
     */
    public Scene(String name) {
        _name = name;
        geometries = new Geometries();
        backGroundColor = Color.BLACK;
        ambientLight = new AmbientLight();//initialize to black
    }

    /**
     * setters for chaining methode
     *
     * @param ambientLight  Environmental lighting
     * @return  Scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * setters for chaining methode
     *
     * @param color the background color
     * @return Scene
     */
    public Scene setBackground(Color color) {
        this.backGroundColor = color;
        return this;
    }
}
