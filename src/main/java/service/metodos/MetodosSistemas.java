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
        List<MatrizDouble> iteracoesL = new ArrayList<>();
        List<MatrizDouble> iteracoesU = new ArrayList<>();
        
        return null;
    }
}
