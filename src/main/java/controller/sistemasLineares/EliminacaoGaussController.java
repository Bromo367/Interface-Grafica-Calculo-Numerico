package controller.sistemasLineares;

import java.util.List;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.metodos.MetodosInterpolacao;

public class EliminacaoGaussController {

    @FXML
    private AnchorPane anchorEntradas;

    @FXML
    private GridPane gridEntradas;

    @FXML
    private HBox hboxLayout;

    @FXML
    private Label lblFeedback;

    @FXML
    private ScrollPane scrollLayout;

    @FXML
    private TextField txtFieldQtdPontos;

    @FXML
    private VBox vboxConteudo;


    private MetodosInterpolacao metodosInterpolacao = new MetodosInterpolacao();

    private List<List<TextField>> entradasCoeficientes = new ArrayList<>();
    private List<TextField> entradasResultados = new ArrayList<>();

    private VerificadorEntradas verificador = new VerificadorEntradas();

    private int ordemMatriz;
    private int ordemMatrizAntiga=0;

    private int larguraAnchor = 480;
    private int alturaAnchor = 360;

    private int larguraTextFieldCoeficientes = 30;

    @FXML
    void initialize() {

        scrollLayout.widthProperty().addListener((observable, antigo, novo)->{
            hboxLayout.setPrefWidth(novo.doubleValue());
        });

        txtFieldQtdPontos.textProperty().addListener((observable, antigo, novo)->{
            try{
                ordemMatriz=verificador.avaliarInt(novo, "O número de variáveis");
                if(ordemMatriz>0){
                    criarEntradas();
                    mostraMensagem(lblFeedback, "", TiposTexto.NEUTRO);
                }
                else{
                    mostraMensagem(lblFeedback, "O número de variáveis deve ser positivo", TiposTexto.FRACASSO);
                    ordemMatriz=0;
                }
            }
            catch(EntradaException ex){
                ordemMatriz=0;
                mostraMensagem(lblFeedback, ex.getMessage(), TiposTexto.FRACASSO);
            }
        });
    }

    void criarEntradas(){
        if(ordemMatriz>0){
            anchorEntradas.getChildren().clear();
            anchorEntradas.setPrefHeight(alturaAnchor);
            for(int i = 0; i<ordemMatriz;i++){
                entradasCoeficientes.add(new ArrayList<>());
                for(int j = 0;j<ordemMatriz;j++){
                    TextField entradaCoeficiente = new TextField();
                    entradaCoeficiente.setPrefWidth(larguraTextFieldCoeficientes);
                    entradaCoeficiente.setMaxWidth(larguraTextFieldCoeficientes);

                    entradaCoeficiente.setLayoutX((alturaAnchor*(i+1))/(ordemMatriz+1));
                    entradaCoeficiente.setLayoutY((alturaAnchor*(j+1))/(ordemMatriz+1));

                    entradasCoeficientes.get(i).add(entradaCoeficiente);
                    anchorEntradas.getChildren().add(entradaCoeficiente);
                }
            }
        }
        else{
            mostraMensagem(lblFeedback, "O número de variáveis deve ser positivo", TiposTexto.FRACASSO);
        }
    }

    @FXML
    void resolverSistema(ActionEvent event) {
        
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
