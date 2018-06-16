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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import nsbm.models.course;
import nsbm.models.lecturer;
import nsbm.models.subject;

public class createSubject {


    public JFXComboBox lecturer;
    @FXML
    private JFXTextField code;

    @FXML
    private JFXComboBox course;

    @FXML
    private JFXTextField credits;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField fee;

    @FXML
    private JFXTextField hours;

    public void initialize() throws SQLException {
        setCourses();
    }

    @FXML
    void close(ActionEvent event) {
        Stage thisstage= (Stage) course.getScene().getWindow();
        thisstage.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException, IOException {
        subject subj=new subject(code.getText(),name.getText(),Double.parseDouble(fee.getText()),Integer.parseInt(credits.getText()),lecturer.getSelectionModel().getSelectedItem().toString(),Integer.parseInt(hours.getText()),course.getSelectionModel().getSelectedItem().toString());
        boolean result=subj.save();
        if(result){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add new Subject");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin=(Stage) lecturer.getScene().getWindow();
            thiswin.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add new Subject");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin=(Stage) lecturer.getScene().getWindow();
            thiswin.close();
        }
    }

    private void setCourses() throws SQLException{
        ArrayList<course> all=nsbm.models.course.getall();
        for(course crse:all){
            course.getItems().add(crse.getCode());
        }
        ArrayList<lecturer> alllec=nsbm.models.lecturer.getall();
        for(lecturer lect:alllec){
            lecturer.getItems().add(lect.getLecture_id());
        }
    }



}
