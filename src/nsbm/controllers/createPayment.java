package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nsbm.models.payment;
import nsbm.models.reciept;
import nsbm.models.semester;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class createPayment {

    @FXML
    private JFXTextField student;

    @FXML
    private JFXComboBox semesters;

    @FXML
    private JFXTextField amount;

    @FXML
    private JFXDatePicker issue;

    public void initialize() throws SQLException{
        setData();
    }

    @FXML
    void close(ActionEvent event) {
        Stage thiswin=(Stage)issue.getScene().getWindow();
        thiswin.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException, IOException {
        reciept thisone=new reciept(Double.parseDouble(amount.getText()),issue.getValue().toString());
        int reciept_id=thisone.save();
        payment pay = new payment(student.getText(),semesters.getSelectionModel().getSelectedItem().toString(),Integer.toString(reciept_id));
        boolean result=pay.save();
        if(result){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add new Payment");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) student.getScene().getWindow();
            thiswin.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add new Payment");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) student.getScene().getWindow();
            thiswin.close();
        }
    }

    private void setData() throws SQLException{
        String course_id=selectPayment.getCourse_id();
        String facultyId=selectPayment.getFaculty();

        ArrayList<semester> sems = semester.getall();
        for(semester sem:sems){
            if(sem.getFaculty().equals(facultyId)){
                semesters.getItems().add(sem.getSemester_id());
            }
        }
    }

}
