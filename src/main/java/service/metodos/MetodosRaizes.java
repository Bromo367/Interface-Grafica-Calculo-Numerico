package service.metodos;
import service.funcoes.Funcao;

import java.util.ArrayList;

import model.metodosRaizes.iteracoes.IteracaoBisseccao;
import model.metodosRaizes.retornos.RetornoBisseccao;
import model.metodosRaizes.Raiz;

public class MetodosRaizes {
    private CriteriosParadaRaizes parada = new CriteriosParadaRaizes();

    public RetornoBisseccao bisseccao(double a, double b, double precisao, int it, Funcao f){
        ArrayList<IteracaoBisseccao> iteracoes = new ArrayList<IteracaoBisseccao>();
        int k=1;
        double fInicio;
        double meio=0;
        double fMeio;
        boolean parou=false;
        while(!parou&&k<=it){
            fInicio = f.aplicar(a);
            meio = (a+b)/2;
            fMeio = f.aplicar(meio);

            if(fInicio*fMeio<0){
                b=meio;
            }
            else{
                a=meio;
            }
            iteracoes.add(new IteracaoBisseccao(k, meio, fMeio, a, b, precisao));

            parou=parou||iteracoes.get(k-1).criterioParadaFuncao();
            parou=parou||iteracoes.get(k-1).criterioParadaIntervalo();
            k++;
        }
        String criterioParada;
        IteracaoBisseccao ultimaIteracao=iteracoes.get(iteracoes.size()-1);
        if(ultimaIteracao.criterioParadaFuncao()){
            criterioParada="|f(x"+(k-2)+")| = "+Math.abs(ultimaIteracao.getFxn())+" < "+precisao;
        }
        else if(ultimaIteracao.criterioParadaIntervalo()){
            criterioParada="|"+b+"-"+a+"| = "+(Math.abs(ultimaIteracao.getDiferencaIntervalo()))+" < "+precisao;
        }
        else{
            criterioParada="Ultrapassou o limite de iterações";
        }
        Raiz raiz = new Raiz(k-1,ultimaIteracao.getXn(),ultimaIteracao.getFxn(),criterioParada);
        RetornoBisseccao retorno = new RetornoBisseccao(iteracoes, raiz);
        return retorno;
    }
    public void mil(double x0, double precisao, int it, Funcao f, Funcao fi){
        double xAtual=x0;
        double xAnterior=xAtual;

        double fAtual=f.aplicar(xAtual);
        boolean parou=false;

        parou=parada.erroFuncao(fAtual, 0, precisao);

        int k=1;
        while(k<=it&&!parou){
            xAnterior=xAtual;
            xAtual=fi.aplicar(xAtual);
            fAtual=f.aplicar(xAtual);

            parou=parou||parada.erroFuncao(fAtual, k, precisao);
            parou=parou||parada.erroAbsoluto(xAtual, xAnterior, k, precisao);            
            k++;
        }
    }

    public void newton(double x0, double precisao, int it, Funcao f){
        double xAtual=x0;
        double xAnterior=xAtual;

        Funcao fDerivada = f.derivar();

        double fAtual=f.aplicar(xAtual);
        boolean parou=false;
        parou=parada.erroFuncao(fAtual, 0, precisao);

        int k=1;
        while(k<=it&&!parou){
            xAnterior=xAtual;
            xAtual=xAtual-(fAtual/fDerivada.aplicar(xAtual));
            fAtual=f.aplicar(xAtual);

            parou=parou||parada.erroFuncao(fAtual, k, precisao);
            parou=parou||parada.erroAbsoluto(xAtual, xAnterior, k, precisao);

            k++;
        }
    }

    public void secante(double x0, double x1, double precisao, int it, Funcao f){
        double xAnterior=x0;
        System.out.println("Insira x1:");
        double xAtual=x1;

        double fAtual=f.aplicar(xAtual);
        double fAnterior=f.aplicar(xAnterior);
        double raiz=0;
        boolean parou=false;
        
        if(parada.erroFuncao(fAtual, 0, precisao)){
            parou=true;
            raiz=xAnterior;
        }
        else if(parada.erroFuncao(fAtual, 1, precisao)){
            parou=true;
            raiz=xAtual;
        }
        else if(parada.erroAbsoluto(xAtual, xAnterior, 1, precisao)){
            parou=true;
            raiz=xAtual;
        }

        int k=1;
        while(k<=it&&!parou){
            double aux=xAtual;
            xAtual=xAtual-((fAtual*(xAtual-xAnterior))/(fAtual-fAnterior));
            xAnterior=aux;
            fAtual=f.aplicar(xAtual);
            fAnterior=f.aplicar(xAnterior);

            parou=parou||parada.erroFuncao(fAtual, k+1, precisao);
            parou=parou||parada.erroAbsoluto(xAtual, xAnterior, k+1, precisao);

            if(parou){
                raiz=xAtual;
            }
            k++;
        }
    }

    public void regulaFalsi(double a, double b, double precisao, int it, Funcao f){
        double xAtual=0;
        double fInicio=f.aplicar(a);
        double fFim=f.aplicar(b);
        double fAtual=0;
        double raiz=0;
        boolean parou=false;

        
        if(parada.erroFuncaoVariavel(fInicio,"a",precisao)){
            parou=true;
            raiz=a;
        }
        else if(parada.erroFuncaoVariavel(fInicio,"b",precisao)){
            parou=true;
            raiz=b;
        }
        else if(parada.diferencaIntervalo(a, b, precisao)){
            parou=true;
            raiz=a;
        }

        int k=1;
        while(k<=it&&!parou){
            xAtual=((a*fFim)-(b*fInicio))/(fFim-fInicio);
            fAtual=f.aplicar(xAtual);

            parou=parou||parada.erroFuncao(fAtual, k-1, precisao);
            if(fInicio*fAtual<0){
                b=xAtual;
                fFim=fAtual;
                parou=parou||parada.erroRelativo(xAtual, a, k-1, precisao);
            }
            else{
                a=xAtual;
                fInicio=fAtual;
                parou=parou||parada.erroRelativo(xAtual, b, k-1, precisao);
            }

            if(parou){
                raiz=xAtual;
            }
            k++;
        }
    }
}
