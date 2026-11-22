package model.integracao.retornos;

import model.geral.MatrizDouble;
import model.sistemasLineares.RetornoEliminacaoGauss;

public class RetornoCasoDiscreto {
    private String polinomio;
    private MatrizDouble matrizB;
    private RetornoEliminacaoGauss resolucaoSistema;

    public RetornoCasoDiscreto(String polinomio, MatrizDouble matrizB, RetornoEliminacaoGauss resolucaoSistema){
        this.polinomio = polinomio;
        this.matrizB = matrizB;
        this.resolucaoSistema = resolucaoSistema;
    }

    public MatrizDouble getMatrizB() {
        return matrizB;
    }

    public String getPolinomio() {
        return polinomio;
    }
    
    public RetornoEliminacaoGauss getResolucaoSistema() {
        return resolucaoSistema;
    }
}
