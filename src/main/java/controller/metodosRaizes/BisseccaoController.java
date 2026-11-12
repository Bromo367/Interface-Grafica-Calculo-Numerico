package controller.metodosRaizes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BisseccaoController {

    @FXML
    private HBox hboxLayout;

    @FXML
    private Text lblFeedback;

    @FXML
    private ScrollPane scrollLayout;

    @FXML
    private VBox vboxConteudo;

    @FXML
    void initialize() {
        scrollLayout.widthProperty().addListener((observable, antigo, novo)->{
            hboxLayout.setPrefWidth(novo.doubleValue());
        });


    }

    @FXML
    void calcularRaiz(ActionEvent event) {

    }
}
