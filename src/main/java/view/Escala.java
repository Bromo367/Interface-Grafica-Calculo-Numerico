package view;

import javafx.scene.Group;

public abstract class Escala{
    public abstract void criar(int quantidadeDivisoes,double diferencaEixoInterface,double diferencaNumerica, double w, double h);
    public abstract Group getGrupo();
}