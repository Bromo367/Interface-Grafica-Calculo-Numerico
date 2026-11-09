package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class DivisoresEixoY extends Divisores{
    private Group grupo = new Group();
    private ArrayList<Line>linhasDivisores = new ArrayList<>();

    public void criar(int quantidadeDivisoes,double diferencaEixoInterface, double w, double h){
        grupo.getChildren().clear();
        linhasDivisores.clear();
        for(int i=0;i<quantidadeDivisoes;i++){
            Line ln = new Line();
            ln.setStroke(Paint.valueOf("dddddd"));
            ln.setStrokeWidth(3);
            ln.setStartY(h/2+(i-quantidadeDivisoes/2)*diferencaEixoInterface);
            ln.setEndY(h/2+(i-quantidadeDivisoes/2)*diferencaEixoInterface);
            ln.setStartX(0);
            ln.setEndX(w);

            ln.setLayoutX(0);
            ln.setLayoutY(0);
            linhasDivisores.add(ln);
            grupo.getChildren().add(ln);
        }
    }

    public Group getGrupo(){
        return grupo;
    }
}
