package controller.integracao;

import java.text.DecimalFormat;

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
import service.metodos.ResolucaoException;
import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.funcoes.Funcao;
import model.integracao.retornos.RetornoSimpson;
import model.integracao.subintervalos.SubintervaloSimpson;

public class SimpsonRepetidoController {

    
    @FXML private TableView<SubintervaloSimpson> tableSubintervalos;
    @FXML private TableColumn<SubintervaloSimpson, String> colunaXAtual;
    @FXML private TableColumn<SubintervaloSimpson, String> colunaXAnterior;
    @FXML private TableColumn<SubintervaloSimpson, String> colunaFXAtual;
    @FXML private TableColumn<SubintervaloSimpson, String> colunaXProximo;
    @FXML private TableColumn<SubintervaloSimpson, String> colunaArea;
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

    private DecimalFormat df;
    private int numCasasDecimais=4;

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

        colunaK.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,Integer>("K"));
        colunaXAtual.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,String>("strXAtual"));
        colunaXAnterior.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,String>("strXAnterior"));
        colunaFXAtual.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,String>("strFxAtual"));
        colunaXProximo.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,String>("strXProximo"));
        colunaArea.setCellValueFactory(new PropertyValueFactory<SubintervaloSimpson,String>("strArea"));
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
                lblResultado.setText("Área total aproximada: "+df.format(areaTotal));
            }
            else{
                mostraMensagem(lblFeedback, "O número de subintervalos deve ser par e positivo", TiposTexto.FRACASSO);
            }
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
