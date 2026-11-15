package service.metodos;

import java.util.List;
import java.util.ArrayList;

import service.funcoes.Funcao;
import model.integracao.retornos.RetornoTrapezio;
import model.integracao.subintervalos.SubintervaloTrapezio;

public class MetodosIntegracao {
    public RetornoTrapezio trapezioRepetido(double a, double b, int nSubintervalos, Funcao f){
        List<SubintervaloTrapezio> subintervalos = new ArrayList<>();

        double h=(b-a)/nSubintervalos;
        
        for(int i=0;i<nSubintervalos;i++){
            double xAtual = a+((i+1)*h);
            double xAnterior = a+(i*h);
            double fxAtual = f.aplicar(xAtual);
            double fxAnterior = f.aplicar(xAnterior);
            subintervalos.add(new SubintervaloTrapezio(i, xAtual, fxAtual, xAnterior, fxAnterior));
        }

        return new RetornoTrapezio(subintervalos);
    }
}
