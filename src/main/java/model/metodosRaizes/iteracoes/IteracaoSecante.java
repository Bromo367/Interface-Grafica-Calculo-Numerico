package model.metodosRaizes.iteracoes;

import java.text.DecimalFormat;

public class IteracaoSecante {
    private int k;
    private double xAnterior;
    private double xAtual;
    private double fxAtual;
    private double fxAnterior;
    private double precisao;
    private double diferenca;

    private String strXAnterior;
    private String strXAtual;
    private String strFxAtual;
    private String strFxAnterior;
    private String strDiferenca;
    
    private DecimalFormat df;

    public IteracaoSecante(int k, double xAtual, double fxAtual, double fxAnterior, double precisao, double xAnterior){
        this.k=k;
        this.xAtual=xAtual;
        this.fxAtual=fxAtual;
        this.fxAnterior=fxAnterior;
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

        strXAnterior = df.format(xAnterior);
        strXAtual = df.format(xAtual);
        strDiferenca = df.format(diferenca);
        strFxAnterior = df.format(fxAnterior);
        strFxAtual = df.format(fxAtual);

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

    public String getStrDiferenca() {
        return strDiferenca;
    }

    public String getStrFxAnterior() {
        return strFxAnterior;
    }

    public String getStrFxAtual() {
        return strFxAtual;
    }

    public String getStrXAnterior() {
        return strXAnterior;
    }

    public String getStrXAtual() {
        return strXAtual;
    }
}
