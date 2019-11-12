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
    private EventFrame eventFrame;

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
                    if (databaseHandler.getPosition(fullNameField.getText()).equals("Admin")) {
                        loginButton.getScene().getWindow().hide();
                        eventFrame=new EventFrame();
                        eventFrame.openNewFrame("/sample/adminFrame.fxml");
                        System.out.println(databaseHandler.getPosition(fullNameField.getText()));//надо вывести админовскую форму
                    }
                    if (databaseHandler.getPosition(fullNameField.getText()).equals("executor")) {
                        System.out.println("empty");
                    }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        } );

    }
}

