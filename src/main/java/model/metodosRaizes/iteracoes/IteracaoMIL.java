package model.metodosRaizes.iteracoes;

public class IteracaoMIL {
    private int k;
    private double xAnterior;
    private double xAtual;
    private double fxn;
    private double fixn;
    private double precisao;
    private double diferenca;

    public IteracaoMIL(int k, double xAtual, double fxn, double fixn, double precisao, double xAnterior){
        this.k=k;
        this.xAtual=xAtual;
        this.fxn=fxn;
        this.fixn=fixn;
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

    public double getFixn() {
        return fixn;
    }

    public double getXAnterior() {
        return xAnterior;
    }

    public double getDiferenca() {
        return diferenca;
    }
}
