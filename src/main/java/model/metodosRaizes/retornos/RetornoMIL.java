package model.metodosRaizes.retornos;

import java.util.List;
import java.util.ArrayList;

import model.metodosRaizes.iteracoes.IteracaoMIL;
import model.metodosRaizes.Raiz;


public class RetornoMIL{
    private List<IteracaoMIL> iteracoes;
    private Raiz raiz;

    public RetornoMIL(ArrayList<IteracaoMIL> iteracoes, Raiz raiz){
        this.iteracoes=iteracoes;
        this.raiz=raiz;
    }

    public List<IteracaoMIL> getIteracoes() {
        return iteracoes;
    }

    public Raiz getRaiz(){
        return raiz;
    }
}
