package model.integracao.subintervalos;

public class SubintervaloTrapezio {
    private int k;
    private double xAnterior;
    private double xAtual;
    private double fxAtual;
    private double fxAnterior;
    private double area;

    public SubintervaloTrapezio(int k, double xAtual, double fxAtual, double xAnterior, double fxAnterior){
        this.k=k;
        this.xAtual=xAtual;
        this.fxAtual=fxAtual;
        this.fxAnterior=fxAnterior;
        this.xAnterior=xAnterior;
        area=((fxAnterior+fxAtual)*(xAtual-xAnterior))/2;
    }

    public int getK() {
        return k;
    }

    public double getXAtual() {
        return xAtual;
    }

    public double getFxAtual() {
        return fxAtual;
    }

    public double getFxAnterior() {
        return fxAnterior;
    }

    public double getXAnterior() {
        return xAnterior;
    }

    public double getArea() {
        return area;
    }
}
