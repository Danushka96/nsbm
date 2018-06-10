package nsbm.controllers.alert;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class updateSuccess {

    @FXML
    private JFXButton close;

    @FXML
    private JFXButton add;


    public void close(ActionEvent actionEvent) {
        Stage thiswin= (Stage)close.getScene().getWindow();
        thiswin.close();
    }

    public void addnew(ActionEvent actionEvent) throws IOException {
        Stage thiswin= (Stage)close.getScene().getWindow();
        thiswin.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/view/findbox.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Main Interface");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
