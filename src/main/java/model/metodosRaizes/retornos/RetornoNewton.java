package model.metodosRaizes.retornos;

import java.util.List;
import java.util.ArrayList;

import model.metodosRaizes.iteracoes.IteracaoNewton;
import model.metodosRaizes.Raiz;


public class RetornoNewton{
    private List<IteracaoNewton> iteracoes;
    private Raiz raiz;

    public RetornoNewton(ArrayList<IteracaoNewton> iteracoes, Raiz raiz){
        this.iteracoes=iteracoes;
        this.raiz=raiz;
    }

    public List<IteracaoNewton> getIteracoes() {
        return iteracoes;
    }

    public Raiz getRaiz(){
        return raiz;
    }
}
