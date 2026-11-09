package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class DivisoresEixoX extends Divisores{
    private Group grupo = new Group();
    private ArrayList<Line>linhasDivisores = new ArrayList<>();

    public void criar(int quantidadeDivisoes,double diferencaEixoInterface, double w, double h){
        grupo.getChildren().clear();
        linhasDivisores.clear();
        for(int i=0;i<quantidadeDivisoes;i++){
            Line ln = new Line();
            ln.setStroke(Paint.valueOf("dddddd"));
            ln.setStrokeWidth(3);
            ln.setLayoutX(0);
            ln.setLayoutY(0);
            ln.setStartY(0);
            ln.setEndY(h);
            ln.setStartX((i-quantidadeDivisoes/2)*diferencaEixoInterface+w/2);
            ln.setEndX((i-quantidadeDivisoes/2)*diferencaEixoInterface+w/2);
            linhasDivisores.add(ln);
            grupo.getChildren().add(ln);
        }
    }

    public void resize(int quantidadeDivisoes,double diferencaEixoInterface, double w, double h){
        for(int i=0;i<quantidadeDivisoes;i++){
            linhasDivisores.get(i).setStartY(0);
            linhasDivisores.get(i).setEndY(h);
            linhasDivisores.get(i).setStartX((i-quantidadeDivisoes/2)*diferencaEixoInterface+w/2);
            linhasDivisores.get(i).setEndX((i-quantidadeDivisoes/2)*diferencaEixoInterface+w/2);
        }
    }

    public Group getGrupo(){
        return grupo;
    }
}
