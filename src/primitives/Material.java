package primitives;

public class Material {
    private Double3 Kd = Double3.ZERO;
    private Double3 Ks = Double3.ZERO;


    public Double3 kT = Double3.ZERO; // transparancy attenuation factor
    public Double3 kR = Double3.ZERO; // reflection attenuation factor
    private int nShininess = 0;

    public Material setKd(double kd) {
        this.Kd = new Double3(kd);
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

    public Material setKs(double v) {
        this.Ks= new Double3(v);
        return this;
    }

    public Material setKt(double v) {
        this.kT=new Double3(v);
        return this;
    }

    public Material setKr(Double kr) {
        kR = new Double3(kr);
        return this;
    }
    public Double3 getkT() {
        return kT;
    }

    public Double3 getkR() {
        return kR;
    }


}
