package service.metodos;

import java.util.List;
import java.util.ArrayList;

import service.funcoes.Funcao;
import model.integracao.retornos.RetornoTrapezio;
import model.integracao.subintervalos.SubintervaloTrapezio;
import model.integracao.retornos.RetornoSimpson;
import model.integracao.subintervalos.SubintervaloSimpson;

public class MetodosIntegracao {
    public RetornoTrapezio trapezioRepetido(double a, double b, int nSubintervalos, Funcao f){
        List<SubintervaloTrapezio> subintervalos = new ArrayList<>();

        double h=(b-a)/nSubintervalos;
        
        for(int i=0;i<nSubintervalos;i++){
            double xAtual = a+((i+1)*h);
            double xAnterior = a+(i*h);
            double fxAtual = f.aplicar(xAtual);
            double fxAnterior = f.aplicar(xAnterior);
            subintervalos.add(new SubintervaloTrapezio(i+1, xAtual, fxAtual, xAnterior, fxAnterior));
        }

        return new RetornoTrapezio(subintervalos);
    }

    public RetornoSimpson simpsonRepetido(double a, double b, int nSubintervalos, Funcao f){
        List<SubintervaloSimpson> subintervalos = new ArrayList<>();

        double h=(b-a)/nSubintervalos;
        
        for(int i=0;i<nSubintervalos;i+=2){
            double xAnterior = a+(i*h);
            double xAtual = a+((i+1)*h);
            double xProximo = a+((i+2)*h);
            double fxAnterior = f.aplicar(xAnterior);
            double fxAtual = f.aplicar(xAtual);
            double fxProximo = f.aplicar(xProximo);
            subintervalos.add(new SubintervaloSimpson((i/2)+1, xAtual, fxAtual, xAnterior, fxAnterior, xProximo, fxProximo));
        }

        return new RetornoSimpson(subintervalos);
    }
    
}
