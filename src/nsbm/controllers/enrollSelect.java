package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nsbm.models.postgraduate;
import nsbm.models.semester;
import nsbm.models.student;
import nsbm.models.undergraduate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Danushka
 */
public class enrollSelect {

    private static String student1;
    private static String semester1;

    @FXML
    private JFXTextField studentId;

    @FXML
    private JFXComboBox semesterid;

    @FXML
    private Text alert;

    public void initialize() throws SQLException{

    }

    @FXML
    void addsubjects(ActionEvent event) throws IOException {
        semester1=semesterid.getSelectionModel().getSelectedItem().toString();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/enroll/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Semester Registration");
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        Stage thiswin = (Stage) studentId.getScene().getWindow();
        thiswin.close();
    }

    public void selectsub(ActionEvent actionEvent) throws SQLException{
        //System.out.println("hi");
        alert.setVisible(false);
        ArrayList<semester> sem=semester.getall();
        student std=student.findstudent(studentId.getText());
        if(std.getFaculty()!=null){
            for(semester semes:sem){
                if(semes.getFaculty().equals(std.getFaculty())){
                    semesterid.getItems().add(semes.getSemester_id());
                }
            }
            student1=studentId.getText();
        }else{
            alert.setVisible(true);
        }
    }

    public static String getStudent(){
        return student1;
    }

    public static String getSemester(){
        return semester1;
    }
}
