package model.metodosRaizes;

import java.text.DecimalFormat;

public class Raiz {
    private int numIteracoes;
    private double valor;
    private double valorNaFuncao;
    private String criterioParada;
    private DecimalFormat df;

    public Raiz(int numIteracoes, double valor, double valorNaFuncao, String criterioParada, double precisao){
        this.numIteracoes=numIteracoes;
        this.valor=valor;
        this.valorNaFuncao=valorNaFuncao;
        this.criterioParada=criterioParada;

        String formato = "0.";
        int i=0;
        while(precisao*Math.pow(10,i)<1){
            formato+="##";
            i++;
        }
        df = new DecimalFormat(formato);
    }

    public int getNumIteracoes() {
        return numIteracoes;
    }

    public String getCriterioParada() {
        return criterioParada;
    }

    public double getValor() {
        return valor;
    }

    public double getValorNaFuncao() {
        return valorNaFuncao;
    }

    @Override 
    public String toString(){
        return "Número total de iterações: "+numIteracoes
                +"\nCritério de parada: "+criterioParada
                +"\nRaiz: "+df.format(valor)
                +"\nf(Raiz): "+df.format(valorNaFuncao);
    }
}
