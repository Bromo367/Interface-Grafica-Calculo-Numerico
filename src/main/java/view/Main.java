package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/geral/menuPrincipal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Plano Cartesiano");
        stage.setMinHeight(120);
        stage.setMinWidth(120);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}