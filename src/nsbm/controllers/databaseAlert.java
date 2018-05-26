package nsbm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class databaseAlert {

    public Button cancelButton;

    void show(String title){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/error.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.showAndWait();
//            cancelButton.onActionProperty();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closewin(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
