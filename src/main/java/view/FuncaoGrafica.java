package view;

import javafx.scene.shape.Path;
import service.funcoes.Funcao;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.paint.Paint;

public class FuncaoGrafica{
    private Funcao funcao = new Funcao("x^2");
    private Path linhaGrafico = new Path();

    public FuncaoGrafica(){
        linhaGrafico.setStroke(Paint.valueOf("ff0000"));
        linhaGrafico.setStrokeWidth(3);

    }

    public void criar(double escalaNumerica,double escalaInterface, double w, double h){
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
            if(xTeste==0){
                System.out.println("yTeste="+yTeste);
            }
            if(!Double.isFinite(yTeste)){
                System.out.println("Achei um nan parceiro");
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
}