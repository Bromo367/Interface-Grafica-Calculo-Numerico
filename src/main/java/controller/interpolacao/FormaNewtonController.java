package controller.interpolacao;

import java.lang.module.ResolutionException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.geral.Ponto;
import model.interpolacao.RetornoFormaNewton;
import model.interpolacao.RetornoLagrange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.metodos.MetodosInterpolacao;

public class FormaNewtonController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gridEntradas;

    @FXML
    private HBox hboxLayout;


    @FXML
    private ScrollPane scrollLayout;

    @FXML
    private TextField txtFieldQtdPontos;

    @FXML
    private VBox vboxConteudo;

    @FXML
    private Label lblFeedback;

    @FXML
    private Label lblCopiar;

    @FXML
    private Label lblResultado;

    private MetodosInterpolacao metodosInterpolacao = new MetodosInterpolacao();

    private List<TextField> entradasX = new ArrayList<>();
    private List<TextField> entradasY = new ArrayList<>();

    private VerificadorEntradas verificador = new VerificadorEntradas();

    private String polinomio="";

    private int qtdPontos;
    private int qtdPontosAntiga=0;

    @FXML
    void initialize() {

        scrollLayout.widthProperty().addListener((observable, antigo, novo)->{
            hboxLayout.setPrefWidth(novo.doubleValue());
        });

        txtFieldQtdPontos.textProperty().addListener((observable, antigo, novo)->{
            try{
                qtdPontos=verificador.avaliarInt(novo, "A quantidade de pontos");
                if(qtdPontos>0){
                    criarEntradasGrid();
                    mostraMensagem(lblFeedback, "", TiposTexto.NEUTRO);
                }
                else{
                    mostraMensagem(lblFeedback, "A quantidade de pontos deve ser positiva", TiposTexto.FRACASSO);
                    qtdPontos=0;
                }
            }
            catch(EntradaException ex){
                qtdPontos=0;
                mostraMensagem(lblFeedback, ex.getMessage(), TiposTexto.FRACASSO);
            }
        });
    }

    void criarEntradasGrid(){
        int tamanhoLinha = 35;
        int diferencaQtdPontos = qtdPontos-qtdPontosAntiga;
        
        for(int i=0; i<diferencaQtdPontos*-1;i++){
            entradasX.remove(qtdPontosAntiga-i-1);
            entradasY.remove(qtdPontosAntiga-i-1);
            gridEntradas.getChildren().remove((qtdPontosAntiga-i)*2+3);
            gridEntradas.getChildren().remove((qtdPontosAntiga-i)*2+2);
        }

        int qtdAntigaLinhas = gridEntradas.getRowCount();
        for(int i=0;i<qtdPontos-qtdAntigaLinhas+3;i++){
            RowConstraints linha = new RowConstraints();
            linha.setPrefHeight(tamanhoLinha);
            linha.setMinHeight(tamanhoLinha);
            gridEntradas.getRowConstraints().add(linha);
        }

        for(int i=0;i<diferencaQtdPontos;i++){
            entradasX.add(new TextField());
            entradasY.add(new TextField());
            gridEntradas.add(entradasX.get(i+qtdPontosAntiga),0,(qtdPontosAntiga+i)+3);
            gridEntradas.add(entradasY.get(i+qtdPontosAntiga),1,(qtdPontosAntiga+i)+3);
        }
        qtdPontosAntiga=qtdPontos;
    }

    @FXML
    void calcularPolinomio(ActionEvent event) {
        if(qtdPontos>0){
            try{
                List<Ponto> pontos = new ArrayList<>();
                for(int i=0;i<qtdPontos;i++){
                    double x = verificador.avaliarDouble(entradasX.get(i).getText().trim(), "X"+i);
                    double y = verificador.avaliarDouble(entradasY.get(i).getText().trim(), "Y"+i);
                    pontos.add(new Ponto(x,y));
                }
                RetornoFormaNewton resultado = metodosInterpolacao.formaNewton(pontos);
                String strResultado = "";
                for(int i=0; i<resultado.getDiferencasDivididas().size();i++){
                    strResultado += "d"+i+" = "+resultado.getDiferencasDivididas().get(i)+"\n";
                }
                strResultado += "p(x) = "+resultado.getPolinomio();
                lblResultado.setText(strResultado);

                polinomio = resultado.getPolinomio();
            }
            catch(EntradaException ex){
                mostraMensagem(lblFeedback, ex.getMessage(), TiposTexto.FRACASSO);
            }
            catch(ResolutionException ex){
                mostraMensagem(lblFeedback, ex.getMessage(), TiposTexto.FRACASSO);
            }
        }
        else{
            mostraMensagem(lblFeedback, "O número de pontos deve ser positivo", TiposTexto.FRACASSO);
        }
    }

    @FXML
    void copiarPolinomio(ActionEvent event) {
        if(polinomio.length()>0){
            Clipboard areaTransferencia = Clipboard.getSystemClipboard();
            ClipboardContent conteudo = new ClipboardContent();
            conteudo.putString(polinomio);
            areaTransferencia.setContent(conteudo);
            mostraMensagem(lblCopiar, "Polinômio copiado para área de transferência", TiposTexto.SUCESSO);
        }
        else{
            mostraMensagem(lblCopiar, "Calcule um polinômio primeiro", TiposTexto.FRACASSO);
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
