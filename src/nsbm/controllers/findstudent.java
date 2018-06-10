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

public class findstudent {

    private static String student_id;

    @FXML
    private JFXTextField undergrad;

    @FXML
    private JFXTextField postgrad;

    @FXML
    private JFXButton undersearch;

    @FXML
    private JFXButton postsearch;

    @FXML
    void searchpostgrad(ActionEvent event) throws IOException {

    }

    @FXML
    void searchundergrad(ActionEvent event) throws IOException {
        String typed=undergrad.getText();
        if(!typed.equals("")){
            student_id=typed;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/editundergrad.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Undergraduate Update");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public static String getStudent_id(){
        return student_id;
    }

}
