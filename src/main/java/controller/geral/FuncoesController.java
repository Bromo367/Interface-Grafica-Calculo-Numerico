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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.funcoes.Funcao;

public class FuncoesController {

    @FXML private Label lblFeedback;
    @FXML private BorderPane borderFuncoes;
    @FXML private TextField txtFieldFuncao;
    @FXML private Button btnDerivar;
    @FXML private Button btnEnviar;
    @FXML private ColorPicker colorPickerFuncao;

    private PlanoCartesianoController planoController=null;

    private VerificadorEntradas verificador = new VerificadorEntradas();

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

        borderFuncoes.addEventHandler(KeyEvent.KEY_PRESSED, e ->{
            if(e.getCode()==KeyCode.ENTER){
                enviarFuncao(null);
            }
        });
    }

    void mudarCor() {
        planoController.mudarCor(colorPickerFuncao.getValue());
    }

    @FXML
    void enviarFuncao(ActionEvent event) {
        mudarCor();

        try{
            Funcao funcao = verificador.avaliarFuncao(txtFieldFuncao.getText().trim(), "f(x)");
            planoController.adicionarFuncao(funcao);
            mostraMensagem(lblFeedback, "Função calculada!", TiposTexto.SUCESSO);
        }
        catch(EntradaException ex){
            mostraMensagem(lblFeedback, ex.getMessage(), TiposTexto.FRACASSO);
        }
    }

    @FXML
    void derivarFuncao(ActionEvent event) {
        txtFieldFuncao.setText(planoController.derivarFuncao());
    }

    private void mostraMensagem(Label label, String msg, TiposTexto tipo) {
        if (label == null) {
            return;
        }

        label.setText(msg);

        if(tipo==TiposTexto.SUCESSO){
            label.setStyle("-fx-text-fill: #18733b;");
        }
        else if(tipo==TiposTexto.FRACASSO){
            label.setStyle("-fx-text-fill: #E53935;");
        }
        else if(tipo==TiposTexto.NEUTRO){
            label.setStyle("-fx-text-fill: #000000;");
        }
    }
}
