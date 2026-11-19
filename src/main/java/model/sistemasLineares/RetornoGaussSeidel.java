package model.sistemasLineares;

import java.util.List;
import model.geral.MatrizDouble;

public class RetornoGaussSeidel {
    private List<IteracaoGaussSeidel> iteracoes;
    private MatrizDouble solucao;

    public RetornoGaussSeidel(List<IteracaoGaussSeidel> iteracoes, MatrizDouble solucao){
        this.iteracoes = iteracoes;
        this.solucao = solucao;
    }

    public List<IteracaoGaussSeidel> getIteracoes() {
        return iteracoes;
    }

    public MatrizDouble getSolucao() {
        return solucao;
    }
}
