package service.metodos;

import java.util.ArrayList;
import java.util.List;

import model.sistemasLineares.IteracaoGaussSeidel;
import model.sistemasLineares.RetornoEliminacaoGauss;
import model.sistemasLineares.RetornoGaussSeidel;
import model.sistemasLineares.RetornoLU;
import model.geral.MatrizDouble;

public class MetodosSistemas {
    public RetornoEliminacaoGauss eliminacaoGauss(MatrizDouble matrizB) throws ResolucaoException{
        int largura = matrizB.getLargura();
        int altura = matrizB.getAltura();
        if(matrizB.submatriz(altura,altura).determinante()==0){
            throw new ResolucaoException("Determinante da matriz A nulo");
        }

        List<MatrizDouble> iteracoes = new ArrayList<>();
        for(int i=0;i<altura;i++){
            double pivo = matrizB.get(i,i);
            int linhaPraTrocar = i+1;
            while (pivo==0 && linhaPraTrocar<altura) {
                matrizB.trocarLinhas(i, linhaPraTrocar);
                pivo = matrizB.get(i,i);
                iteracoes.add(matrizB.criarCopia());
            }
            if(pivo==0){
                throw new ResolucaoException("O sistema pode apresentar infinitas soluções!");
            }
            for(int j=i+1;j<altura;j++){
                double multiplicador =  -matrizB.get(j,i)/pivo;
                matrizB.somarLinhas(j, i, multiplicador);
                iteracoes.add(matrizB.criarCopia());
            }
        }
        MatrizDouble solucao = new MatrizDouble(1,altura);

        for(int i=altura-1;i>=0;i--){
            double valorXAtual = matrizB.get(i,largura-1);
            for(int j=largura-2;j>i;j--){
                valorXAtual -= matrizB.get(i, j)*solucao.get(j,0);
            }
            valorXAtual/=matrizB.get(i,i);
            solucao.set(valorXAtual,i,0);
        }

        return new RetornoEliminacaoGauss(iteracoes, solucao);
    }

    public RetornoLU fatoracaoLU(MatrizDouble matrizB){
        int altura = matrizB.getAltura();
        int largura = matrizB.getLargura();

        boolean necessitaTroca = false;
        int linhaPraTrocar1 = 0;
        int linhaPraTrocar2 = 0;
        do{
            for(int i=1;i<=altura;i++){
                if(matrizB.submatriz(i,i).determinante()==0){
                    necessitaTroca=true;
                }
            }
            if(necessitaTroca){
                linhaPraTrocar2++;
                if(linhaPraTrocar2==altura){
                    linhaPraTrocar1++;
                    linhaPraTrocar2=linhaPraTrocar1+1;
                }
                if(linhaPraTrocar1<altura-2){
                    matrizB.trocarLinhas(linhaPraTrocar1, linhaPraTrocar2);
                }
            }
        }while(necessitaTroca && linhaPraTrocar1<altura-2);
        if(necessitaTroca){
            throw new ResolucaoException("Determinante de uma submatriz nulo!");
        }

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


        return new RetornoLU(matrizB.criarCopia(), matrizL.criarCopia(), matrizU.criarCopia(), solucaoX, solucaoY);
    }
    
    public RetornoGaussSeidel gaussSeidel(MatrizDouble matrizB, double precisao, MatrizDouble matrizChutesIniciais, int it){
        List<IteracaoGaussSeidel> iteracoes = new ArrayList<>();
        int altura = matrizB.getAltura();
        int largura = matrizB.getLargura();
        MatrizDouble matrizXAtual = matrizChutesIniciais.criarCopia();
        MatrizDouble matrizXAnterior = matrizChutesIniciais.criarCopia();

        int linhaPraTrocar1 = 0;
        int linhaPraTrocar2 = 0;
        boolean criterioSassenfeld = false;
        do{ 
            criterioSassenfeld = true;
            List<Double> betas = new ArrayList<>();
            for(int i=0;i<altura;i++){
                double betaAtual=0;
                for(int j=0;j<largura-1;j++){
                    if(j>i){
                        betaAtual+=Math.abs(matrizB.get(i,j));
                    }
                    else if(j<i){
                        betaAtual+=Math.abs(matrizB.get(i,j))*betas.get(j);
                    }
                }
                betaAtual/=Math.abs(matrizB.get(i,i));
                if(betaAtual>=1){
                    criterioSassenfeld = false;
                }
                betas.add(betaAtual);
            }
            if(!criterioSassenfeld){
                linhaPraTrocar2++;
                if(linhaPraTrocar2==altura){
                    linhaPraTrocar1++;
                    linhaPraTrocar2=linhaPraTrocar1+1;
                }
                if(linhaPraTrocar1<altura-2){
                    matrizB.trocarLinhas(linhaPraTrocar1, linhaPraTrocar2);
                }
            }
        }while(!criterioSassenfeld && linhaPraTrocar1<altura-2);
        if(!criterioSassenfeld){
            throw new ResolucaoException("Não passou no critério Sassenfeld");
        }

        int k=0;
        do{
            k++;
            matrizXAnterior = matrizXAtual.criarCopia();
            for(int i=0;i<altura;i++){
                double xAtual = matrizB.get(i, largura-1);
                for(int j=0;j<altura;j++){
                    if(j!=i){
                        xAtual-=matrizB.get(i,j)*matrizXAtual.get(j,0);
                    }
                }
                xAtual/=matrizB.get(i,i);
                matrizXAtual.set(xAtual,i,0);
            }
            iteracoes.add(new IteracaoGaussSeidel(matrizXAtual.criarCopia(), matrizXAnterior.criarCopia(), precisao));
        }while(k<it&iteracoes.get(iteracoes.size()-1).getErroRelativo()>=precisao);


        return new RetornoGaussSeidel(matrizB.criarCopia(), iteracoes, matrizXAtual.criarCopia());
    }
}
