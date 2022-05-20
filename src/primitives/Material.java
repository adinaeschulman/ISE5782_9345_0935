package primitives;

public class Material {
    private Double3 Kd = Double3.ZERO;
    private Double3 Ks = Double3.ZERO;
    private int nShininess = 0;

    public Material setKd(Double3 kd) {
        this.Kd = kd;
        return this;
    }

    public Double3 getKd() {
        return Kd;
    }

    public Double3 getKs() {
        return Ks;
    }

    public Material setKs(Double3 ks) {
        this.Ks = ks;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public int getnShininess() {
        return nShininess;
    }

}
