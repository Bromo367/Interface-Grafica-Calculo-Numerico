package model.metodosRaizes.iteracoes;

import java.text.DecimalFormat;

public class IteracaoBisseccao {
    private int k;
    private double xn;
    private double fxn;
    private double a;
    private double b;
    private double diferencaIntervalo;

    private String strXn;
    private String strFxn;
    private String strA;
    private String strB;
    private String strDiferencaIntervalo;
    
    private double precisao;
    private DecimalFormat df;


    public IteracaoBisseccao(int k, double xn, double fxn, double a, double b, double precisao){
        this.k=k;
        this.xn=xn;
        this.fxn=fxn;
        this.a=a;
        this.b=b;
        this.precisao=precisao;
        this.diferencaIntervalo=Math.abs(b-a);

        String formato = "0.";
        int i=0;
        while(precisao*Math.pow(10,i)<1){
            formato+="##";
            i++;
        }
        df = new DecimalFormat(formato);

        strA = df.format(a);
        strB = df.format(b);
        strXn = df.format(xn);
        strFxn = df.format(fxn);
        strDiferencaIntervalo = df.format(diferencaIntervalo);
    }

    public boolean criterioParadaFuncao(){
        return Math.abs(fxn)<precisao;
    }

    public boolean criterioParadaIntervalo(){
        return Math.abs(diferencaIntervalo)<precisao;
    }

    public int getK() {
        return k;
    }

    public double getXn() {
        return xn;
    }

    public double getFxn() {
        return fxn;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getDiferencaIntervalo() {
        return diferencaIntervalo;
    }

    public String getStrA() {
        return strA;
    }

    public String getStrB() {
        return strB;
    }

    public String getStrDiferencaIntervalo() {
        return strDiferencaIntervalo;
    }

    public String getStrFxn() {
        return strFxn;
    }

    public String getStrXn() {
        return strXn;
    }
}
