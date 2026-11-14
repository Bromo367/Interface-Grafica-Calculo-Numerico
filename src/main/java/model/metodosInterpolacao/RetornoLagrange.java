package model.metodosInterpolacao;

import java.util.List;

public class RetornoLagrange {
    private String polinomio;
    private List<String> subpolinomios;

    public RetornoLagrange(String polinomio,List<String> subpolinomios){
        this.polinomio=polinomio;
        this.subpolinomios=subpolinomios;
    }

    public String getPolinomio() {
        return polinomio;
    }

    public List<String> getSubpolinomios() {
        return subpolinomios;
    }
}
