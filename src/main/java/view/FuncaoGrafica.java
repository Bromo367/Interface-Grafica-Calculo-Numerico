package view;

import javafx.scene.shape.Path;
import service.funcoes.Funcao;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public class FuncaoGrafica{
    private Funcao funcao = new Funcao("x^2");
    private Path linhaGrafico = new Path();
    private double escalaNumerica;
    private double escalaInterface;
    private double w;
    private double h;
    
    public FuncaoGrafica(){
        linhaGrafico.setStroke(Paint.valueOf("ff0000"));
        linhaGrafico.setStrokeWidth(3);
    }

    public void criar(double escalaNumerica,double escalaInterface, double w, double h){
        this.escalaNumerica=escalaNumerica;
        this.escalaInterface=escalaInterface;
        this.w=w;
        this.h=h;
        linhaGrafico.getElements().clear();
        double xGrafico=0;
        double xTeste=((xGrafico-w/2)*escalaNumerica)/escalaInterface;
        double yTeste=funcao.aplicar(xTeste);
        double yGrafico=(((-yTeste)*escalaInterface)/escalaNumerica)+h/2;
        linhaGrafico.getElements().add(new MoveTo(xGrafico,yGrafico));
        boolean ultimoTesteNAN = false;
        
        while(xGrafico<=w){
            xTeste=((xGrafico-w/2)*escalaNumerica)/escalaInterface;
            yTeste=funcao.aplicar(xTeste);
            if(!Double.isFinite(yTeste)){
                ultimoTesteNAN=true;
            }
            else{
                yGrafico=(((-yTeste)*escalaInterface)/escalaNumerica)+h/2;

                if(ultimoTesteNAN){
                    linhaGrafico.getElements().add(new MoveTo(xGrafico,yGrafico));
                }
                else{
                    linhaGrafico.getElements().add(new LineTo(xGrafico,yGrafico));
                }
                ultimoTesteNAN=false;
            }
            xGrafico++;
        }

    }

    public Path getLinha(){
        return linhaGrafico;
    }

    public void setFuncao(String novaFuncao){
        funcao = new Funcao(novaFuncao);
    }

    public void derivar(){
        funcao = funcao.derivar();
    }

    public void setCor(Color cor){
        linhaGrafico.setStroke(cor);
        criar(escalaNumerica, escalaInterface, w, h);
    }

    public String arvoreEmOrdem(){
        return funcao.arvoreEmOrdem();
    }
}