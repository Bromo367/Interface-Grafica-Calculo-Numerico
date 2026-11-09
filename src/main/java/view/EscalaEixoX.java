package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.control.Label;

public class EscalaEixoX extends Escala{
    private Group grupo = new Group();
    private ArrayList<Label>labels = new ArrayList<>();

    public void criar(int quantidadeDivisoes,double diferencaEixoInterface,double diferencaNumerica, double w, double h){
        grupo.getChildren().clear();
        labels.clear();
        for(int i=0;i<quantidadeDivisoes;i++){
            Label lbl = new Label();
            lbl.setTextFill(Paint.valueOf("4e4e4e"));
            if(i==quantidadeDivisoes/2){
                lbl.setText("");
            }
            else{
                lbl.setText(""+((i-quantidadeDivisoes/2)*diferencaNumerica));
            }
            lbl.setLayoutX((i-quantidadeDivisoes/2)*diferencaEixoInterface+w/2);
            lbl.setLayoutY(h/2);
            labels.add(lbl);
            grupo.getChildren().add(lbl);
        }
    }

    public Group getGrupo(){
        return grupo;
    }

    public void resize(int quantidadeDivisoes,double diferencaEixoInterface, double w, double h){
        for(int i=0;i<quantidadeDivisoes;i++){
            labels.get(i).setLayoutX((i-quantidadeDivisoes/2)*diferencaEixoInterface+w/2);
            labels.get(i).setLayoutY(h/2);
        }
    }

}
