package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.control.Label;

public class EscalaEixoY extends Escala{
    private Group grupo = new Group();
    private ArrayList<Label>labels = new ArrayList<>();

    public void criar(int quantidadeDivisoes,double diferencaEixoInterface, double diferencaNumerica, double w, double h){
        grupo.getChildren().clear();
        labels.clear();
        for(int i=0;i<quantidadeDivisoes;i++){
            Label lbl = new Label();
            lbl.setTextFill(Paint.valueOf("4e4e4e"));
            lbl.setText(""+(i-quantidadeDivisoes/2)*diferencaNumerica);
            lbl.setLayoutX(w/2+5);
            lbl.setLayoutY(h/2+((i-quantidadeDivisoes/2)*diferencaEixoInterface));
            labels.add(lbl);
            grupo.getChildren().add(lbl);
        }
    }

    public Group getGrupo(){
        return grupo;
    }
}
