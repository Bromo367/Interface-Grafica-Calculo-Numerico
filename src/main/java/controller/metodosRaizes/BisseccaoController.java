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

import service.metodos.ResolucaoException;
import service.metodos.MetodosRaizes;
import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.funcoes.Funcao;

import model.metodosRaizes.iteracoes.IteracaoBisseccao;
import model.metodosRaizes.Raiz;
import model.metodosRaizes.retornos.RetornoBisseccao;


public class BisseccaoController {

    @FXML private TableView<IteracaoBisseccao> tableIteracoes;
    @FXML private TableColumn<IteracaoBisseccao, String> colunaXn;
    @FXML private TableColumn<IteracaoBisseccao, String> colunaA;
    @FXML private TableColumn<IteracaoBisseccao, String> colunaB;
    @FXML private TableColumn<IteracaoBisseccao, String> colunaDiferencaIntervalo;
    @FXML private TableColumn<IteracaoBisseccao, String> colunaFxn;
    @FXML private TableColumn<IteracaoBisseccao, Integer> colunaK;
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
    private ObservableList<IteracaoBisseccao> iteracoes;
    private Raiz raiz;
    private double tamanhoCelula=30;

    @FXML
    void initialize() {
        scrollLayout.widthProperty().addListener((observable, antigo, novo)->{
            hboxLayout.setPrefWidth(novo.doubleValue());
        });

        colunaK.setCellValueFactory(new PropertyValueFactory<IteracaoBisseccao,Integer>("k"));
        colunaA.setCellValueFactory(new PropertyValueFactory<IteracaoBisseccao,String>("strA"));
        colunaB.setCellValueFactory(new PropertyValueFactory<IteracaoBisseccao,String>("strB"));
        colunaXn.setCellValueFactory(new PropertyValueFactory<IteracaoBisseccao,String>("strXn"));
        colunaFxn.setCellValueFactory(new PropertyValueFactory<IteracaoBisseccao,String>("strFxn"));
        colunaDiferencaIntervalo.setCellValueFactory(new PropertyValueFactory<IteracaoBisseccao,String>("strDiferencaIntervalo"));
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
            RetornoBisseccao resultado = metodosRaizes.bisseccao(a, b, precisao, it, fx);
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
        catch(ResolucaoException ex){
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
