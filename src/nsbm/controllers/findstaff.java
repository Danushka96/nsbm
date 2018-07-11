package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nsbm.models.instructor;
import nsbm.models.lecturer;

import java.io.IOException;
import java.sql.SQLException;

public class findstaff {

    private static String staffmember;
    @FXML
    private JFXTextField lecturer1;

    @FXML
    private JFXTextField Instructor;

    @FXML
    private JFXButton lecturesearch;

    @FXML
    private JFXButton instructorsearch;

    @FXML
    void searchins(ActionEvent event) throws IOException, SQLException {
        String typed=Instructor.getText();
        if(!typed.equals("")){
            staffmember=typed;
        }
        instructor ins = instructor.findInstructor(staffmember);
        if(ins.getNic()!=null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/instructor/view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Lecturer Update");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/searchnotfound.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Error on delete");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    @FXML
    void searchlect(ActionEvent event) throws IOException, SQLException {
        String typed= lecturer1.getText();
        if(!typed.equals("")){
            staffmember=typed;
        }
        lecturer lect = lecturer.findLecturer(staffmember);
        if(lect.getOffice_number()!=null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/lecturer/view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Instructor Update");
            stage.setScene(new Scene(root1));
            stage.show();
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/searchnotfound.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Error  on delete");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    static String getStaffmember(){
        return staffmember;
    }

}
