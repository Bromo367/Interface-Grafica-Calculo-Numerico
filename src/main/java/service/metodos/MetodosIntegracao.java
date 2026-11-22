package service.metodos;

import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;

import service.funcoes.Funcao;
import model.geral.MatrizDouble;
import model.geral.Ponto;
import model.integracao.retornos.RetornoTrapezio;
import model.integracao.subintervalos.SubintervaloTrapezio;
import model.sistemasLineares.RetornoEliminacaoGauss;
import model.integracao.retornos.RetornoCasoDiscreto;
import model.integracao.retornos.RetornoSimpson;
import model.integracao.subintervalos.SubintervaloSimpson;

public class MetodosIntegracao {
    public RetornoTrapezio trapezioRepetido(double a, double b, int nSubintervalos, Funcao f) throws ResolucaoException{
        if(a>=b){
            throw new ResolucaoException("O intervalo dado não é válido");
        }
        if(nSubintervalos < 0){
            throw new ResolucaoException("O número de subintervalos deve ser positivo");
        }
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

    public RetornoSimpson simpsonRepetido(double a, double b, int nSubintervalos, Funcao f) throws ResolucaoException{
        if(a>=b){
            throw new ResolucaoException("O intervalo dado não é válido");
        }
        if(nSubintervalos < 0){
            throw new ResolucaoException("O número de subintervalos deve ser positivo");
        }
        if(nSubintervalos%2 !=0){
            throw new ResolucaoException("O número de subintervalos deve ser par");
        }
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

    public RetornoCasoDiscreto casoDiscreto(List<Ponto> pontos, int grauPolinomio) throws ResolucaoException{
        if(grauPolinomio < 0){
            throw new ResolucaoException("O grau do polinômio deve ser positivo");
        }
        validarPontos(pontos);

        String formato = "0.";
        int numCasasDecimais = 6;
        for(int i=0; i<numCasasDecimais;i++){
            formato+="#";
        }
        DecimalFormat df = new DecimalFormat(formato);

        int altura = grauPolinomio+1;
        int largura = grauPolinomio+2;
        MatrizDouble matrizB = new MatrizDouble(largura, altura);
        MatrizDouble matrizBOriginal;
        MetodosSistemas metodosSistemas = new MetodosSistemas();

        for(int i=0;i<altura;i++){
            for(int j=0;j<largura;j++){
                double coeficienteAtual = 0;
                for(int k=0;k<pontos.size();k++){
                    coeficienteAtual += Math.pow(pontos.get(k).getX(),i+j);
                }
                matrizB.set(coeficienteAtual, i, j);
            }
        }

        for(int i=0;i<altura;i++){
            double resultadoAtual = 0;
            for(int j=0;j<pontos.size();j++){
                resultadoAtual += pontos.get(j).getY()*Math.pow((pontos.get(j).getX()),i);
            }
            matrizB.set(resultadoAtual, i, largura-1);
        }

        matrizBOriginal=matrizB.criarCopia();

        RetornoEliminacaoGauss resolucaoSistema = metodosSistemas.eliminacaoGauss(matrizB);

        String polinomio = "";

        List<Double> solucaoAlfas = resolucaoSistema.getSolucao().getColuna(0);

        for(int i=0; i<solucaoAlfas.size();i++){
            polinomio += df.format(solucaoAlfas.get(i))+"*x^"+i+"+";
        }
        polinomio = polinomio.substring(0,polinomio.length()-1);
        
        return new RetornoCasoDiscreto(polinomio, matrizBOriginal, resolucaoSistema);
    }

    public void validarPontos(List<Ponto> pontos) throws ResolucaoException{
        for(int i=0;i<pontos.size();i++){
            for(int j=i+1;j<pontos.size();j++){
                if(pontos.get(i).getX()==pontos.get(j).getX()){
                    throw new ResolucaoException("As abscissas dos pontos devem ser diferentes entre si");
                }
            }
        }
    }
    
}
