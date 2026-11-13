package controller.geral;

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
    private Region noMIL = null;
    private Region noNewton = null;
    private Region noSecante = null;
    private Region noRegulaFalsi = null;

    @FXML
    void initialize() {

        FXMLLoader fxmlFuncoes = new FXMLLoader(getClass().getResource("/view/geral/funcoes-view.fxml"));
        FXMLLoader fxmlBisseccao = new FXMLLoader(getClass().getResource("/view/metodosRaizes/bisseccao-view.fxml"));
        FXMLLoader fxmlMIL = new FXMLLoader(getClass().getResource("/view/metodosRaizes/mil-view.fxml"));
        FXMLLoader fxmlNewtonRaphson = new FXMLLoader(getClass().getResource("/view/metodosRaizes/newtonRaphson-view.fxml"));
        FXMLLoader fxmlSecante = new FXMLLoader(getClass().getResource("/view/metodosRaizes/secante-view.fxml"));
        FXMLLoader fxmlRegulaFalsi = new FXMLLoader(getClass().getResource("/view/metodosRaizes/regulaFalsi-view.fxml"));
        try{
            noFuncoes = fxmlFuncoes.load();
            noBisseccao = fxmlBisseccao.load();
            noMIL = fxmlMIL.load();
            noNewton = fxmlNewtonRaphson.load();
            noSecante = fxmlSecante.load();
            noRegulaFalsi = fxmlRegulaFalsi.load();
        }
        catch(IOException e){
            System.out.println("Não foi possível carregar o arquivo fxml");
        }
        telaPrincipal.setCenter(noFuncoes);
        
    }

    @FXML
    void abrirPlanoCartesiano(ActionEvent event) {
        telaPrincipal.setCenter(noFuncoes);
    }

    @FXML
    void abrirBisseccao(ActionEvent event) {
        telaPrincipal.setCenter(noBisseccao);
    }

    @FXML
    void abrirMIL(ActionEvent event) {
        telaPrincipal.setCenter(noMIL);
    }

    @FXML
    void abrirNewton(ActionEvent event) {
        telaPrincipal.setCenter(noNewton);
    }

    @FXML
    void abrirSecante(ActionEvent event) {
        telaPrincipal.setCenter(noSecante);
    }

    @FXML
    void abrirRegulaFalsi(ActionEvent event) {
        telaPrincipal.setCenter(noRegulaFalsi);
    }
}
