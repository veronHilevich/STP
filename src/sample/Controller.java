package sample;

import java.io.PrintStream;
import java.net.URL;
import java.sql.*;
import java.util.*;

import com.sun.source.doctree.StartElementTree;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fullNameField;

    @FXML
    private Button loginButton;

    @FXML
    void initialize() {
        loginButton.setOnAction(event ->{
            try {DatabaseHandler databaseHandler=new DatabaseHandler();
                Statement stmt =  databaseHandler.getCnct().createStatement();
                PreparedStatement preparedStatement = null;

                preparedStatement =  databaseHandler.getCnct().prepareStatement("SELECT position FROM employees where full_name=?");
                preparedStatement.setString(1,fullNameField.getText());
                ResultSet resultSet=preparedStatement.executeQuery();
                while(resultSet.next()){

                    String position = resultSet.getString("position");
                    if (position.equals("Admin")) {
                        loginButton.getScene().getWindow().hide();
                        FXMLLoader loader=new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sample/adminFrame.fxml"));
                        loader.load();
                        Parent root=loader.getRoot();
                        Stage stage =new Stage();
                        stage.setScene(new Scene(root));
                        stage.showAndWait();
                        System.out.println(position);//надо вывести админовскую форму
                    }
                    if (position.equals("executor")) {
                        System.out.println("empty");
                    }
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        } );

    }
}

