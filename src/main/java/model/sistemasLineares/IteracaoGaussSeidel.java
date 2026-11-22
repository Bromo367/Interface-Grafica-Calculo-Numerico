package model.sistemasLineares;

import model.geral.MatrizDouble;

public class IteracaoGaussSeidel {
    private MatrizDouble matrizXAtual;
    private MatrizDouble matrizXAnterior;
    private double precisao;
    private double erroRelativo;
    private int linhaMaiorDiferenca;
    private int linhaMaiorValor;


    public IteracaoGaussSeidel(MatrizDouble matrizXAtual, MatrizDouble matrizXAnterior, double precisao){
        this.matrizXAtual = matrizXAtual;
        this.matrizXAnterior = matrizXAnterior;
        this.precisao = precisao;
        
        double maiorErroAbsoluto = 0;
        for(int i=0; i<matrizXAtual.getAltura();i++){
            if(Math.abs(matrizXAtual.get(i,0)-matrizXAnterior.get(i,0)) > maiorErroAbsoluto){
                maiorErroAbsoluto = Math.abs(matrizXAtual.get(i,0)-matrizXAnterior.get(i,0));
                linhaMaiorDiferenca = i;
            }
        }
        double maiorValorAtual = 0;
        for(int i=0; i<matrizXAtual.getAltura();i++){
            if(Math.abs(matrizXAtual.get(i,0)) > maiorValorAtual){
                maiorValorAtual = Math.abs(matrizXAtual.get(i,0));
                linhaMaiorValor = i;
            }
        }

        erroRelativo = maiorErroAbsoluto/maiorValorAtual;

    }

    public MatrizDouble getMatrizXAtual() {
        return matrizXAtual;
    }


    public double getErroRelativo() {
        return erroRelativo;
    }

    public String stringCriterioParada(){
        String strErroRelativo = "";

        strErroRelativo += "|" + matrizXAtual.get(linhaMaiorDiferenca,0) + "-";
        strErroRelativo += matrizXAnterior.get(linhaMaiorDiferenca,0) + "|";
        strErroRelativo += "/|" + matrizXAtual.get(linhaMaiorValor,0)+"|";
        strErroRelativo += " = " + erroRelativo;

        if(erroRelativo >= precisao){
            strErroRelativo += " >= ";
        }
        else{
            strErroRelativo += " < ";
        }

        strErroRelativo += precisao;

        return strErroRelativo;
    }
}
