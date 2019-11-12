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
import javafx.stage.Stage;

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
            System.setOut(new PrintStream(System.out, true, "utf-8"));
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection cnct = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "sys as sysdba","BATEchampion2017");
            Statement stmt = cnct.createStatement();
            ResultSet resultSet=stmt.executeQuery("SELECT contract_number,to_char(date_of_conclusion,'DD/MM/YYYY'),client_information,to_char(date_of_Expiry,'DD/MM/YYYY'),cost_of_work FROM contracts");
            while (resultSet.next()){
                String contract_number= Integer.toString(resultSet.getInt("contract_number"));
                String date_of_conclusion=resultSet.getString("to_char(date_of_conclusion,'DD/MM/YYYY')");
                String client_information=resultSet.getString("client_information");
                String date_of_Expiry=resultSet.getString("to_char(date_of_Expiry,'DD/MM/YYYY')");
                String cost_of_work=Float.toString(resultSet.getFloat("Cost_of_work"));
                listOfContracts.getItems().addAll(contract_number+"        "+date_of_conclusion+"       "+date_of_Expiry+"    "+client_information+cost_of_work);
                listOfContracts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        buttonNewClient.setOnAction(event ->{
            try {
                buttonNewClient.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/newClient.fxml"));
                loader.load();
                Parent root1 = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.showAndWait();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }


}
