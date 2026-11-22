package model.metodosRaizes.iteracoes;

import java.text.DecimalFormat;

public class IteracaoNewtonRaphson {
    private int k;
    private double xAnterior;
    private double xAtual;
    private double fxn;
    private double derivadaXn;
    private double precisao;
    private double diferenca;

    private String strXAnterior;
    private String strXAtual;
    private String strFxn;
    private String strDerivadaXn;
    private String strDiferenca;
    
    private DecimalFormat df;

    public IteracaoNewtonRaphson(int k, double xAtual, double fxn, double derivadaXn, double precisao, double xAnterior){
        this.k=k;
        this.xAtual=xAtual;
        this.fxn=fxn;
        this.derivadaXn=derivadaXn;
        this.precisao=precisao;
        this.xAnterior=xAnterior;
        diferenca=Math.abs(xAtual-xAnterior);

        String formato = "0.";
        int i=0;
        while(precisao*Math.pow(10,i)<1){
            formato+="##";
            i++;
        }
        df = new DecimalFormat(formato);

        strDerivadaXn = df.format(derivadaXn);
        strDiferenca = df.format(diferenca);
        strXAnterior = df.format(xAnterior);
        strFxn = df.format(fxn);
        strXAtual = df.format(xAtual);
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

    public String getStrDerivadaXn() {
        return strDerivadaXn;
    }

    public String getStrDiferenca() {
        return strDiferenca;
    }

    public String getStrFxn() {
        return strFxn;
    }

    public String getStrXAnterior() {
        return strXAnterior;
    }

    public String getStrXAtual() {
        return strXAtual;
    }
}
