package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class findstaff {

    private static String staffmember;
    @FXML
    private JFXTextField lecturer;

    @FXML
    private JFXTextField Instructor;

    @FXML
    private JFXButton lecturesearch;

    @FXML
    private JFXButton instructorsearch;

    @FXML
    void searchins(ActionEvent event) throws IOException {
        String typed=Instructor.getText();
        if(!typed.equals("")){
            staffmember=typed;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/instructor/view.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Lecturer Update");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void searchlect(ActionEvent event) throws IOException {
        String typed=lecturer.getText();
        if(!typed.equals("")){
            staffmember=typed;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/lecturer/view.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Instructor Update");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    static String getStaffmember(){
        return staffmember;
    }

}
