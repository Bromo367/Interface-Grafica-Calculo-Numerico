package model.metodosRaizes.retornos;

import java.util.List;
import java.util.ArrayList;

import model.metodosRaizes.iteracoes.IteracaoRegulaFalsi;
import model.metodosRaizes.Raiz;


public class RetornoRegulaFalsi{
    private List<IteracaoRegulaFalsi> iteracoes;
    private Raiz raiz;

    public RetornoRegulaFalsi(ArrayList<IteracaoRegulaFalsi> iteracoes, Raiz raiz){
        this.iteracoes=iteracoes;
        this.raiz=raiz;
    }

    public List<IteracaoRegulaFalsi> getIteracoes() {
        return iteracoes;
    }

    public Raiz getRaiz(){
        return raiz;
    }
}
