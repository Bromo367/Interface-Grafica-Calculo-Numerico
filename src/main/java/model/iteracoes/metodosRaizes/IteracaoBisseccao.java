package model.iteracoes.metodosRaizes;

public class IteracaoBisseccao {
    private int k;
    private double xn;
    private double fxn;
    private double a;
    private double b;
    private double diferencaIntervalo;

    public IteracaoBisseccao(int k, double xn, double fxn, double a, double b){
        this.k=k;
        this.xn=xn;
        this.fxn=fxn;
        this.a=a;
        this.b=b;
        this.diferencaIntervalo=Math.abs(b-a);
    }

    public int getK() {
        return k;
    }

    public double getXn() {
        return xn;
    }

    public double getFxn() {
        return fxn;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getDiferencaIntervalo() {
        return diferencaIntervalo;
    }
}
