package model.metodosRaizes.retornos;

import java.util.List;
import java.util.ArrayList;

import model.metodosRaizes.iteracoes.IteracaoBisseccao;
import model.metodosRaizes.Raiz;


public class RetornoBisseccao{
    private List<IteracaoBisseccao> iteracoes;
    private Raiz raiz;

    public RetornoBisseccao(ArrayList<IteracaoBisseccao> iteracoes, Raiz raiz){
        this.iteracoes=iteracoes;
        this.raiz=raiz;
    }

    public List<IteracaoBisseccao> getIteracoes() {
        return iteracoes;
    }

    public Raiz getRaiz(){
        return raiz;
    }
}
