package primitives;

public class Material {
    private double Kd = 0d;
    private double Ks = 0d;
    private int nShininess = 0;

    public Material setKd(double kd) {
        this.Kd = kd;
        return this;
    }

    public double getKd() {
        return Kd;
    }

    public double getKs() {
        return Ks;
    }

    public Material setKs(double ks) {
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
