package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nsbm.models.UniversityMemeber;
import nsbm.models.semester;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Danushka
 */
public class selectstudent {
    public JFXComboBox semesterselect;
    @FXML
    private JFXComboBox assignmenttype;

    @FXML
    private JFXTextField studentid;

    private static String student_id;
    private static int type;

    public void initialize(){
        assignmenttype.getItems().add("Exam");
        assignmenttype.getItems().add("Assignment");
    }

    @FXML
    void submitstudent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/searchnotfound.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Marks");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public static String getStudent_id() {
        return student_id;
    }

    public static int getType() {
        return type;
    }

    public void getstudent(ActionEvent actionEvent) throws SQLException, IOException {
        student_id = studentid.getText();
        type=assignmenttype.getSelectionModel().getSelectedItem().equals("Exam")?1:0;

        UniversityMemeber student = UniversityMemeber.findmember(student_id);
        if(student.getFaculty()!=null){
            ArrayList<semester> allsem = semester.getall();
            for(semester sem:allsem){
                if(sem.getFaculty().equals(student.getFaculty())){
                    semesterselect.getItems().add(sem.getSemester_id());
                }
            }
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/searchnotfound.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Select Student");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }
}
