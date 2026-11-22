package model.integracao.subintervalos;

import java.text.DecimalFormat;

public class SubintervaloTrapezio {
    private int k;
    private double xAnterior;
    private double xAtual;
    private double fxAtual;
    private double fxAnterior;
    private double area;
    
    private String strXAnterior;
    private String strXAtual;
    private String strFxAtual;
    private String strFxAnterior;
    private String strArea;

    private DecimalFormat df;
    private int numCasasDecimais = 6;

    public SubintervaloTrapezio(int k, double xAtual, double fxAtual, double xAnterior, double fxAnterior){
        this.k=k;
        this.xAtual=xAtual;
        this.fxAtual=fxAtual;
        this.fxAnterior=fxAnterior;
        this.xAnterior=xAnterior;
        area=((fxAnterior+fxAtual)*(xAtual-xAnterior))/2;

        String formato = "0.";
        for(int i=0;i<numCasasDecimais;i++){
            formato+="#";
        }
        df = new DecimalFormat(formato);

        strArea = df.format(area);
        strFxAnterior = df.format(fxAnterior);
        strFxAtual = df.format(fxAtual);
        strXAnterior = df.format(xAnterior);
        strXAtual = df.format(xAtual);
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

    public String getStrArea() {
        return strArea;
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
