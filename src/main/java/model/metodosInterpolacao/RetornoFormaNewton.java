package model.metodosInterpolacao;

import java.util.List;

public class RetornoFormaNewton {
    private String polinomio;
    private List<Double> diferencasDivididas;

    public RetornoFormaNewton(String polinomio,List<Double> diferencasDivididas){
        this.polinomio=polinomio;
        this.diferencasDivididas=diferencasDivididas;
    }

    public String getPolinomio() {
        return polinomio;
    }

    public List<Double> getdiferencasDivididas() {
        return diferencasDivididas;
    }
}
