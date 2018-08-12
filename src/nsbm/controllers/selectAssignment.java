package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nsbm.models.semester;

/**
 * @author Danushka
 */
public class selectAssignment {

    private static String facul;
    private static String sem;

    @FXML
    private JFXComboBox faculty;

    @FXML
    private JFXComboBox semester;

    public void initialize() throws SQLException{
        setFaculty();
    }

    @FXML
    void changesemester(ActionEvent event) throws SQLException {
        setSem();
    }

    @FXML
    void go(ActionEvent event) throws IOException {
        sem = semester.getSelectionModel().getSelectedItem().toString();
        facul = faculty.getSelectionModel().getSelectedItem().toString();
        Stage thiswin=(Stage)faculty.getScene().getWindow();
        thiswin.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/assignment/index.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Index Assignments");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private void setFaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }
    private void setSem() throws SQLException{
        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        ArrayList<semester> all=nsbm.models.semester.getall();
        for(semester sem:all){
            if(sem.getFaculty().equals(fac)){
                semester.getItems().add(sem.getSemester_id());
            }
        }
    }

    public static String getFacul(){
        return facul;
    }
    public static String getSem(){
        return sem;
    }
}
