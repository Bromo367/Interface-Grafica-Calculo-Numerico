package model.metodosRaizes.retornos;

import java.util.List;
import java.util.ArrayList;

import model.metodosRaizes.iteracoes.IteracaoSecante;
import model.metodosRaizes.Raiz;


public class RetornoSecante{
    private List<IteracaoSecante> iteracoes;
    private Raiz raiz;

    public RetornoSecante(ArrayList<IteracaoSecante> iteracoes, Raiz raiz){
        this.iteracoes=iteracoes;
        this.raiz=raiz;
    }

    public List<IteracaoSecante> getIteracoes() {
        return iteracoes;
    }

    public Raiz getRaiz(){
        return raiz;
    }
}
