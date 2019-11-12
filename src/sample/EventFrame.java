package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EventFrame {
    public void openNewFrame(String frame)throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource(frame));
        loader.load();
        Parent root=loader.getRoot();
        Stage stage =new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
