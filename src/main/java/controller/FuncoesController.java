package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class FuncoesController {


    @FXML
    private BorderPane borderFuncoes;

    @FXML
    private TextField inputFuncao;
    
    @FXML
    private Button btnDerivar;

    @FXML
    private Button btnEnviar;

    PlanoCartesianoController planoController=null;

    @FXML
    void initialize() {
        
        FXMLLoader fxmlPlano = new FXMLLoader(getClass().getResource("/view/planoCartesiano-view.fxml"));
        Region noPlano=null;
        try{
            Rectangle retangulo = new Rectangle(600,400);
            noPlano = fxmlPlano.load();
            retangulo.heightProperty().bind(noPlano.heightProperty());
            retangulo.widthProperty().bind(noPlano.widthProperty());
            noPlano.autosize();
            noPlano.setClip(retangulo);
        }
        catch(IOException e){
            System.out.println("Não foi possível carregar o arquivo fxml do plano cartesiano");
        }

        borderFuncoes.setCenter(noPlano);
        
        
        planoController = (PlanoCartesianoController) fxmlPlano.getController();


    }

    @FXML
    void enviarFuncao(ActionEvent event) {
        System.out.println(inputFuncao.getText().trim());
        planoController.adicionarFuncao(inputFuncao.getText().trim());
    }

    @FXML
    void derivarFuncao(ActionEvent event) {
        planoController.derivarFuncao();
    }
}
