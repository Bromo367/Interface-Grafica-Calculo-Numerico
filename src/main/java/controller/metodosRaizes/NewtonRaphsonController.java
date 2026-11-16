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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import service.metodos.MetodosRaizes;
import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.funcoes.Funcao;

import model.metodosRaizes.iteracoes.IteracaoNewtonRaphson;
import model.metodosRaizes.Raiz;
import model.metodosRaizes.retornos.RetornoNewtonRaphson;


public class NewtonRaphsonController {

    
    @FXML private TableView<IteracaoNewtonRaphson> tableIteracoes;
    @FXML
    private TableColumn<IteracaoNewtonRaphson, Double> colunaXAtual;

    @FXML
    private TableColumn<IteracaoNewtonRaphson, Double> colunaFxn;

    @FXML
    private TableColumn<IteracaoNewtonRaphson, Double> colunaDerivadaXn;

    @FXML
    private TableColumn<IteracaoNewtonRaphson, Double> colunaDiferenca;

    @FXML
    private TableColumn<IteracaoNewtonRaphson, Double> colunaXAnterior;

    @FXML
    private TableColumn<IteracaoNewtonRaphson, Integer> colunaK;

    @FXML private HBox hboxLayout;
    @FXML private Label lblFeedback;
    @FXML private Label lblRaiz;
    @FXML private ScrollPane scrollLayout;
    @FXML
    private TextField txtFieldFuncao;

    @FXML
    private TextField txtFieldIteracoes;

    @FXML
    private TextField txtFieldPrecisao;

    @FXML
    private TextField txtFieldXInicial;

    @FXML private VBox vboxConteudo;

    private MetodosRaizes metodosRaizes = new MetodosRaizes();
    private VerificadorEntradas verificador = new VerificadorEntradas();
    private ObservableList<IteracaoNewtonRaphson> iteracoes;
    private Raiz raiz;
    private double tamanhoCelula=30;

    @FXML
    void initialize() {
        scrollLayout.widthProperty().addListener((observable, antigo, novo)->{
            hboxLayout.setPrefWidth(novo.doubleValue());
        });

        colunaK.setCellValueFactory(new PropertyValueFactory<IteracaoNewtonRaphson,Integer>("k"));
        colunaXAtual.setCellValueFactory(new PropertyValueFactory<IteracaoNewtonRaphson,Double>("xAtual"));
        colunaXAnterior.setCellValueFactory(new PropertyValueFactory<IteracaoNewtonRaphson,Double>("xAnterior"));
        colunaDerivadaXn.setCellValueFactory(new PropertyValueFactory<IteracaoNewtonRaphson,Double>("derivadaXn"));
        colunaFxn.setCellValueFactory(new PropertyValueFactory<IteracaoNewtonRaphson,Double>("fxn"));
        colunaDiferenca.setCellValueFactory(new PropertyValueFactory<IteracaoNewtonRaphson,Double>("diferenca"));
        tableIteracoes.setFixedCellSize(tamanhoCelula);
    }

    @FXML
    void calcularRaiz(ActionEvent event) {
        Funcao funcao = null;
        double x0;
        double precisao;
        int it;
        try{
            funcao = verificador.avaliarFuncao(txtFieldFuncao.getText(), "f(x)");
            x0 = verificador.avaliarDouble(txtFieldXInicial.getText(), "O chute inicial");
            precisao = verificador.avaliarDouble(txtFieldPrecisao.getText(), "A precisão");
            it = verificador.avaliarInt(txtFieldIteracoes.getText(), "O número máximo de iterações");
            RetornoNewtonRaphson resultado = metodosRaizes.newton(x0,precisao,it,funcao);
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
