package service.metodos;

import java.util.List;
import java.util.ArrayList;

import model.geral.Ponto;
import model.metodosInterpolacao.RetornoLagrange;

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
}
