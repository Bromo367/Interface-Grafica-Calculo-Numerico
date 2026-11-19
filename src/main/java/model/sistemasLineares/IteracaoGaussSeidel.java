package model.sistemasLineares;

import model.geral.MatrizDouble;

public class IteracaoGaussSeidel {
    private MatrizDouble matrizX;
    private double precisao;

    public IteracaoGaussSeidel(MatrizDouble matrizX, double precisao){
        this.matrizX = matrizX;
        this.precisao = precisao;
    }

    public MatrizDouble getMatrizX() {
        return matrizX;
    }

    public double ErroRelativo(){
        return 0.0;
    }
}
