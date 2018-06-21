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

public class editAssignment {

    @FXML
    private JFXTextField marks;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXComboBox subject;

    public void initialize() throws SQLException{
        setSubject();
        getData();
    }

    @FXML
    void Update(ActionEvent event) throws SQLException, IOException {
        String sem=selectAssignment.getSem();
        assignment upassign=new assignment(id.getText(), name.getText(),subject.getSelectionModel().getSelectedItem().toString() ,Integer.parseInt(marks.getText()),sem);
        boolean result = upassign.update();
        if(result){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/updateSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Assignment");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) subject.getScene().getWindow();
            thiswin.close();
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/updateFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Assignment");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) subject.getScene().getWindow();
            thiswin.close();
        }
    }

    @FXML
    void close(ActionEvent event) {
        Stage thiswindow = (Stage)name.getScene().getWindow();
        thiswindow.close();
    }

    private void setSubject() throws SQLException{
        ArrayList<nsbm.models.subject> subjects= nsbm.models.subject.getall();
        for(subject sub:subjects){
            subject.getItems().add(sub.getCode());
        }
    }

    private void getData() throws SQLException{
        String assingid = indexAssignments.getSelecter();
        //System.out.println(assingid);
        assignment assing=assignment.findassignment(assingid);
        //System.out.println(assing.getName());
        id.setText(assing.getAssignment_id());
        name.setText(assing.getName());
        marks.setText(Integer.toString(assing.getMarks()));
        subject.getSelectionModel().select(assing.getSubject_id());
    }

}
