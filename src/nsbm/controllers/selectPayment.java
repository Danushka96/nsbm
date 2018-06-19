package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class selectPayment {

    private static String course_id;
    private static String faculty_id;

    @FXML
    private JFXComboBox faculty;

    @FXML
    private JFXComboBox course;

    public void initialize() throws SQLException{
        setFaculty();
    }

    @FXML
    void go(ActionEvent event) throws IOException {
        course_id=course.getSelectionModel().getSelectedItem().toString();
        faculty_id=faculty.getSelectionModel().getSelectedItem().toString();
        Stage thiswin= (Stage) course.getScene().getWindow();
        thiswin.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/payment/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add New Payments");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void changecourse(ActionEvent actionEvent) throws SQLException {
        course.getItems().clear();
        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        ArrayList<String> all=nsbm.models.course.getall(fac);
        for(String code:all){
            course.getItems().add(code);
        }
    }

    private void setFaculty() throws SQLException{
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

    public static String getCourse_id(){
        return course_id;
    }

    public static String getFaculty() { return faculty_id; }
}
