package elements;

import primitives.Color;

/**
 * abstract class that represent the light in the scene
 */
abstract class Light {
    /**
     * the light color
     */
    protected Color _intensity;

    /**
     * constructor
     *
     * @param intensity the light intensity
     */
    public Light(Color intensity) {
        _intensity = intensity;
    }

    /**
     * getter
     *
     * @return the light intensity
     */
    public Color getIntensity() {
        return _intensity;
    }

}
