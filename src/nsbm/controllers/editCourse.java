package nsbm.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nsbm.models.bachelor;
import nsbm.models.course;
import nsbm.models.master;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class editCourse {

    public JFXRadioButton bach;
    public ToggleGroup type;
    public JFXRadioButton mast;
    @FXML
    private JFXTextField courseid;

    @FXML
    private JFXComboBox faculty;

    @FXML
    private JFXTextField credits;

    @FXML
    private JFXTextField courseName;

    @FXML
    private JFXTextField years;

    @FXML
    private JFXCheckBox expandable;

    public void initialize() throws SQLException {
        setfaculty();
        bach.setToggleGroup(type);
        bach.setUserData("B");
        bach.setSelected(true);
        mast.setToggleGroup(type);
        mast.setUserData("M");
        setData();
    }

    @FXML
    void close(ActionEvent event) {
        Stage thiswindow = (Stage) faculty.getScene().getWindow();
        thiswindow.close();
    }

    @FXML
    void update(ActionEvent event) throws SQLException, IOException {
        int expand=0;
        boolean result=false;
        if(expandable.isSelected()){
            expand=1;
        }

        if(type.getSelectedToggle().getUserData().equals("B")) {
            bachelor bach = new bachelor(courseid.getText(), faculty.getSelectionModel().getSelectedItem().toString(), courseName.getText(), Integer.parseInt(credits.getText()), Integer.parseInt(years.getText()), expand);
            result = bach.update();
        }else{
            master mstr= new master(courseid.getText(),faculty.getSelectionModel().getSelectedItem().toString(),courseName.getText(),Integer.parseInt(credits.getText()),Integer.parseInt(years.getText()),expand);
            result=mstr.update();
        }
        if (result) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/updateSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Course Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) courseid.getScene().getWindow();
            thiswin.close();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/updateFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Course Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) courseid.getScene().getWindow();
            thiswin.close();
        }
    }

    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

    private void setData() throws SQLException{
        String code=indexCourse.getSelecter();
        course crse=course.find(code);
        courseid.setText(crse.getCode());
        courseName.setText(crse.getName());
        credits.setText(Integer.toString(crse.getCredits()));
        faculty.getSelectionModel().select(crse.getFaculty());
        years.setText(Integer.toString(crse.getNumberofyears()));
        boolean expan= crse.getCan_extend() == 1;
        expandable.setSelected(expan);
    }
}
