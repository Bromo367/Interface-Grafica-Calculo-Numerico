package model.metodosRaizes;

public class Raiz {
    private int numIteracoes;
    private double valor;
    private double valorNaFuncao;
    private String criterioParada;

    public Raiz(int numIteracoes, double valor, double valorNaFuncao, String criterioParada){
        this.numIteracoes=numIteracoes;
        this.valor=valor;
        this.valorNaFuncao=valorNaFuncao;
        this.criterioParada=criterioParada;
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
}
