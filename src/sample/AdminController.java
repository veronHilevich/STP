package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.PrintStream;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

import static java.lang.String.join;
import static javax.swing.UIManager.getInt;

public class AdminController {

    @FXML
    private Button buttonNewClient;

    @FXML
    private Button buttonAddContract;

    @FXML
    private Button buttonDeleteContract;

    @FXML
    private ListView<String> listOfContracts;

    @FXML
    void initialize(){
        try {
            DatabaseHandler databaseHandler=new DatabaseHandler();
           databaseHandler.infoInListView(listOfContracts);  //заполняем листвью данными
            }

        catch (Exception ex){
            ex.printStackTrace();
        }

        buttonNewClient.setOnAction(event ->{
            try {
                buttonNewClient.getScene().getWindow().hide();
               EventFrame eventFrame=new EventFrame();
               eventFrame.openNewFrame("/sample/newClient.fxml");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttonDeleteContract.setOnAction(event -> {
            String selectedItem = listOfContracts.getSelectionModel().getSelectedItem();
            try {
                DatabaseHandler databaseHandler = new DatabaseHandler();
                Statement stmt = databaseHandler.getCnct().createStatement();
                PreparedStatement preparedStatement = null;
                preparedStatement = databaseHandler.getCnct().prepareStatement("DELETE FROM Contracts WHERE contract_number=?");
                String[] params = selectedItem.split(" ");   //разделяем строку договора до первого пробела в масив
                String firstString = params[0];
                System.out.println(firstString);
                preparedStatement.setInt(1, Integer.parseInt(firstString));
                preparedStatement.executeQuery();
                databaseHandler.infoInListView(listOfContracts);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        });
        buttonAddContract.setOnAction(event -> {
            try {
                buttonAddContract.getScene().getWindow().hide();
                EventFrame eventFrame=new EventFrame();
                eventFrame.openNewFrame("/sample/newContract.fxml");
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }


}
