package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javax.swing.text.html.ImageView;

public class NewContract {
    @FXML
    private TextField fieldDateOfConclusion;

    @FXML
    private Button buttonSaveContract;

    @FXML
    private TextField fieldContractNumber;

    @FXML
    private ChoiceBox<?> choiseBoxClient;

    @FXML
    private ChoiceBox<?> choiseBoxNetworkConfig;

    @FXML
    private TextField fieldDateOfExpiry;

    @FXML
    private TextField fieldCostOfWork;

    @FXML
    private ImageView imgBack;

    @FXML
    void initialize(){

    }
}
