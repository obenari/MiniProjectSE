package elements;

import primitives.Color;

/**
 *
 */
public class AmbientLight extends Light{



    /**
     * default constructor with initialize with black
     */
    public AmbientLight() {
        super( Color.BLACK);
    }

    /**
     *constructor
     * @param Ia the basic color
     * @param Ka reduction coefficient
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(Ka));
    }




}
