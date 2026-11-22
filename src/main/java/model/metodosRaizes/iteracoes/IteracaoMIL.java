package model.metodosRaizes.iteracoes;

import java.text.DecimalFormat;

public class IteracaoMIL {
    private int k;
    private double xAnterior;
    private double xAtual;
    private double fxn;
    private double fixn;
    private double precisao;
    private double diferenca;
    private String strXAnterior;
    private String strXAtual;
    private String strFxn;
    private String strFixn;
    private String strDiferenca;
    private DecimalFormat df;

    public IteracaoMIL(int k, double xAtual, double fxn, double fixn, double precisao, double xAnterior){
        this.k=k;
        this.xAtual=xAtual;
        this.fxn=fxn;
        this.fixn=fixn;
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
        strFxn = df.format(fxn);
        strFixn = df.format(fixn);
        strDiferenca = df.format(strDiferenca);
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

    public String getStrDiferenca() {
        return strDiferenca;
    }

    public String getStrFixn() {
        return strFixn;
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
