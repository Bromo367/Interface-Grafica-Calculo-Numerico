package service.metodos;
import service.funcoes.Funcao;

import java.text.DecimalFormat;
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

    public RetornoBisseccao bisseccao(double a, double b, double precisao, int it, Funcao f) throws ResolucaoException{
        if(precisao < 0){
            throw new ResolucaoException("A precisão deve ser positiva");
        }
        if(it < 0){
            throw new ResolucaoException("O número máximo de iterações deve ser positivo");
        }
        if(f.aplicar(a)*f.aplicar(b)>0){
            throw new ResolucaoException("O intervalo dado não contém necessariamente uma raiz");
        }
        if(a>=b){
            throw new ResolucaoException("O intervalo dado não é válido");
        }

        String formato = "0.";
        int i=0;
        while(precisao*Math.pow(10,i)<1){
            formato+="##";
            i++;
        }
        DecimalFormat df = new DecimalFormat(formato);
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
            criterioParada="|f(x"+(k-2)+")| = "+df.format(Math.abs(ultimaIteracao.getFxn()))+" < "+df.format(precisao);
        }
        else if(ultimaIteracao.criterioParadaIntervalo()){
            criterioParada="|"+df.format(b)+"-"+df.format(a)+"| = "
                    +df.format(Math.abs(ultimaIteracao.getDiferencaIntervalo()))+" < "+df.format(precisao);
        }
        else{
            criterioParada="Ultrapassou o limite de iterações";
        }
        Raiz raiz = new Raiz(k-1,ultimaIteracao.getXn(),ultimaIteracao.getFxn(),criterioParada, precisao);
        RetornoBisseccao retorno = new RetornoBisseccao(iteracoes, raiz);
        return retorno;
    }

    public RetornoMIL mil(double x0, double precisao, int it, Funcao f, Funcao fi) throws ResolucaoException{
        if(precisao < 0){
            throw new ResolucaoException("A precisão deve ser positiva");
        }
        if(it < 0){
            throw new ResolucaoException("O número máximo de iterações deve ser positivo");
        }

        String formato = "0.";
        int i=0;
        while(precisao*Math.pow(10,i)<1){
            formato+="##";
            i++;
        }
        DecimalFormat df = new DecimalFormat(formato);

        ArrayList<IteracaoMIL> iteracoes = new ArrayList<IteracaoMIL>();
        double xAtual=x0;
        double xAnterior=xAtual;

        double fAtual=f.aplicar(xAtual);
        boolean parou=false;

        if(Math.abs(fAtual)<precisao){
            String criterioParada;
            criterioParada="|f(x0)| = "+df.format(Math.abs(fAtual))+" < "+df.format(precisao);
            Raiz raiz = new Raiz(0, xAtual, fAtual, criterioParada, precisao);
            return new RetornoMIL(iteracoes, raiz);
        }
        parou = false;

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
            criterioParada="|f(x"+(k-1)+")| = "+df.format(Math.abs(ultimaIteracao.getFxn()))+" < "+df.format(precisao);
        }
        else if(ultimaIteracao.criterioParadaDiferenca()){
            criterioParada="|"+df.format(xAtual)+"-"+df.format(xAnterior)+"| = "
                +df.format(Math.abs(ultimaIteracao.getDiferenca()))+" < "+df.format(precisao);
        }
        else{
            criterioParada="Ultrapassou o limite de iterações";
        }
        Raiz raiz = new Raiz(k-1,ultimaIteracao.getXAtual(),ultimaIteracao.getFxn(),criterioParada, precisao);
        RetornoMIL retorno = new RetornoMIL(iteracoes, raiz);
        return retorno;
    }

    public RetornoNewtonRaphson newtonRaphson(double x0, double precisao, int it, Funcao f) throws ResolucaoException{
        if(precisao < 0){
            throw new ResolucaoException("A precisão deve ser positiva");
        }
        if(it < 0){
            throw new ResolucaoException("O número máximo de iterações deve ser positivo");
        }

        String formato = "0.";
        int i=0;
        while(precisao*Math.pow(10,i)<1){
            formato+="##";
            i++;
        }
        DecimalFormat df = new DecimalFormat(formato);

        ArrayList<IteracaoNewtonRaphson> iteracoes = new ArrayList<>();

        double xAtual=x0;
        double xAnterior=xAtual;

        Funcao fDerivada = f.derivar();

        double fAtual=f.aplicar(xAtual);
        boolean parou=false;

        if(Math.abs(fAtual)<precisao){
            String criterioParada;
            criterioParada="|f(x0)| = "+df.format(Math.abs(fAtual))+" < "+precisao;
            Raiz raiz = new Raiz(0, xAtual, fAtual, criterioParada, precisao);
            return new RetornoNewtonRaphson(iteracoes, raiz);
        }
        parou = false;

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
            criterioParada="|f(x"+(k-1)+")| = "+df.format(Math.abs(ultimaIteracao.getFxn()))+" < "+df.format(precisao);
        }
        else if(ultimaIteracao.criterioParadaDiferenca()){
            criterioParada="|"+df.format(xAtual)+"-"+df.format(xAnterior)+"| = "
                    +df.format(Math.abs(ultimaIteracao.getDiferenca()))+" < "+df.format(precisao);
        }
        else{
            criterioParada="Ultrapassou o limite de iterações";
        }
        Raiz raiz = new Raiz(k-1,ultimaIteracao.getXAtual(),ultimaIteracao.getFxn(),criterioParada, precisao);
        RetornoNewtonRaphson retorno = new RetornoNewtonRaphson(iteracoes, raiz);
        return retorno;
    }

    public RetornoSecante secante(double x0, double x1, double precisao, int it, Funcao f) throws ResolucaoException{
        if(precisao < 0){
            throw new ResolucaoException("A precisão deve ser positiva");
        }
        if(it < 0){
            throw new ResolucaoException("O número máximo de iterações deve ser positivo");
        }

        String formato = "0.";
        int i=0;
        while(precisao*Math.pow(10,i)<1){
            formato+="##";
            i++;
        }
        DecimalFormat df = new DecimalFormat(formato);

        ArrayList<IteracaoSecante> iteracoes = new ArrayList<>();
        double xAnterior=x0;
        double xAtual=x1;

        double fAtual=f.aplicar(xAtual);
        double fAnterior=f.aplicar(xAnterior);
        boolean parou=false;

        String criterioParada;
        
        if(Math.abs(fAnterior) < precisao){
            criterioParada="|f(x0)| = "+df.format(Math.abs(fAnterior))+" < "+df.format(precisao);
            Raiz raiz = new Raiz(0, xAnterior, fAnterior, criterioParada, precisao);
            return new RetornoSecante(iteracoes, raiz);
        }
        else if(Math.abs(fAtual) < precisao){
            criterioParada = "|f(x1)| = "+df.format(Math.abs(fAtual))+" < "+df.format(precisao);
            Raiz raiz = new Raiz(0, xAtual, fAtual, criterioParada, precisao);
            return new RetornoSecante(iteracoes, raiz);
        }
        else if(Math.abs(xAtual-xAnterior) < precisao){
            criterioParada = "|"+df.format(xAtual)+"-"+df.format(xAnterior)+"| = "
                    +df.format(Math.abs(xAtual-xAnterior))+" < "+df.format(precisao);
            Raiz raiz = new Raiz(0, xAtual, fAtual, criterioParada, precisao);
            return new RetornoSecante(iteracoes, raiz);
        }

        int k=1;
        while(k<=it&&!parou){
            double aux = xAtual;
            xAtual = xAtual-((fAtual*(xAtual-xAnterior))/(fAtual-fAnterior));
            xAnterior = aux;
            fAtual = f.aplicar(xAtual);
            fAnterior = f.aplicar(xAnterior);

            iteracoes.add(new IteracaoSecante(k, xAtual, fAtual, fAnterior, precisao, xAnterior));

            parou = parou||iteracoes.get(k-1).criterioParadaFuncao();
            parou = parou||iteracoes.get(k-1).criterioParadaDiferenca();       

            k++;
        }

        IteracaoSecante ultimaIteracao=iteracoes.get(iteracoes.size()-1);
        if(ultimaIteracao.criterioParadaFuncao()){
            criterioParada="|f(x"+k+")| = "+df.format(Math.abs(ultimaIteracao.getFxAtual()))+" < "+df.format(precisao);
        }
        else if(ultimaIteracao.criterioParadaDiferenca()){
            criterioParada="|"+df.format(xAtual)+"-"+df.format(xAnterior)+"| = "
                    +df.format(Math.abs(ultimaIteracao.getDiferenca()))+" < "+df.format(precisao);
        }
        else{
            criterioParada="Ultrapassou o limite de iterações";
        }
        Raiz raiz = new Raiz(k-1,ultimaIteracao.getXAtual(),ultimaIteracao.getFxAtual(),criterioParada, precisao);
        RetornoSecante retorno = new RetornoSecante(iteracoes, raiz);
        return retorno;
    }

    public RetornoRegulaFalsi regulaFalsi(double a, double b, double precisao, int it, Funcao f) throws ResolucaoException{
        if(precisao < 0){
            throw new ResolucaoException("A precisão deve ser positiva");
        }
        if(it < 0){
            throw new ResolucaoException("O número máximo de iterações deve ser positivo");
        }
        if(f.aplicar(a)*f.aplicar(b)>0){
            throw new ResolucaoException("O intervalo dado não contém necessariamente uma raiz");
        }
        if(a>=b){
            throw new ResolucaoException("O intervalo dado não é válido");
        }
        
        String formato = "0.";
        int i=0;
        while(precisao*Math.pow(10,i)<1){
            formato+="##";
            i++;
        }
        DecimalFormat df = new DecimalFormat(formato);

        ArrayList<IteracaoRegulaFalsi> iteracoes = new ArrayList<>();
        double xAtual=0;
        double fInicio=f.aplicar(a);
        double fFim=f.aplicar(b);
        double fAtual=0;
        boolean parou=false;

        String criterioParada;

        
        if(Math.abs(fInicio) < precisao){
            criterioParada="|f(a)| = "+df.format(Math.abs(fInicio))+" < "+df.format(precisao);
            Raiz raiz = new Raiz(0, a, a, criterioParada, precisao);
            return new RetornoRegulaFalsi(iteracoes, raiz);
        }
        else if(Math.abs(fFim) < precisao){
            criterioParada="|f(b)| = "+df.format(Math.abs(fFim))+" < "+df.format(precisao);
            Raiz raiz = new Raiz(0, b, fFim, criterioParada, precisao);
            return new RetornoRegulaFalsi(iteracoes, raiz);
        }
        else if(Math.abs(b-a) < precisao){
            criterioParada="|"+df.format(b)+"-"+df.format(a)+"| = "
                    +df.format(Math.abs(b-a))+" < "+df.format(precisao);
            Raiz raiz = new Raiz(0, b, fFim, criterioParada, precisao);
            return new RetornoRegulaFalsi(iteracoes, raiz);
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

            k++;
        }

        IteracaoRegulaFalsi ultimaIteracao = iteracoes.get(iteracoes.size()-1);
        if(ultimaIteracao.criterioParadaFuncao()){
            criterioParada="|f(x"+(k-2)+")| = "+df.format(Math.abs(ultimaIteracao.getFxn()))+" < "+df.format(precisao);
        }
        else if(ultimaIteracao.criterioParadaErroRelativo()){
            criterioParada="|("+df.format(b)+"-"+df.format(a)+")/"+df.format(xAtual)+"| = "
                    +df.format(ultimaIteracao.getErroRelativo())+" < "+df.format(precisao);
        }
        else{
            criterioParada="Ultrapassou o limite de iterações";
        }
        Raiz raiz = new Raiz(k-1,ultimaIteracao.getXn(),ultimaIteracao.getFxn(),criterioParada, precisao);
        RetornoRegulaFalsi retorno = new RetornoRegulaFalsi(iteracoes, raiz);
        return retorno;
    }
}
