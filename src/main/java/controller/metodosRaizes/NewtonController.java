package controller.metodosRaizes;

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
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import service.metodos.MetodosRaizes;
import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.funcoes.Funcao;

import model.metodosRaizes.iteracoes.IteracaoNewton;
import model.metodosRaizes.Raiz;
import model.metodosRaizes.retornos.RetornoNewton;

import java.util.List;

public class NewtonController {

    
    @FXML private TableView<IteracaoNewton> tableIteracoes;
    @FXML
    private TableColumn<IteracaoNewton, Double> colunaXn;

    @FXML
    private TableColumn<IteracaoNewton, Double> colunaA;

    @FXML
    private TableColumn<IteracaoNewton, Double> colunaB;

    @FXML
    private TableColumn<IteracaoNewton, Double> colunaDiferencaIntervalo;

    @FXML
    private TableColumn<IteracaoNewton, Double> colunaFxn;

    @FXML
    private TableColumn<IteracaoNewton, Integer> colunaK;

    @FXML private HBox hboxLayout;
    @FXML private Label lblFeedback;
    @FXML private Label lblRaiz;
    @FXML private ScrollPane scrollLayout;
    @FXML private TextField txtFieldFuncao;
    @FXML private TextField txtFieldIteracoes;
    @FXML private TextField txtFieldLimiteInferior;
    @FXML private TextField txtFieldLimiteSuperior;
    @FXML private TextField txtFieldPrecisao;
    @FXML private VBox vboxConteudo;

    private MetodosRaizes metodosRaizes = new MetodosRaizes();
    private VerificadorEntradas verificador = new VerificadorEntradas();
    private ObservableList<IteracaoNewton> iteracoes;
    private Raiz raiz;
    private double tamanhoCelula=30;

    @FXML
    void initialize() {
        scrollLayout.widthProperty().addListener((observable, antigo, novo)->{
            hboxLayout.setPrefWidth(novo.doubleValue());
        });

        colunaK.setCellValueFactory(new PropertyValueFactory<IteracaoNewton,Integer>("k"));
        colunaA.setCellValueFactory(new PropertyValueFactory<IteracaoNewton,Double>("a"));
        colunaB.setCellValueFactory(new PropertyValueFactory<IteracaoNewton,Double>("b"));
        colunaXn.setCellValueFactory(new PropertyValueFactory<IteracaoNewton,Double>("xn"));
        colunaFxn.setCellValueFactory(new PropertyValueFactory<IteracaoNewton,Double>("fxn"));
        colunaDiferencaIntervalo.setCellValueFactory(new PropertyValueFactory<IteracaoNewton,Double>("diferencaIntervalo"));
        tableIteracoes.setFixedCellSize(tamanhoCelula);
    }

    @FXML
    void calcularRaiz(ActionEvent event) {
        Funcao fx = null;
        double a;
        double b;
        double precisao;
        int it;
        try{
            fx = verificador.avaliarFuncao(txtFieldFuncao.getText(), "f(x)");
            a = verificador.avaliarDouble(txtFieldLimiteInferior.getText(), "O limite inferior");
            b = verificador.avaliarDouble(txtFieldLimiteSuperior.getText(), "O limite superior");
            precisao = verificador.avaliarDouble(txtFieldPrecisao.getText(), "A precisão");
            it = verificador.avaliarInt(txtFieldIteracoes.getText(), "O número máximo de iterações");
            // RetornoNewton resultado = metodosRaizes.newton(a, b, precisao, it, fx);
            RetornoNewton resultado = null;
            iteracoes = FXCollections.observableList(resultado.getIteracoes());
            raiz = resultado.getRaiz();
            mostraMensagem(lblFeedback, "Raiz calculada", TiposTexto.SUCESSO);
            tableIteracoes.setItems(iteracoes);
            tableIteracoes.setPrefHeight((iteracoes.size()+1)*tamanhoCelula);
            lblRaiz.setText(raiz.toString());
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
