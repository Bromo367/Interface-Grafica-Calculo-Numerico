package model.metodosRaizes.iteracoes;

public class IteracaoRegulaFalsi {
    private int k;
    private double xn;
    private double fxn;
    private double a;
    private double b;
    private double erroRelativo;
    private double precisao;

    public IteracaoRegulaFalsi(int k, double xn, double fxn, double a, double b, double precisao){
        this.k=k;
        this.xn=xn;
        this.fxn=fxn;
        this.a=a;
        this.b=b;
        this.precisao=precisao;
        this.erroRelativo=Math.abs((b-a)/xn);
    }

    public boolean criterioParadaFuncao(){
        return Math.abs(fxn)<precisao;
    }

    public boolean criterioParadaErroRelativo(){
        return Math.abs((b-a)/xn)<precisao;
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

    public double getErroRelativo() {
        return erroRelativo;
    }
}
