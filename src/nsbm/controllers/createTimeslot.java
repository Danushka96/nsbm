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
import nsbm.models.course;
import nsbm.models.subject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import nsbm.models.timeslot;

public class createTimeslot {

    @FXML
    private JFXComboBox subject;

    @FXML
    private JFXComboBox day;

    @FXML
    private JFXComboBox from;

    @FXML
    private JFXComboBox to;

    @FXML
    private JFXTextField timeid;

    public void initialize() throws SQLException {
        //System.out.println("HI");
        setSubject();
        setDay();
        setTo();
        setfrom();
    }

    @FXML
    void close(ActionEvent event) {
        Stage thiswindow = (Stage) timeid.getScene().getWindow();
        thiswindow.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException, IOException {
        String course_id=selectTimetable.getcode();
        String faculty= course.find(course_id).getFaculty();
        timeslot time=new timeslot(timeid.getText(),course_id,faculty,from.getSelectionModel().getSelectedItem().toString(),to.getSelectionModel().getSelectedItem().toString(),day.getSelectionModel().getSelectedItem().toString(),subject.getSelectionModel().getSelectedItem().toString());
        boolean result=time.save();
        if (result){
            Stage thiswindow = (Stage) timeid.getScene().getWindow();
            thiswindow.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add New Timeslot");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add New Timeslot");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        }
    }

    private void setSubject() throws SQLException {
        String course_id=selectTimetable.getcode();
        ArrayList<nsbm.models.subject> subjects= nsbm.models.subject.getall();
        for(subject sub:subjects){
            if(sub.getCourse_id().equals(course_id)){
                subject.getItems().add(sub.getCode());
            }
        }
    }

    private void setDay(){
        day.getItems().add("Sunday");
        day.getItems().add("Monday");
        day.getItems().add("Tuesday");
        day.getItems().add("Wednesday");
        day.getItems().add("Friday");
        day.getItems().add("Saturday");
    }

    private void setfrom(){
        from.getItems().add("8.00");
        from.getItems().add("8.30");
        from.getItems().add("9.00");
        from.getItems().add("9.30");
        from.getItems().add("10.00");
        from.getItems().add("10.30");
        from.getItems().add("11.00");
        from.getItems().add("11.30");
        from.getItems().add("12.00");
        from.getItems().add("12.30");
        from.getItems().add("13.00");
        from.getItems().add("13.30");
        from.getItems().add("14.00");
        from.getItems().add("14.30");
        from.getItems().add("15.00");
        from.getItems().add("15.30");
        from.getItems().add("16.00");
        from.getItems().add("16.30");
        from.getItems().add("17.00");
        from.getItems().add("17.30");
        from.getItems().add("18.00");
    }

    private void setTo(){
        to.getItems().add("8.00");
        to.getItems().add("8.30");
        to.getItems().add("9.00");
        to.getItems().add("9.30");
        to.getItems().add("10.00");
        to.getItems().add("10.30");
        to.getItems().add("11.00");
        to.getItems().add("11.30");
        to.getItems().add("12.00");
        to.getItems().add("12.30");
        to.getItems().add("13.00");
        to.getItems().add("13.30");
        to.getItems().add("14.00");
        to.getItems().add("14.30");
        to.getItems().add("15.00");
        to.getItems().add("15.30");
        to.getItems().add("16.00");
        to.getItems().add("16.30");
        to.getItems().add("17.00");
        to.getItems().add("17.30");
        to.getItems().add("18.00");
    }
}
