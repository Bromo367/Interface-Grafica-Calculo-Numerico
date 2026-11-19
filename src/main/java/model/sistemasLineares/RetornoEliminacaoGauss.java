package model.sistemasLineares;

import java.util.List;
import model.geral.MatrizDouble;

public class RetornoEliminacaoGauss {
    private List<MatrizDouble> iteracoes;
    private MatrizDouble solucao;

    public RetornoEliminacaoGauss(List<MatrizDouble> iteracoes, MatrizDouble solucao){
        this.iteracoes = iteracoes;
        this.solucao = solucao;
    }

    public List<MatrizDouble> getIteracoes() {
        return iteracoes;
    }

    public MatrizDouble getSolucao() {
        return solucao;
    }
}
