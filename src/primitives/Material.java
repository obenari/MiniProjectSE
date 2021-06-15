package primitives;

/**
 * this class is PDS
 */
public class Material {
    /**
     * for diffuse
     */
    public double kD = 0;
    /**
     * //for specular
     */
    public double kS = 0;
    /**
     * /for shining
     */
    public int nShininess = 0;
    /**
     * //for Reflection
     */
    public double kR = 0;
    /**
     * //for  Refraction (Transparency)
     */
    public double kT = 0;

    /**
     * setter chaining methode
     *
     * @param kR
     * @return
     */
    public Material setkR(double kR) {
        this.kR = kR;
        return this;
    }

    /**
     * setter chaining methode
     *
     * @param kT
     * @return
     */
    public Material setkT(double kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter chaining methode
     *
     * @param kD
     * @return
     */
    public Material setkD(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter chaining methode
     *
     * @param kS
     * @return
     */
    public Material setkS(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter chaining methode
     *
     * @param nShininess
     * @return
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
