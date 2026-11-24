package controller.integracao;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.geral.MatrizDouble;
import model.geral.Ponto;
import model.integracao.retornos.RetornoCasoDiscreto;
import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.metodos.MetodosIntegracao;
import service.metodos.ResolucaoException;

import javafx.scene.control.Separator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class CasoDiscretoController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private GridPane gridEntradas;
    @FXML private HBox hboxLayout;
    @FXML private ScrollPane scrollLayout;
    @FXML private TextField txtFieldQtdPontos;
    @FXML private VBox vboxConteudo;
    @FXML private Label lblFeedback;
    @FXML private Label lblCopiar;
    @FXML private VBox vboxResultados;
    @FXML private TextField txtFieldGrauPolinomio;

    private MetodosIntegracao metodosIntegracao = new MetodosIntegracao();

    private List<TextField> entradasX = new ArrayList<>();
    private List<TextField> entradasY = new ArrayList<>();

    private VerificadorEntradas verificador = new VerificadorEntradas();

    private String polinomio="";

    private int qtdPontos;
    private int qtdPontosAntiga=0;

    private int larguraAnchor = 480;
    private int alturaAnchor = 400;

    private int numeroCasasDecimais = 6;
    private DecimalFormat df;

    @FXML
    void initialize() {    
        String formato = "0.";
        for(int i=0;i<numeroCasasDecimais;i++){
            formato+="#";
        }
        df = new DecimalFormat(formato);

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
            gridEntradas.getChildren().remove((qtdPontosAntiga-i)*2+5);
            gridEntradas.getChildren().remove((qtdPontosAntiga-i)*2+4);
        }

        int qtdAntigaLinhas = gridEntradas.getRowCount();
        for(int i=0;i<qtdPontos-qtdAntigaLinhas+4;i++){
            RowConstraints linha = new RowConstraints();
            linha.setPrefHeight(tamanhoLinha);
            linha.setMinHeight(tamanhoLinha);
            gridEntradas.getRowConstraints().add(linha);
        }
        for(int i=0;i<diferencaQtdPontos;i++){
            entradasX.add(new TextField());
            entradasY.add(new TextField());
            gridEntradas.add(entradasX.get(i+qtdPontosAntiga),0,(qtdPontosAntiga+i)+4);
            gridEntradas.add(entradasY.get(i+qtdPontosAntiga),1,(qtdPontosAntiga+i)+4);
        }
        qtdPontosAntiga=qtdPontos;
    }

    @FXML
    void calcularPolinomio(ActionEvent event) {
        if(qtdPontos>0){
            try{
                vboxResultados.getChildren().clear();
                int grauPolinomio = verificador.avaliarInt(txtFieldGrauPolinomio.getText(), "O grau do polinômio");
                List<Ponto> pontos = new ArrayList<>();
                for(int i=0;i<qtdPontos;i++){
                    double x = verificador.avaliarDouble(entradasX.get(i).getText().trim(), "X"+i);
                    double y = verificador.avaliarDouble(entradasY.get(i).getText().trim(), "Y"+i);
                    pontos.add(new Ponto(x,y));
                }
                RetornoCasoDiscreto resultado = metodosIntegracao.casoDiscreto(pontos, grauPolinomio);

                polinomio = resultado.getPolinomio();

                Label lblB = new Label();
                lblB.setText("Matriz B: ");

                vboxResultados.getChildren().add(lblB);
                vboxResultados.getChildren().add(matrizParaAnchor(resultado.getMatrizB()));
                vboxResultados.getChildren().add(new Separator());

                List<MatrizDouble> iteracoes = resultado.getResolucaoSistema().getIteracoes();
                for(int i=0;i<iteracoes.size();i++){
                    Label lblK = new Label();
                    lblK.setText("K = "+(i+1));
                    lblK.setStyle(null);
                    vboxResultados.getChildren().add(lblK);
                    vboxResultados.getChildren().add(matrizParaAnchor(iteracoes.get(i)));
                    vboxResultados.getChildren().add(new Separator());
                }
                Label lblSolucao = new Label();
                lblSolucao.setText("Matriz solução:");
                vboxResultados.getChildren().add(lblSolucao);
                vboxResultados.getChildren().add(matrizParaAnchor(resultado.getResolucaoSistema().getSolucao()));
                vboxResultados.getChildren().add(new Separator());
                
                Label lblPolinomio = new Label();
                String strPolinomio = "p(x) = " + polinomio;
                lblPolinomio.setText(strPolinomio);

                vboxResultados.getChildren().add(lblPolinomio);

                mostraMensagem(lblFeedback, "Polinômio calculado!", TiposTexto.SUCESSO);
            }
            catch(EntradaException ex){
                mostraMensagem(lblFeedback, ex.getMessage(), TiposTexto.FRACASSO);
            }
            catch(ResolucaoException ex){
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

    AnchorPane matrizParaAnchor(MatrizDouble matriz){

        AnchorPane anchorMatriz = new AnchorPane();
        anchorMatriz.setPrefHeight(alturaAnchor);
        anchorMatriz.setMaxHeight(alturaAnchor);
        anchorMatriz.setPrefWidth(larguraAnchor);
        anchorMatriz.setMaxWidth(larguraAnchor);

        int alturaMatriz = matriz.getAltura();
        int larguraMatriz = matriz.getLargura();
        for(int i=0;i<alturaMatriz;i++){
            for(int j=0;j<larguraMatriz;j++){
                Label lblValorMatriz = new Label();
                lblValorMatriz.setText(df.format(matriz.get(i,j)));
                lblValorMatriz.setLayoutX((larguraAnchor*(j+1))/(larguraMatriz+1));
                lblValorMatriz.setLayoutY((alturaAnchor*(i+1))/(alturaMatriz+1));
                anchorMatriz.getChildren().add(lblValorMatriz);
            }
        }


        return anchorMatriz;
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
