package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.PrintStream;
import java.sql.*;

public class NewClient {
    private EventFrame eventFrame;

    @FXML
    private TextField fieldFullName;

    @FXML
    private TextField fieldPassport;

    @FXML
    private TextField fieldDateOfBirth;

    @FXML
    private TextField fieldTelephone;

    @FXML
    private TextField fieldAdress;

    @FXML
    private Button buttonSaveClient;

    @FXML
    void initialize(){
        buttonSaveClient.setOnAction(event -> {
            try {
                DatabaseHandler databaseHandler=new DatabaseHandler();
                Statement stmt = databaseHandler.getCnct().createStatement();
                PreparedStatement preparedStatement = null;
                preparedStatement = databaseHandler.getCnct().prepareStatement("INSERT INTO clients(passport,Full_name,Adress,Date_of_birth,Telephone) VALUES (?,?,?,TO_DATE(?, 'DD.MM.YYYY'),?)");
                preparedStatement.setString(1,fieldPassport.getText());
                preparedStatement.setString(2,fieldFullName.getText());
                preparedStatement.setString(3,fieldAdress.getText());
                preparedStatement.setString(4,fieldDateOfBirth.getText());
                preparedStatement.setString(5,fieldTelephone.getText());
                preparedStatement.executeQuery();
                buttonSaveClient.getScene().getWindow().hide();
                eventFrame=new EventFrame();
                eventFrame.openNewFrame("/sample/adminFrame.fxml");
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });

    }

}