package model.metodosRaizes.retornos;

import java.util.List;
import java.util.ArrayList;

import model.metodosRaizes.iteracoes.IteracaoNewtonRaphson;
import model.metodosRaizes.Raiz;


public class RetornoNewtonRaphson{
    private List<IteracaoNewtonRaphson> iteracoes;
    private Raiz raiz;

    public RetornoNewtonRaphson(ArrayList<IteracaoNewtonRaphson> iteracoes, Raiz raiz){
        this.iteracoes=iteracoes;
        this.raiz=raiz;
    }

    public List<IteracaoNewtonRaphson> getIteracoes() {
        return iteracoes;
    }

    public Raiz getRaiz(){
        return raiz;
    }
}
