package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class studenttype {
    public JFXButton undergrad;
    public JFXButton postgrad;
    public JFXButton close;

    public void openundergrad(ActionEvent actionEvent) throws IOException {
        Stage thiswin=(Stage) close.getScene().getWindow();
        thiswin.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/create/regundergrad.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Undergraduate Registration");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void openpostgrad(ActionEvent actionEvent) throws IOException {
        Stage thiswin=(Stage) close.getScene().getWindow();
        thiswin.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/create/regpostgrad.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Postgraduate Registration");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void closewindow(ActionEvent actionEvent) {
        Stage stage=(Stage) close.getScene().getWindow();
        stage.close();
    }
}
