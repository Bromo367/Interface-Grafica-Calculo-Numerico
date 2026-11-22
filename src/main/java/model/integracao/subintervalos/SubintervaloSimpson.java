package model.integracao.subintervalos;

import java.text.DecimalFormat;

public class SubintervaloSimpson {
    private int k;
    private double xAnterior;
    private double xAtual;
    private double xProximo;
    private double fxAtual;
    private double fxAnterior;
    private double fxProximo;
    private double area;
    
    private String strXAnterior;
    private String strXAtual;
    private String strXProximo;
    private String strFxAtual;
    private String strArea;

    private DecimalFormat df;
    private int numCasasDecimais = 6;

    public SubintervaloSimpson(int k, double xAtual, double fxAtual, double xAnterior, double fxAnterior, double xProximo, double fxProximo){
        this.k=k;
        this.xAtual=xAtual;
        this.fxAtual=fxAtual;
        this.fxAnterior=fxAnterior;
        this.xAnterior=xAnterior;
        this.xProximo=xProximo;
        this.fxProximo=fxProximo;
        area=((fxAnterior+4*fxAtual+fxProximo)*(xProximo-xAtual))/3;

        String formato = "0.";
        for(int i=0;i<numCasasDecimais;i++){
            formato+="#";
        }
        df = new DecimalFormat(formato);

        strArea = df.format(area);
        strXAnterior = df.format(xAnterior);
        strXAtual = df.format(xAtual);
        strXProximo = df.format(xProximo);
        strFxAtual = df.format(fxAtual);
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

    public String getStrArea() {
        return strArea;
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

    public String getStrXProximo() {
        return strXProximo;
    }
}
