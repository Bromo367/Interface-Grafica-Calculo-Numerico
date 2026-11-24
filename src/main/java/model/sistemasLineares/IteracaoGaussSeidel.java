package model.sistemasLineares;

import java.text.DecimalFormat;

import model.geral.MatrizDouble;

public class IteracaoGaussSeidel {
    private MatrizDouble matrizXAtual;
    private MatrizDouble matrizXAnterior;
    private double precisao;
    private double erroRelativo;
    private int linhaMaiorDiferenca;
    private int linhaMaiorValor;
    private DecimalFormat df;



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

        String formato = "0.";
        int i=0;
        while(precisao*Math.pow(10,i)<1){
            formato+="##";
            i++;
        }
        df = new DecimalFormat(formato);

    }

    public MatrizDouble getMatrizXAtual() {
        return matrizXAtual;
    }


    public double getErroRelativo() {
        return erroRelativo;
    }

    public String stringCriterioParada(){
        String strErroRelativo = "";

        strErroRelativo += "|" + df.format(matrizXAtual.get(linhaMaiorDiferenca,0)) + "-";
        strErroRelativo += df.format(matrizXAnterior.get(linhaMaiorDiferenca,0)) + "|";
        strErroRelativo += "/|" + df.format(matrizXAtual.get(linhaMaiorValor,0))+"|";
        strErroRelativo += " = " + df.format(erroRelativo);

        if(erroRelativo >= precisao){
            strErroRelativo += " >= ";
        }
        else{
            strErroRelativo += " < ";
        }

        strErroRelativo += df.format(precisao);

        return strErroRelativo;
    }
}
