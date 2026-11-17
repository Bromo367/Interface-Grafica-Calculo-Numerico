package service.metodos;

import java.util.ArrayList;
import java.util.List;

import model.geral.MatrizDouble;

public class MetodosSistemas {
    public List<MatrizDouble> eliminacaoGauss(MatrizDouble matrizB){
        List<MatrizDouble> iteracoes = new ArrayList<>();
        for(int i=0;i<matrizB.getAltura();i++){
            double pivo = matrizB.get(i,i);
            for(int j=i+1;j<matrizB.getAltura();j++){
                double multiplicador =  -matrizB.get(j,i)/pivo;
                matrizB.somarLinhas(j, i, multiplicador);
                iteracoes.add(matrizB.criarCopia());
            }
        }
        MatrizDouble resultado = new MatrizDouble(1,matrizB.getAltura());

        for(int i=matrizB.getAltura()-1;i>=0;i--){
            double valorXAtual = matrizB.get(i,matrizB.getLargura()-1);
            for(int j=matrizB.getLargura()-2;j>i;j--){
                valorXAtual -= matrizB.get(i, j)*resultado.get(j,0);
            }
            valorXAtual/=matrizB.get(i,i);
            resultado.set(valorXAtual,i,0);
        }
        iteracoes.add(resultado);


        return iteracoes;
    }
}
