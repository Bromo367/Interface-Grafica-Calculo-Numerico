package model.metodosRaizes.iteracoes;

public class IteracaoNewton {
    private int k;
    private double xn;
    private double fxn;
    private double a;
    private double b;
    private double diferencaIntervalo;
    private double precisao;

    public IteracaoNewton(int k, double xn, double fxn, double a, double b, double precisao){
        this.k=k;
        this.xn=xn;
        this.fxn=fxn;
        this.a=a;
        this.b=b;
        this.precisao=precisao;
        this.diferencaIntervalo=Math.abs(b-a);
    }

    public boolean criterioParadaFuncao(){
        return Math.abs(fxn)<precisao;
    }

    public boolean criterioParadaIntervalo(){
        return Math.abs(diferencaIntervalo)<precisao;
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
