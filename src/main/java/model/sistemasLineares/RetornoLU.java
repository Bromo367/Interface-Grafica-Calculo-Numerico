package model.sistemasLineares;

import java.util.List;
import model.geral.MatrizDouble;

public class RetornoLU {
    private List<MatrizDouble> iteracoesL;
    private List<MatrizDouble> iteracoesU;
    private MatrizDouble matrizL;
    private MatrizDouble matrizU;
    private MatrizDouble solucaoX;
    private MatrizDouble solucaoY;


    public RetornoLU(List<MatrizDouble> iteracoesL, List<MatrizDouble> iteracoesU, MatrizDouble matrizL,  MatrizDouble matrizU, MatrizDouble solucaoX, MatrizDouble solucaoY){
        this.iteracoesL = iteracoesL;
        this.iteracoesU = iteracoesU;
        this.matrizL = matrizL;
        this.matrizU = matrizU;
        this.solucaoX = solucaoX;
        this.solucaoY = solucaoY;
    }

    public MatrizDouble getMatrizL() {
        return matrizL;
    }

    public MatrizDouble getMatrizU() {
        return matrizU;
    }

    public List<MatrizDouble> getIteracoesL() {
        return iteracoesL;
    }

    public List<MatrizDouble> getIteracoesU() {
        return iteracoesU;
    }

    public MatrizDouble getSolucaoX() {
        return solucaoX;
    }
    
    public MatrizDouble getSolucaoY() {
        return solucaoY;
    }
}
