package model.metodosRaizes.iteracoes;

import java.text.DecimalFormat;

public class IteracaoRegulaFalsi {
    private int k;
    private double xn;
    private double fxn;
    private double a;
    private double b;
    private double erroRelativo;
    private double precisao;
    
    private String strXn;
    private String strFxn;
    private String strA;
    private String strB;
    private String strErroRelativo;

    private DecimalFormat df;


    public IteracaoRegulaFalsi(int k, double xn, double fxn, double a, double b, double precisao){
        this.k=k;
        this.xn=xn;
        this.fxn=fxn;
        this.a=a;
        this.b=b;
        this.precisao=precisao;
        this.erroRelativo=Math.abs((b-a)/xn);

        String formato = "0.";
        int i=0;
        while(precisao*Math.pow(10,i)<1){
            formato+="##";
            i++;
        }
        df = new DecimalFormat(formato);

        strXn = df.format(xn);
        strA = df.format(a);
        strB = df.format(b);
        strFxn = df.format(fxn);
        strErroRelativo = df.format(erroRelativo);
    }

    public boolean criterioParadaFuncao(){
        return Math.abs(fxn)<precisao;
    }

    public boolean criterioParadaErroRelativo(){
        return Math.abs((b-a)/xn)<precisao;
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

    public double getErroRelativo() {
        return erroRelativo;
    }

    public String getStrA() {
        return strA;
    }

    public String getStrB() {
        return strB;
    }

    public String getStrErroRelativo() {
        return strErroRelativo;
    }

    public String getStrFxn() {
        return strFxn;
    }

    public String getStrXn() {
        return strXn;
    }
}
