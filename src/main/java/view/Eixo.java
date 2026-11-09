package view;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.control.Label;


public class Eixo {
    private Label nome;
    private Line linha;
    private Polygon ponta;

    private double x0;
    private double y0;
    private double x1;
    private double y1;

    private double tamanho;
    private double sen;
    private double cos;

    public Eixo(Label nome,Line linha,Polygon ponta,double x0,double y0,double x1,double y1){
        this.nome=nome;
        this.linha=linha;
        this.ponta=ponta;

        this.x0=x0;
        this.y0=y0;
        this.x1=x1;
        this.y1=y1;

        tamanho=Math.sqrt(Math.pow(x1-x0,2)+Math.pow(y1-y0,2));
        sen=(y1-y0)/tamanho;
        cos=(x1-x0)/tamanho;


        linha.setLayoutX(0);
        linha.setLayoutY(0);
     
        linha.setStartX(x0);
        linha.setStartY(y0);
        linha.setEndX(x1);
        linha.setEndY(y1);

        
        ponta.setLayoutX(0);
        ponta.setLayoutY(0);



        ponta.getPoints().setAll(x1,y1,
                            (x1-15*cos)-20*sen,(y1-15*sen)-20*cos,
                            (x1-15*cos)+20*sen,(y1-15*sen)+20*cos);

    }

    public void resizeX(double wAntigo,double wNovo){
        x0*=wNovo/wAntigo;
        x1*=wNovo/wAntigo;

        linha.setStartX(x0);
        linha.setEndX(x1);

        ponta.getPoints().setAll(x1,y1,
                            (x1-15*cos)-20*sen,(y1-15*sen)-20*cos,
                            (x1-15*cos)+20*sen,(y1-15*sen)+20*cos);

        nome.setLayoutX((x1-15*cos)+20*sen);

    }

    public void resizeY(double hAntigo,double hNovo){
        y0*=hNovo/hAntigo;
        y1*=hNovo/hAntigo;

        linha.setStartY(y0);
        linha.setEndY(y1);

        ponta.getPoints().setAll(x1,y1,
                            (x1-15*cos)-20*sen,(y1-15*sen)-20*cos,
                            (x1-15*cos)+20*sen,(y1-15*sen)+20*cos);

        nome.setLayoutY((y1-15*sen)+20*cos);
    }
}
