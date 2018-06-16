package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
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

public class selectTimetable {

    private static String course_code;

    @FXML
    private JFXComboBox faculty;

    @FXML
    private JFXComboBox course;

    public void initialize() throws SQLException {
        setfaculty();
    }

    @FXML
    void close(ActionEvent event) {
        Stage thiswin= (Stage) faculty.getScene().getWindow();
        thiswin.close();
    }

    @FXML
    void show(ActionEvent event) throws IOException {
        course_code=course.getSelectionModel().getSelectedItem().toString();
        Stage thiswin= (Stage) faculty.getScene().getWindow();
        thiswin.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/timetable/index.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Undergraduate Registration");
        stage.setScene(new Scene(root1));
        stage.showAndWait();
    }

    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

    public void setCourses(ActionEvent inputMethodEvent) throws SQLException {
        course.getItems().clear();
        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        ArrayList<String> all=nsbm.models.course.getall(fac);
        for(String code:all){
            course.getItems().add(code);
        }
    }

    public static String getcode(){
        return course_code;
    }

}
