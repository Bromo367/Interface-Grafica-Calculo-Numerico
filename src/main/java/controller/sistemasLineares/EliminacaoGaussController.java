package controller.sistemasLineares;

import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import model.geral.MatrizDouble;
import model.sistemasLineares.RetornoEliminacaoGauss;
import service.geral.EntradaException;
import service.geral.TiposTexto;
import service.geral.VerificadorEntradas;
import service.metodos.MetodosSistemas;
import service.metodos.ResolucaoException;

public class EliminacaoGaussController {

    @FXML private AnchorPane anchorEntradas;
    @FXML private GridPane gridEntradas;
    @FXML private HBox hboxLayout;
    @FXML private Label lblFeedback;
    @FXML private ScrollPane scrollLayout;
    @FXML private TextField txtFieldQtdPontos;
    @FXML private VBox vboxConteudo;
    @FXML private VBox vboxResultados;


    private MetodosSistemas metodosSistemas = new MetodosSistemas();

    private List<List<TextField>> entradasCoeficientes = new ArrayList<>();
    private List<TextField> entradasResultados = new ArrayList<>();

    private VerificadorEntradas verificador = new VerificadorEntradas();

    private int ordemMatriz;

    private int larguraAnchor = 480;
    private int alturaAnchor = 400;

    private int larguraEntradas = 40;
    private int alturaEntradas = 26;

    private int numeroCasasDecimais = 4;
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
                ordemMatriz=verificador.avaliarInt(novo, "O número de variáveis");
                if(ordemMatriz>0){
                    mostraMensagem(lblFeedback, "", TiposTexto.NEUTRO);
                    criarEntradas();
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
        if(ordemMatriz>5){
            mostraMensagem(lblFeedback, "Ordem da matriz limitada a 5", TiposTexto.NEUTRO);
            ordemMatriz = 5;
        }
        if(ordemMatriz>0){
            anchorEntradas.getChildren().clear();
            for(List<TextField> listas:entradasCoeficientes){
                listas.clear();
            }
            entradasResultados.clear();
            anchorEntradas.setPrefHeight(alturaAnchor);
            for(int i = 0; i<ordemMatriz;i++){
                entradasCoeficientes.add(new ArrayList<>());
                for(int j = 0;j<ordemMatriz;j++){
                    TextField entradaCoeficiente = new TextField();
                    entradaCoeficiente.setPrefWidth(larguraEntradas);
                    entradaCoeficiente.setMaxWidth(larguraEntradas);
                    entradaCoeficiente.setPrefHeight(alturaEntradas);

                    Label textoX = new Label();
                    
                    if(i!=ordemMatriz-1){
                        textoX.setText("X"+i+"+");
                    }
                    else{
                        textoX.setText("X"+i);
                    }
                    
                    double tamanhoTexto = textoX.getFont().getSize();

                    entradaCoeficiente.setLayoutX(((alturaAnchor*(i+1))/(ordemMatriz+1))-larguraEntradas/2);
                    entradaCoeficiente.setLayoutY((alturaAnchor*(j+1))/(ordemMatriz+1)-alturaEntradas/2);

                    textoX.setLayoutX(((alturaAnchor*(i+1))/(ordemMatriz+1))+larguraEntradas/2+3);
                    textoX.setLayoutY((alturaAnchor*(j+1))/(ordemMatriz+1)-tamanhoTexto/2);

                    entradasCoeficientes.get(i).add(entradaCoeficiente);
                    anchorEntradas.getChildren().add(entradaCoeficiente);
                    anchorEntradas.getChildren().add(textoX);
                }
            }
            for(int i=0;i<ordemMatriz;i++){
                Label lblIgual = new Label();
                lblIgual.setText("=");
                double tamanhoTexto = lblIgual.getFont().getSize();

                TextField entradaResultado = new TextField();
                entradaResultado.setPrefWidth(larguraEntradas);
                entradaResultado.setMaxWidth(larguraEntradas);
                entradaResultado.setPrefHeight(alturaEntradas);

                lblIgual.setLayoutX(((larguraAnchor-alturaAnchor)/3)+alturaAnchor-8);
                lblIgual.setLayoutY((alturaAnchor*(i+1))/(ordemMatriz+1)-tamanhoTexto/2);

                entradaResultado.setLayoutX((((larguraAnchor-alturaAnchor)*2)/3)+alturaAnchor-8);
                entradaResultado.setLayoutY((alturaAnchor*(i+1))/(ordemMatriz+1)-alturaEntradas/2);

                anchorEntradas.getChildren().add(lblIgual);
                anchorEntradas.getChildren().add(entradaResultado);

                entradasResultados.add(entradaResultado);
            }
        }
        else{
            mostraMensagem(lblFeedback, "O número de variáveis deve ser positivo", TiposTexto.FRACASSO);
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

    @FXML
    void resolverSistema(ActionEvent event) {
        try{
            verificador.avaliarInt(txtFieldQtdPontos.getText(), "A quantidade de variáveis");
            vboxResultados.getChildren().clear();
            MatrizDouble matrizB = new MatrizDouble(ordemMatriz+1, ordemMatriz);
            for(int i=0;i<ordemMatriz;i++){
                for(int j=0;j<ordemMatriz;j++){
                    matrizB.set(verificador.avaliarDouble(entradasCoeficientes.get(j).get(i).getText(), "Coeficiente "+i+" "+j), i, j);
                }
            }
            for(int i=0;i<ordemMatriz;i++){
                matrizB.set(verificador.avaliarDouble(entradasResultados.get(i).getText(), "Resultado "+i), i, ordemMatriz);
            }
            RetornoEliminacaoGauss resultado = metodosSistemas.eliminacaoGauss(matrizB);

            List<MatrizDouble> iteracoes = resultado.getIteracoes();
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
            vboxResultados.getChildren().add(matrizParaAnchor(resultado.getSolucao()));

            mostraMensagem(lblFeedback, "Sistema resolvido!", TiposTexto.SUCESSO);
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
