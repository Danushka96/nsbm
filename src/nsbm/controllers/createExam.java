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
import nsbm.models.assignment;
import nsbm.models.subject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Danushka
 */
public class createExam {
    @FXML
    private JFXTextField marks;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXComboBox subject;

    public void initialize() throws SQLException {
        setSubject();
    }

    @FXML
    void close(ActionEvent event) {
        Stage thiswindow = (Stage)name.getScene().getWindow();
        thiswindow.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException, IOException {
        String sem=selectAssignment.getSem();
        assignment newassignment = new assignment(id.getText(), name.getText(),subject.getSelectionModel().getSelectedItem().toString() ,Integer.parseInt(marks.getText()),sem);
        newassignment.setType(1);
        boolean result=newassignment.save();
        if(result){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add new Exam");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) subject.getScene().getWindow();
            thiswin.close();
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add new Exam");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) subject.getScene().getWindow();
            thiswin.close();
        }
    }

    private void setSubject() throws SQLException {
        ArrayList<nsbm.models.subject> subjects= nsbm.models.subject.getall();
        for(subject sub:subjects){
            subject.getItems().add(sub.getCode());
        }
    }
}
