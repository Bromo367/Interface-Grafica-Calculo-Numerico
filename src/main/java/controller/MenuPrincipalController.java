package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

public class MenuPrincipalController {
    @FXML
    private BorderPane telaPrincipal;
    private Region noFuncoes = null;
    private Region noBisseccao = null;

    @FXML
    void initialize() {

        FXMLLoader fxmlFuncoes = new FXMLLoader(getClass().getResource("/view/geral/funcoes-view.fxml"));
        FXMLLoader fxmlBisseccao = new FXMLLoader(getClass().getResource("/view/metodosRaizes/bisseccao-view.fxml"));
        try{
            noFuncoes = fxmlFuncoes.load();
            noFuncoes.autosize();
            noBisseccao = fxmlBisseccao.load();
            noBisseccao.autosize();
        }
        catch(IOException e){
            System.out.println("Não foi possível carregar o arquivo fxml das funções");
        }
        telaPrincipal.setCenter(noFuncoes);
        
    }

    @FXML
    void abrirBisseccao(ActionEvent event) {
        telaPrincipal.setCenter(noBisseccao);
    }
}
