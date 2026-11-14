package service.metodos;

import java.util.List;
import java.util.ArrayList;

import model.geral.Ponto;
import model.interpolacao.RetornoFormaNewton;
import model.interpolacao.RetornoLagrange;

public class MetodosInterpolacao {
    public RetornoLagrange lagrange(List<Ponto> pontos){
        int n=pontos.size();
        String polinomio = "";
        List<String> subpolinomios = new ArrayList<>();

        for(int i=0;i<n;i++){
            String subpolinomioAtual = "(";
            Double denominador = 1.0;
            for(int j=0;j<n;j++){
                if(i!=j){
                    subpolinomioAtual+="(x-"+pontos.get(j).getX().toString()+")*";
                    denominador*=(pontos.get(i).getX()-pontos.get(j).getX());
                }
            }
            subpolinomioAtual=subpolinomioAtual.substring(0,subpolinomioAtual.length()-1);
            subpolinomioAtual+="/("+denominador.toString()+"))";
            subpolinomios.add(subpolinomioAtual);
        }

        for(int i=0;i<n;i++){
            polinomio+=pontos.get(i).getY().toString()+"*"+subpolinomios.get(i)+"+";
        }

        polinomio=polinomio.substring(0,polinomio.length()-1);

        RetornoLagrange retorno = new RetornoLagrange(polinomio, subpolinomios);
        return retorno;
    }

    public RetornoFormaNewton formaNewton(List<Ponto> pontos){
        String polinomio="";
        int n=pontos.size();
        double[][] diferencasDivididas = new double[n][n];
        for(int i=0;i<n;i++){
            diferencasDivididas[i][0]=pontos.get(i).getY();
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<n-i;j++){
                double diferencaAtual = (diferencasDivididas[j+1][i-1]-diferencasDivididas[j][i-1]);
                double denominador = pontos.get(j+i).getX()-pontos.get(j).getX();
                diferencasDivididas[j][i] = diferencaAtual/denominador;
            }
        }
        for(int i=0;i<n;i++){
            polinomio+=diferencasDivididas[0][i];
            for(int j=0;j<i;j++){
                polinomio+="*(x-"+pontos.get(j).getX()+")";
            }
            polinomio += "+";
        }
        polinomio = polinomio.substring(0,polinomio.length()-1);

        List<Double> diferencasPrincipais = new ArrayList<>();
        for(int i=0;i<n;i++){
            diferencasPrincipais.add(diferencasDivididas[0][i]);
        }

        RetornoFormaNewton retorno = new RetornoFormaNewton(polinomio, diferencasPrincipais);
        return retorno;
    }
}
