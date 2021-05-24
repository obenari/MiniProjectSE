package primitives;

/**
 * this class is PDS
 */
public class Material {
    public double kD = 0;//for diffuse
    public double kS = 0;//for specular
    public int nShininess = 0;//for shining
    public double kR = 0;//for Reflection
    public double kT = 0;//for  Refraction (Transparency)

    public Material setkR(double kR) {
        this.kR = kR;
        return this;
    }

    public Material setkT(double kT) {
        this.kT = kT;
        return this;
    }

    public Material setkD(double kD) {
        this.kD = kD;
        return this;
    }

    public Material setkS(double kS) {
        this.kS = kS;
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
