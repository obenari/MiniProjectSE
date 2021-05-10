package elements;

import primitives.Color;

/**
 *
 */
public class AmbientLight {

    private final Color _intensity;

    /**
     * default constructor with initialize with black
     */
    public AmbientLight() {
        _intensity= Color.BLACK;
    }

    /**
     *constructor
     * @param Ia the basic color
     * @param Ka reduction coefficient
     */
    public AmbientLight(Color Ia, double Ka) {
        _intensity = Ia.scale(Ka);
    }

    public Color getIntensity() {
        return _intensity;
    }


}
