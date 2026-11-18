package service.metodos;

import java.util.ArrayList;
import java.util.List;

import model.sistemasLineares.RetornoGauss;
import model.sistemasLineares.RetornoLU;
import model.geral.MatrizDouble;

public class MetodosSistemas {
    public RetornoGauss eliminacaoGauss(MatrizDouble matrizB){
        List<MatrizDouble> iteracoes = new ArrayList<>();
        for(int i=0;i<matrizB.getAltura();i++){
            double pivo = matrizB.get(i,i);
            int linhaPraTrocar = i+1;
            while (pivo==0 && linhaPraTrocar<matrizB.getAltura()) {
                matrizB.trocarLinhas(i, linhaPraTrocar);
                pivo = matrizB.get(i,i);
                iteracoes.add(matrizB.criarCopia());
            }
            for(int j=i+1;j<matrizB.getAltura();j++){
                double multiplicador =  -matrizB.get(j,i)/pivo;
                matrizB.somarLinhas(j, i, multiplicador);
                iteracoes.add(matrizB.criarCopia());
            }
        }
        MatrizDouble solucao = new MatrizDouble(1,matrizB.getAltura());

        for(int i=matrizB.getAltura()-1;i>=0;i--){
            double valorXAtual = matrizB.get(i,matrizB.getLargura()-1);
            for(int j=matrizB.getLargura()-2;j>i;j--){
                valorXAtual -= matrizB.get(i, j)*solucao.get(j,0);
            }
            valorXAtual/=matrizB.get(i,i);
            solucao.set(valorXAtual,i,0);
        }

        return new RetornoGauss(iteracoes, solucao);
    }

    public RetornoLU fatoracaoLU(MatrizDouble matrizB){
        int altura = matrizB.getAltura();
        int largura = matrizB.getLargura();
        MatrizDouble matrizL = new MatrizDouble(altura, altura);
        MatrizDouble matrizU = new MatrizDouble(altura, altura);
        
        for(int i=0;i<altura;i++){
            matrizL.set(1.0,i,i);
        }

        for(int i=0;i<altura;i++){
            for(int j=i;j<altura;j++){
                double uAtual = matrizB.get(i,j);
                for(int k=0;k<i;k++){
                    uAtual-=matrizL.get(i,k)*matrizU.get(k,j);
                }
                matrizU.set(uAtual,i,j);
            }
            for(int j=i+1;j<altura;j++){
                double lAtual = matrizB.get(j,i);
                for(int k=0;k<j;k++){
                    lAtual-=matrizL.get(j,k)*matrizU.get(k,i);
                }
                lAtual/=matrizU.get(i,i);
                matrizL.set(lAtual,j,i);
            }
        }

        MatrizDouble solucaoY = new MatrizDouble(1,matrizB.getAltura());

        for(int i=0;i<altura;i++){
            double valorYAtual = matrizB.get(i,largura-1);
            for(int j=0;j<i;j++){
                valorYAtual -= matrizL.get(i, j)*solucaoY.get(j,0);
            }
            valorYAtual/=matrizL.get(i,i);
            solucaoY.set(valorYAtual,i,0);
        }

        MatrizDouble solucaoX = new MatrizDouble(1,matrizB.getAltura());

        for(int i=altura-1;i>=0;i--){
            double valorXAtual = solucaoY.get(i, 0);
            for(int j=largura-2;j>i;j--){
                valorXAtual -= matrizU.get(i, j)*solucaoX.get(j,0);
            }
            valorXAtual/=matrizU.get(i,i);
            solucaoX.set(valorXAtual,i,0);
        }


        return new RetornoLU(matrizL.criarCopia(), matrizU.criarCopia(), solucaoX, solucaoY);
    }
}
