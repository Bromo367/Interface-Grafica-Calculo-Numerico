package controller.geral;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
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
    
    @FXML
    private ColorPicker colorPickerFuncao;

    PlanoCartesianoController planoController=null;

    @FXML
    void initialize() {
        
        FXMLLoader fxmlPlano = new FXMLLoader(getClass().getResource("/view/geral/planoCartesiano-view.fxml"));
        Region noPlano=null;
        try{
            Rectangle retangulo = new Rectangle(400,400);
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

        colorPickerFuncao.setValue(Color.RED);

        colorPickerFuncao.valueProperty().addListener((observable, antigo, novo)->{
            mudarCor();
        });
    }

    void mudarCor() {
        planoController.mudarCor(colorPickerFuncao.getValue());
    }

    @FXML
    void enviarFuncao(ActionEvent event) {
        mudarCor();
        planoController.adicionarFuncao(inputFuncao.getText().trim());
    }

    @FXML
    void derivarFuncao(ActionEvent event) {
        inputFuncao.setText(planoController.derivarFuncao());
    }
}
