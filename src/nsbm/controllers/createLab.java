package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nsbm.models.lab;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class createLab {

    @FXML
    private JFXTextField id;

    @FXML
    private JFXComboBox faculty;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField seats;

    public void initialize() throws SQLException {
        setfaculty();
    }

    @FXML
    void close(ActionEvent event) {
        Stage thiswindow=(Stage) id.getScene().getWindow();
        thiswindow.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException, IOException {
        lab newlab=new lab(id.getText(),name.getText(),faculty.getSelectionModel().getSelectedItem().toString(),Integer.parseInt(seats.getText()));
        boolean result=newlab.save();
        if(result){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Lab Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) id.getScene().getWindow();
            thiswin.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Lab Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) id.getScene().getWindow();
            thiswin.close();
        }
    }

    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

}
