package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class MenuPrincipalController {
    @FXML
    private BorderPane telaPrincipal;

    @FXML
    void initialize() {
    FXMLLoader fxmlFuncoes = new FXMLLoader(getClass().getResource("/view/funcoes-view.fxml"));
    Region noFuncoes = null;
        try{
            noFuncoes = fxmlFuncoes.load();
            noFuncoes.autosize();
        }
        catch(IOException e){
            System.out.println("Não foi possível carregar o arquivo fxml das funções");
        }

        telaPrincipal.setCenter(noFuncoes);
        
    }
}
