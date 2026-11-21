package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.geral.MatrizDouble;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/geral/menuPrincipal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Métodos Cálculo Numérico");
        stage.setMinHeight(120);
        stage.setMinWidth(120);
        stage.setScene(scene);
        stage.show();


        MatrizDouble matriz1 = new MatrizDouble(1,1);
        matriz1.set(3.0,0,0);
        System.out.println("Det matriz 1 = "+matriz1.determinante());

        MatrizDouble matriz2 = new MatrizDouble(2,2);
        matriz2.set(3.0,0,0);
        matriz2.set(5.0,0,1);
        matriz2.set(7.0,1,0);
        matriz2.set(8.0,1,1);
        System.out.println("Det matriz 2 = "+matriz2.determinante());

        MatrizDouble matriz3 = new MatrizDouble(3,3);
        matriz3.set(1.0,0,0);
        matriz3.set(2.0,0,1);
        matriz3.set(3.0,0,2);
        matriz3.set(2.0,1,0);
        matriz3.set(4.0,1,1);
        matriz3.set(7.0,1,2);
        matriz3.set(3.0,2,0);
        matriz3.set(6.0,2,1);
        matriz3.set(11.0,2,2);
        System.out.println("Det matriz 3 = "+matriz3.determinante());

        MatrizDouble matriz4 = new MatrizDouble(4,4);
        matriz4.set(4.0,0,0);
        matriz4.set(5.0,0,1);
        matriz4.set(-3.0,0,2);
        matriz4.set(0.0,0,3);
        matriz4.set(2.0,1,0);
        matriz4.set(-1.0,1,1);
        matriz4.set(3.0,1,2);
        matriz4.set(1.0,1,3);
        matriz4.set(1.0,2,0);
        matriz4.set(-3.0,2,1);
        matriz4.set(2.0,2,2);
        matriz4.set(1.0,2,3);
        matriz4.set(0.0,3,0);
        matriz4.set(2.0,3,1);
        matriz4.set(-2.0,3,2);
        matriz4.set(5.0,3,3);

        System.out.println("Det matriz 4 = "+matriz4.determinante());
    }

    public static void main(String[] args) {
        launch(args);
    }
}