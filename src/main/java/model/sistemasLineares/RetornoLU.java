package model.sistemasLineares;

import model.geral.MatrizDouble;

public class RetornoLU {
    private MatrizDouble matrizB;
    private MatrizDouble matrizL;
    private MatrizDouble matrizU;
    private MatrizDouble solucaoX;
    private MatrizDouble solucaoY;


    public RetornoLU(MatrizDouble matrizB, MatrizDouble matrizL, MatrizDouble matrizU, MatrizDouble solucaoX, MatrizDouble solucaoY){
        this.matrizB = matrizB;
        this.matrizL = matrizL;
        this.matrizU = matrizU;
        this.solucaoX = solucaoX;
        this.solucaoY = solucaoY;
    }

    public MatrizDouble getMatrizB() {
        return matrizB;
    }

    public MatrizDouble getMatrizL() {
        return matrizL;
    }

    public MatrizDouble getMatrizU() {
        return matrizU;
    }

    public MatrizDouble getmatrizL() {
        return matrizL;
    }

    public MatrizDouble getmatrizU() {
        return matrizU;
    }

    public MatrizDouble getSolucaoX() {
        return solucaoX;
    }
    
    public MatrizDouble getSolucaoY() {
        return solucaoY;
    }
}
