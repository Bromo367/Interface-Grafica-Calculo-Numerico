package service.metodos;
import service.funcoes.Funcao;

import java.util.ArrayList;

import model.metodosRaizes.iteracoes.IteracaoBisseccao;
import model.metodosRaizes.iteracoes.IteracaoNewtonRaphson;
import model.metodosRaizes.iteracoes.IteracaoSecante;
import model.metodosRaizes.iteracoes.IteracaoMIL;
import model.metodosRaizes.iteracoes.IteracaoRegulaFalsi;

import model.metodosRaizes.retornos.RetornoBisseccao;
import model.metodosRaizes.retornos.RetornoNewtonRaphson;
import model.metodosRaizes.retornos.RetornoSecante;
import model.metodosRaizes.retornos.RetornoMIL;
import model.metodosRaizes.retornos.RetornoRegulaFalsi;

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
    public RetornoMIL mil(double x0, double precisao, int it, Funcao f, Funcao fi){
        ArrayList<IteracaoMIL> iteracoes = new ArrayList<IteracaoMIL>();
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

            System.out.println(fi.arvoreEmOrdem());

            iteracoes.add(new IteracaoMIL(k, xAtual, fAtual, fi.aplicar(xAtual), precisao, xAnterior));

            parou=parou||iteracoes.get(k-1).criterioParadaFuncao();
            parou=parou||iteracoes.get(k-1).criterioParadaDiferenca();            
            k++;
        }
        String criterioParada;
        IteracaoMIL ultimaIteracao=iteracoes.get(iteracoes.size()-1);
        if(ultimaIteracao.criterioParadaFuncao()){
            criterioParada="|f(x"+(k-2)+")| = "+Math.abs(ultimaIteracao.getFxn())+" < "+precisao;
        }
        else if(ultimaIteracao.criterioParadaDiferenca()){
            criterioParada="|"+xAtual+"-"+xAnterior+"| = "+(Math.abs(ultimaIteracao.getDiferenca()))+" < "+precisao;
        }
        else{
            criterioParada="Ultrapassou o limite de iterações";
        }
        Raiz raiz = new Raiz(k-1,ultimaIteracao.getXAtual(),ultimaIteracao.getFxn(),criterioParada);
        RetornoMIL retorno = new RetornoMIL(iteracoes, raiz);
        return retorno;
    }

    public RetornoNewtonRaphson newton(double x0, double precisao, int it, Funcao f){
        ArrayList<IteracaoNewtonRaphson> iteracoes = new ArrayList<>();

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

            iteracoes.add(new IteracaoNewtonRaphson(k, xAtual, fAtual , fDerivada.aplicar(xAtual), precisao, xAnterior));
            System.out.println("Derivas "+iteracoes.getLast().getDerivadaXn());

            parou=parou||iteracoes.get(k-1).criterioParadaFuncao();
            parou=parou||iteracoes.get(k-1).criterioParadaDiferenca();       

            k++;
        }
        String criterioParada;
        IteracaoNewtonRaphson ultimaIteracao=iteracoes.get(iteracoes.size()-1);
        if(ultimaIteracao.criterioParadaFuncao()){
            criterioParada="|f(x"+(k-2)+")| = "+Math.abs(ultimaIteracao.getFxn())+" < "+precisao;
        }
        else if(ultimaIteracao.criterioParadaDiferenca()){
            criterioParada="|"+xAtual+"-"+xAnterior+"| = "+(Math.abs(ultimaIteracao.getDiferenca()))+" < "+precisao;
        }
        else{
            criterioParada="Ultrapassou o limite de iterações";
        }
        Raiz raiz = new Raiz(k-1,ultimaIteracao.getXAtual(),ultimaIteracao.getFxn(),criterioParada);
        RetornoNewtonRaphson retorno = new RetornoNewtonRaphson(iteracoes, raiz);
        return retorno;
    }

    public RetornoSecante secante(double x0, double x1, double precisao, int it, Funcao f){
        ArrayList<IteracaoSecante> iteracoes = new ArrayList<>();
        double xAnterior=x0;
        double xAtual=x1;

        double fAtual=f.aplicar(xAtual);
        double fAnterior=f.aplicar(xAnterior);
        double valorRaiz=0;
        boolean parou=false;
        
        if(parada.erroFuncao(fAtual, 0, precisao)){
            parou=true;
            valorRaiz=xAnterior;
        }
        else if(parada.erroFuncao(fAtual, 1, precisao)){
            parou=true;
            valorRaiz=xAtual;
        }
        else if(parada.erroAbsoluto(xAtual, xAnterior, 1, precisao)){
            parou=true;
            valorRaiz=xAtual;
        }

        int k=1;
        while(k<=it&&!parou){
            double aux=xAtual;
            xAtual=xAtual-((fAtual*(xAtual-xAnterior))/(fAtual-fAnterior));
            xAnterior=aux;
            fAtual=f.aplicar(xAtual);
            fAnterior=f.aplicar(xAnterior);

            iteracoes.add(new IteracaoSecante(k, xAtual, fAtual, fAnterior, precisao, xAnterior));

            parou=parou||iteracoes.get(k-1).criterioParadaFuncao();
            parou=parou||iteracoes.get(k-1).criterioParadaDiferenca();       

            if(parou){
                valorRaiz=xAtual;
            }
            k++;
        }

        String criterioParada;
        IteracaoSecante ultimaIteracao=iteracoes.get(iteracoes.size()-1);
        if(ultimaIteracao.criterioParadaFuncao()){
            criterioParada="|f(x"+(k-2)+")| = "+Math.abs(ultimaIteracao.getFxAtual())+" < "+precisao;
        }
        else if(ultimaIteracao.criterioParadaDiferenca()){
            criterioParada="|"+xAtual+"-"+xAnterior+"| = "+(Math.abs(ultimaIteracao.getDiferenca()))+" < "+precisao;
        }
        else{
            criterioParada="Ultrapassou o limite de iterações";
        }
        Raiz raiz = new Raiz(k-1,ultimaIteracao.getXAtual(),ultimaIteracao.getFxAtual(),criterioParada);
        RetornoSecante retorno = new RetornoSecante(iteracoes, raiz);
        return retorno;
    }

    public RetornoRegulaFalsi regulaFalsi(double a, double b, double precisao, int it, Funcao f){
        ArrayList<IteracaoRegulaFalsi> iteracoes = new ArrayList<>();
        double xAtual=0;
        double fInicio=f.aplicar(a);
        double fFim=f.aplicar(b);
        double fAtual=0;
        double valorRaiz=0;
        boolean parou=false;

        
        if(parada.erroFuncaoVariavel(fInicio,"a",precisao)){
            parou=true;
            valorRaiz=a;
        }
        else if(parada.erroFuncaoVariavel(fInicio,"b",precisao)){
            parou=true;
            valorRaiz=b;
        }
        else if(parada.diferencaIntervalo(a, b, precisao)){
            parou=true;
            valorRaiz=a;
        }

        int k=1;
        while(k<=it&&!parou){
            xAtual=((a*fFim)-(b*fInicio))/(fFim-fInicio);
            fAtual=f.aplicar(xAtual);

            if(fInicio*fAtual<0){
                b=xAtual;
                fFim=fAtual;
            }
            else{
                a=xAtual;
                fInicio=fAtual;
            }

            iteracoes.add(new IteracaoRegulaFalsi(k, xAtual, fAtual, a, b, precisao));

            parou = parou || iteracoes.get(k-1).criterioParadaFuncao();
            parou = parou || iteracoes.get(k-1).criterioParadaErroRelativo();

            if(parou){
                valorRaiz=xAtual;
            }
            k++;
        }

        String criterioParada;
        IteracaoRegulaFalsi ultimaIteracao = iteracoes.get(iteracoes.size()-1);
        if(ultimaIteracao.criterioParadaFuncao()){
            criterioParada="|f(x"+(k-2)+")| = "+Math.abs(ultimaIteracao.getFxn())+" < "+precisao;
        }
        else if(ultimaIteracao.criterioParadaErroRelativo()){
            criterioParada="|("+b+"-"+a+")/"+xAtual+"| = "+(ultimaIteracao.getErroRelativo())+" < "+precisao;
        }
        else{
            criterioParada="Ultrapassou o limite de iterações";
        }
        Raiz raiz = new Raiz(k-1,ultimaIteracao.getXn(),ultimaIteracao.getFxn(),criterioParada);
        RetornoRegulaFalsi retorno = new RetornoRegulaFalsi(iteracoes, raiz);
        return retorno;
    }
}
