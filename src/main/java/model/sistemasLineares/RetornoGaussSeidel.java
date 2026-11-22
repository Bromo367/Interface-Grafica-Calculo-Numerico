package model.sistemasLineares;

import java.util.List;
import model.geral.MatrizDouble;

public class RetornoGaussSeidel {
    private List<IteracaoGaussSeidel> iteracoes;
    private MatrizDouble solucao;
    private MatrizDouble matrizB;

    public RetornoGaussSeidel(MatrizDouble matrizB, List<IteracaoGaussSeidel> iteracoes, MatrizDouble solucao){
        this.matrizB = matrizB;
        this.iteracoes = iteracoes;
        this.solucao = solucao;
    }

    public List<IteracaoGaussSeidel> getIteracoes() {
        return iteracoes;
    }

    public MatrizDouble getSolucao() {
        return solucao;
    }

    public MatrizDouble getMatrizB() {
        return matrizB;
    }
}
