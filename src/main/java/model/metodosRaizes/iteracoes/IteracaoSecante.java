package model.metodosRaizes.iteracoes;

public class IteracaoSecante {
    private int k;
    private double xAnterior;
    private double xAtual;
    private double fxAtual;
    private double fxAnterior;
    private double precisao;
    private double diferenca;

    public IteracaoSecante(int k, double xAtual, double fxAtual, double fxAnterior, double precisao, double xAnterior){
        this.k=k;
        this.xAtual=xAtual;
        this.fxAtual=fxAtual;
        this.fxAnterior=fxAnterior;
        this.precisao=precisao;
        this.xAnterior=xAnterior;
        diferenca=Math.abs(xAtual-xAnterior);
    }

    public boolean criterioParadaFuncao(){
        return Math.abs(fxAtual)<precisao;
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

    public double getFxAtual() {
        return fxAtual;
    }

    public double getFxAnterior() {
        return fxAnterior;
    }

    public double getXAnterior() {
        return xAnterior;
    }

    public double getDiferenca() {
        return diferenca;
    }
}
