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

import model.metodosRaizes.iteracoes.IteracaoSecante;
import model.metodosRaizes.Raiz;
import model.metodosRaizes.retornos.RetornoSecante;

import java.util.List;

public class SecanteController {

    
    @FXML private TableView<IteracaoSecante> tableIteracoes;
    @FXML
    private TableColumn<IteracaoSecante, Double> colunaXAtual;

    @FXML
    private TableColumn<IteracaoSecante, Double> colunaFxn;

    @FXML
    private TableColumn<IteracaoSecante, Double> colunaDerivadaXn;

    @FXML
    private TableColumn<IteracaoSecante, Double> colunaDiferenca;

    @FXML
    private TableColumn<IteracaoSecante, Double> colunaXAnterior;

    @FXML
    private TableColumn<IteracaoSecante, Integer> colunaK;

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
    private ObservableList<IteracaoSecante> iteracoes;
    private Raiz raiz;
    private double tamanhoCelula=30;

    @FXML
    void initialize() {
        scrollLayout.widthProperty().addListener((observable, antigo, novo)->{
            hboxLayout.setPrefWidth(novo.doubleValue());
        });

        colunaK.setCellValueFactory(new PropertyValueFactory<IteracaoSecante,Integer>("k"));
        colunaXAtual.setCellValueFactory(new PropertyValueFactory<IteracaoSecante,Double>("xAtual"));
        colunaXAnterior.setCellValueFactory(new PropertyValueFactory<IteracaoSecante,Double>("xAnterior"));
        colunaDerivadaXn.setCellValueFactory(new PropertyValueFactory<IteracaoSecante,Double>("derivadaXn"));
        colunaFxn.setCellValueFactory(new PropertyValueFactory<IteracaoSecante,Double>("fxn"));
        colunaDiferenca.setCellValueFactory(new PropertyValueFactory<IteracaoSecante,Double>("diferenca"));
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
            // RetornoSecante resultado = metodosRaizes.Secante(a, b, precisao, it, fx);
            RetornoSecante resultado = null;
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
