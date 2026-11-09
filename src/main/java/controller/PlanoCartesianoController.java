package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import view.DivisoresEixoX;
import view.DivisoresEixoY;
import view.Eixo;
import view.EscalaEixoX;
import view.EscalaEixoY;
import view.FuncaoGrafica;
import view.QuantidadeDivisores;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;

public class PlanoCartesianoController {

    private double h;
    private double w;

    private double diferencaEixoNumerico=1;
    private double diferencaEixoInterface;

    private Eixo eixoX;
    private Eixo eixoY;

    private DivisoresEixoX divisoresEixoX = new DivisoresEixoX();
    private DivisoresEixoY divisoresEixoY = new DivisoresEixoY();

    private QuantidadeDivisores qtdDivisores = new QuantidadeDivisores(11);

    private EscalaEixoX escalaX = new EscalaEixoX();
    private EscalaEixoY escalaY = new EscalaEixoY();

    private FuncaoGrafica funcao = new FuncaoGrafica();

    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anc;

    @FXML
    private Line lnEixoX;

    @FXML
    private Line lnEixoY;

    @FXML
    private Polygon setaX;

    @FXML
    private Polygon setaY;

    @FXML
    private Label lblX;

    @FXML
    private Label lblY;

    

    @FXML
    void initialize() {
        h=400;
        w=600;

        diferencaEixoInterface=w/(qtdDivisores.getEixoX()+1);
       
        qtdDivisores.recalcularY(w, h);

        eixoX = new Eixo(lblX, lnEixoX, setaX, 0, h/2, w, h/2);
        eixoY = new Eixo(lblY, lnEixoY, setaY, w/2, h, w/2, 0);

        divisoresEixoX.criar(qtdDivisores.getEixoX(), diferencaEixoInterface, w, h);
        divisoresEixoY.criar(qtdDivisores.getEixoY(), diferencaEixoInterface, w, h);

        escalaX.criar(qtdDivisores.getEixoX(), diferencaEixoInterface,diferencaEixoNumerico, w, h);
        escalaY.criar(qtdDivisores.getEixoX(), diferencaEixoInterface, diferencaEixoNumerico,w, h);

        anc.getChildren().add(0, escalaX.getGrupo());  
        anc.getChildren().add(0, escalaY.getGrupo()); 

        anc.getChildren().add(0,divisoresEixoX.getGrupo());
        anc.getChildren().add(0,divisoresEixoY.getGrupo());
        
        funcao.criar(diferencaEixoNumerico, diferencaEixoInterface, w, h);
        anc.getChildren().add(funcao.getLinha());
   
        anc.addEventHandler(ScrollEvent.SCROLL, e->{
            System.out.println("x = "+e.getSceneX()+" y = "+ e.getSceneY());
            if((diferencaEixoNumerico>0.016&&e.getDeltaY()>0)||(diferencaEixoNumerico<512&&e.getDeltaY()<0)){
            double diferenca = 1+e.getDeltaY()/640;
            diferencaEixoInterface *= diferenca;

            if(qtdDivisores.getEixoX()*diferencaEixoInterface>=2*w){
                diferencaEixoInterface/=2;
                diferencaEixoNumerico/=2;
                System.out.println(diferencaEixoNumerico);
            }
            else if(qtdDivisores.getEixoX()*diferencaEixoInterface<w){
                diferencaEixoInterface*=2;
                diferencaEixoNumerico*=2;
            }

            divisoresEixoX.criar(qtdDivisores.getEixoX(), diferencaEixoInterface, w, h);
            divisoresEixoY.criar(qtdDivisores.getEixoY(), diferencaEixoInterface, w, h);

            escalaX.criar(qtdDivisores.getEixoX(), diferencaEixoInterface,diferencaEixoNumerico, w, h);
            escalaY.criar(qtdDivisores.getEixoY(), diferencaEixoInterface,diferencaEixoNumerico, w, h);

            funcao.criar(diferencaEixoNumerico, diferencaEixoInterface, w, h);

            }

            
        });
        
        anc.widthProperty().addListener((observable,antigo,novo)->{
            double wAntigo=w;
            w=novo.doubleValue();
            System.out.println("Resize horizontal, wAntigo = "+wAntigo+" wNovo="+ w);
            diferencaEixoInterface=w/(qtdDivisores.getEixoX()+1);
            qtdDivisores.recalcularY(w, h);

            eixoX.resizeX(wAntigo, w);
            eixoY.resizeX(wAntigo, w);

            divisoresEixoX.resize(qtdDivisores.getEixoX(), diferencaEixoInterface, w, h);
            divisoresEixoY.criar(qtdDivisores.getEixoY(), diferencaEixoInterface, w, h);

            escalaX.resize(qtdDivisores.getEixoX(), diferencaEixoInterface, w, h);
            escalaY.criar(qtdDivisores.getEixoY(), diferencaEixoInterface,diferencaEixoNumerico, w, h);

            funcao.criar(diferencaEixoNumerico, diferencaEixoInterface, w, h);
        });

        anc.heightProperty().addListener((observable,antigo,novo)->{
            double hAntigo=h;
            h=novo.doubleValue();
            System.out.println("Resize vertical, hAntigo = "+hAntigo+" hNovo="+ h);
            diferencaEixoInterface=w/(qtdDivisores.getEixoX()+1);
            qtdDivisores.recalcularY(w, h);

            eixoX.resizeY(hAntigo, h);
            eixoY.resizeY(hAntigo, h);

            divisoresEixoX.resize(qtdDivisores.getEixoX(), diferencaEixoInterface, w, h);
            divisoresEixoY.criar(qtdDivisores.getEixoY(), diferencaEixoInterface, w, h);

            escalaX.resize(qtdDivisores.getEixoX(), diferencaEixoInterface, w, h);
            escalaY.criar(qtdDivisores.getEixoY(), diferencaEixoInterface,diferencaEixoNumerico, w, h);

            funcao.criar(diferencaEixoNumerico, diferencaEixoInterface, w, h);
        });

    }

    public void adicionarFuncao(String novaFuncao){
        funcao.setFuncao(novaFuncao);
        funcao.criar(diferencaEixoNumerico, diferencaEixoInterface, w, h);
    }

    public void derivarFuncao(){
        funcao.derivar();
        funcao.criar(diferencaEixoNumerico, diferencaEixoInterface, w, h);
    }
}
