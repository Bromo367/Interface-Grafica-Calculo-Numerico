package model.integracao.subintervalos;

public class SubintervaloSimpson {
    private int k;
    private double xAnterior;
    private double xAtual;
    private double xProximo;
    private double fxAtual;
    private double fxAnterior;
    private double fxProximo;
    private double area;

    public SubintervaloSimpson(int k, double xAtual, double fxAtual, double xAnterior, double fxAnterior, double xProximo, double fxProximo){
        this.k=k;
        this.xAtual=xAtual;
        this.fxAtual=fxAtual;
        this.fxAnterior=fxAnterior;
        this.xAnterior=xAnterior;
        this.xProximo=xProximo;
        this.fxProximo=fxProximo;
        area=((fxAnterior+4*fxAtual+fxProximo)*(xProximo-xAtual))/3;
    }

    public int getK() {
        return k;
    }

    public double getXAtual() {
        return xAtual;
    }

    public double getXProximo() {
        return xProximo;
    }

    public double getFxAtual() {
        return fxAtual;
    }

    public double getFxAnterior() {
        return fxAnterior;
    }

    public double getFxProximo() {
        return fxProximo;
    }

    public double getXAnterior() {
        return xAnterior;
    }

    public double getArea() {
        return area;
    }
}
