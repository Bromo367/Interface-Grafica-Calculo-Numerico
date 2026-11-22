package controller.integracao;

import java.lang.module.ResolutionException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import service.metodos.MetodosIntegracao;
import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.funcoes.Funcao;
import model.integracao.retornos.RetornoSimpson;
import model.integracao.subintervalos.SubintervaloSimpson;

public class SimpsonRepetidoController {

    
    @FXML private TableView<SubintervaloSimpson> tableSubintervalos;
    @FXML private TableColumn<SubintervaloSimpson, Double> colunaXAtual;
    @FXML private TableColumn<SubintervaloSimpson, Double> colunaXAnterior;
    @FXML private TableColumn<SubintervaloSimpson, Double> colunaFXAtual;
    @FXML private TableColumn<SubintervaloSimpson, Double> colunaXProximo;
    @FXML private TableColumn<SubintervaloSimpson, Double> colunaArea;
    @FXML private TableColumn<SubintervaloSimpson, Integer> colunaK;
    @FXML private HBox hboxLayout;
    @FXML private Label lblFeedback;
    @FXML private Label lblResultado;
    @FXML private ScrollPane scrollLayout;
    @FXML private TextField txtFieldFuncao;
    @FXML private TextField txtFieldSubintervalos;
    @FXML private TextField txtFieldLimiteInferior;
    @FXML private TextField txtFieldLimiteSuperior;
    @FXML private VBox vboxConteudo;

    private MetodosIntegracao metodosIntegracao = new MetodosIntegracao();
    private VerificadorEntradas verificador = new VerificadorEntradas();
    private ObservableList<SubintervaloSimpson> subintervalos;
    private double tamanhoCelula=30;

    @FXML
    void initialize() {
        scrollLayout.widthProperty().addListener((observable, antigo, novo)->{
            hboxLayout.setPrefWidth(novo.doubleValue());
        });

        colunaK.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,Integer>("k"));
        colunaXAtual.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,Double>("xAtual"));
        colunaXAnterior.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,Double>("xAnterior"));
        colunaFXAtual.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,Double>("fxAtual"));
        colunaXProximo.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,Double>("xProximo"));
        colunaArea.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,Double>("area"));
        tableSubintervalos.setFixedCellSize(tamanhoCelula);
    }

    @FXML
    void calcularIntegral(ActionEvent event) {
        Funcao fx = null;
        double a;
        double b;
        int nSubintervalos;
        try{
            fx = verificador.avaliarFuncao(txtFieldFuncao.getText(), "f(x)");
            a = verificador.avaliarDouble(txtFieldLimiteInferior.getText(), "O limite inferior");
            b = verificador.avaliarDouble(txtFieldLimiteSuperior.getText(), "O limite superior");
            nSubintervalos = verificador.avaliarInt(txtFieldSubintervalos.getText(), "O número máximo de iterações");
            if(nSubintervalos>0&&nSubintervalos%2==0){
                RetornoSimpson resultado = metodosIntegracao.simpsonRepetido(a, b, nSubintervalos, fx);
                subintervalos = FXCollections.observableList(resultado.getSubintervalos());
                Double areaTotal = resultado.getAreaTotal();
                mostraMensagem(lblFeedback, "Integral calculada", TiposTexto.SUCESSO);
                tableSubintervalos.setItems(subintervalos);
                tableSubintervalos.setPrefHeight((subintervalos.size()+1)*tamanhoCelula);
                lblResultado.setText("Área total aproximada: "+areaTotal);
            }
            else{
                mostraMensagem(lblFeedback, "O número de subintervalos deve ser par e positivo", TiposTexto.FRACASSO);
            }
        }
        catch(EntradaException ex){
            mostraMensagem(lblFeedback, ex.getMessage(), TiposTexto.FRACASSO);
        }
        catch(ResolutionException ex){
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
