package model.geral;

import java.util.List;
import java.util.ArrayList;

public class MatrizDouble {
    private List<List<Double>> valores = new ArrayList<>();
    private int altura;
    private int largura;

    public MatrizDouble(int largura, int altura){
        this.largura = largura;
        this.altura = altura;
        for(int i=0;i<altura;i++){
            valores.add(new ArrayList<>());
            for(int j=0;j<largura; j++){
                valores.get(i).add(0.0);
            }
        }
    }

    public Double get(int linha,int coluna){
        return valores.get(linha).get(coluna);
    }

    public void set(double valor, int linha, int coluna){
        valores.get(linha).set(coluna, valor);
    }

    public List<Double> getLinha(int idxLinha){
        List<Double> linha = new ArrayList<>();
        for(int i=0;i<largura;i++){
            linha.add(Double.valueOf((valores.get(idxLinha).get(i))));
        }
        return linha;
    }

    public List<Double> getColuna(int idxColuna){
        List<Double> coluna = new ArrayList<>();
        for(int i=0;i<altura;i++){
            coluna.add(Double.valueOf((valores.get(i).get(idxColuna))));
        }
        return coluna;
    }

    public void adicionarLinha(List<Double> novaLinha){
        if(novaLinha.size()==largura){
            altura++;
            valores.add(new ArrayList<>());
            for(int i=0;i<largura; i++){
                valores.get(altura-1).add(Double.valueOf(novaLinha.get(i)));
            }
        }
    }

    public void adicionarColuna(List<Double> novaColuna){
        if(novaColuna.size()==altura){
            altura++;
            for(int i=0;i<altura;i++){
                valores.get(i).add(Double.valueOf(novaColuna.get(i)));
            }
        }
    }

    public void trocarLinhas(int linha1,int linha2){
        List<Double> aux = new ArrayList<>();
        for(int i=0;i<largura;i++){
            aux.add(valores.get(linha1).get(i));
        }
        for(int i=0;i<largura;i++){
            valores.get(linha1).set(i, valores.get(linha2).get(i));
        }
        for(int i=0;i<largura;i++){
            valores.get(linha2).set(i, aux.get(i));
        }
    }

    public void trocarColunas(int coluna1,int coluna2){
        List<Double> aux = new ArrayList<>();
        for(int i=0;i<altura;i++){
            aux.add(valores.get(i).get(coluna1));
        }
        for(int i=0;i<altura;i++){
            valores.get(i).set(coluna1, valores.get(i).get(coluna2));
        }
        for(int i=0;i<altura;i++){
            valores.get(i).set(coluna2, aux.get(i));
        }
    }

    public MatrizDouble submatriz(int larguraSubmatriz, int alturaSubmatriz){
        MatrizDouble submatriz = new MatrizDouble(larguraSubmatriz, alturaSubmatriz);
        for(int i=0;i<alturaSubmatriz;i++){
            for(int j=0;j<larguraSubmatriz;j++){
                submatriz.set(get(i,j), i, j);
            }
        }
        return submatriz;
    }

    public MatrizDouble criarCopia(){
        MatrizDouble novaMatriz = new MatrizDouble(largura, altura);
        for(int i=0;i<altura;i++){
            for(int j=0;j<largura;j++){
                novaMatriz.set(get(i,j), i, j);
            }
        }
        return novaMatriz;
    }

    public void somarValorLinha(int linha, double valor){
        for(int i=0;i<largura;i++){
            set(get(linha,i)+valor,linha,i);
        }
    }

    public void somarLinhas(int linha1, int linha2, double multiplicador){
        for(int i=0;i<largura;i++){
            set(get(linha1,i)+(get(linha2,i)*multiplicador),linha1,i);
        }
    }

    public double determinante(){
        if(altura!=largura){
            return -1;
        }
        if(altura==1){
            return get(0,0);
        }
        double determinante = 0;
        for(int i=0;i<largura;i++){
            MatrizDouble submatriz = new MatrizDouble(largura-1, altura-1);
            for(int j=0;j<altura-1;j++){
                int deslocamento=0;
                for(int k=0;k<largura;k++){
                    if(k==i){
                        deslocamento=-1;
                    }
                    else{
                        submatriz.set(get(j+1,k),j,k+deslocamento);
                    }
                }
            }
            double parcela = get(0, i)*submatriz.determinante();
            if(i%2!=0){
                parcela*=-1;
            }
            determinante+=parcela;
        }

        return determinante;
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }
}
