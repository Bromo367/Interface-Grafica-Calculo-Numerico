package controller.geral;

import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.funcoes.Funcao;


public class AplicarFuncoesController {


    @FXML private HBox hboxLayout;
    @FXML private Label lblFeedback;
    @FXML private Label lblResultado;
    @FXML private ScrollPane scrollLayout;
    @FXML private TextField txtFieldFuncao;
    @FXML private TextField txtFieldValorX;
    @FXML private VBox vboxConteudo;

    private VerificadorEntradas verificador = new VerificadorEntradas();
    
    private int numCasasDecimais = 6;
    private DecimalFormat df;

    @FXML
    void initialize() {
        String formato = "0.";
        for(int i=0; i<numCasasDecimais;i++){
            formato+="#";
        }
        df = new DecimalFormat(formato);


        scrollLayout.widthProperty().addListener((observable, antigo, novo)->{
            hboxLayout.setPrefWidth(novo.doubleValue());
        });
    }

    @FXML
    void calcularRaiz(ActionEvent event) {
        Funcao funcao = null;
        double x;
        try{
            funcao = verificador.avaliarFuncao(txtFieldFuncao.getText(), "f(x)");
            x = verificador.avaliarDouble(txtFieldValorX.getText(), "x");
            lblResultado.setText("f("+df.format(x)+") = "+df.format(funcao.aplicar(x)));
            mostraMensagem(lblFeedback, "Função aplicada!", TiposTexto.SUCESSO);
        }
        catch(EntradaException ex){
            mostraMensagem(lblFeedback, ex.getMessage(), TiposTexto.FRACASSO);
        }
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
