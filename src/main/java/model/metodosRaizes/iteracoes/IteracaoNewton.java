package model.metodosRaizes.iteracoes;

public class IteracaoNewton {
    private int k;
    private double xAnterior;
    private double xAtual;
    private double fxn;
    private double derivadaXn;
    private double precisao;
    private double diferenca;

    public IteracaoNewton(int k, double xAtual, double fxn, double derivadaXn, double precisao, double xAnterior){
        this.k=k;
        this.xAtual=xAtual;
        this.fxn=fxn;
        this.derivadaXn=derivadaXn;
        this.precisao=precisao;
        this.xAnterior=xAnterior;
        diferenca=Math.abs(xAtual-xAnterior);
    }

    public boolean criterioParadaFuncao(){
        return Math.abs(fxn)<precisao;
    }

    public boolean criterioParadaDiferenca(){
        return Math.abs(diferenca)<precisao;
    }

    public int getK() {
        return k;
    }

    public double getXAtual() {
        return xAtual;
    }

    public double getFxn() {
        return fxn;
    }

    public double getDerivadaXn() {
        return derivadaXn;
    }

    public double getXAnterior() {
        return xAnterior;
    }

    public double getDiferenca() {
        return diferenca;
    }
}
