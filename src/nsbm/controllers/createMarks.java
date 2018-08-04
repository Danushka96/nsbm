package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import nsbm.models.studentsubject;
import nsbm.models.undergraduate;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Danushka
 */
public class createMarks {
    @FXML
    private AnchorPane marks;

    @FXML
    private JFXComboBox subject;

    @FXML
    private JFXComboBox assignment;

    private String student_id;
    private String semester_id;
    private int type;

    public void initialize() throws SQLException {
        student_id = nsbm.controllers.selectstudent.getStudent_id();
        type = nsbm.controllers.selectstudent.getType();
        semester_id = nsbm.controllers.selectstudent.getSemester_id();

        fillsubjects();
    }

    @FXML
    void addmarks(ActionEvent event) {

    }

    @FXML
    void loadassignments(ActionEvent event) {

    }

    private void fillsubjects() throws SQLException {
        String reg_number;
        if(undergraduate.findUndergraduate(student_id).getCourse_id()!=null){
            reg_number = undergraduate.findUndergraduate(student_id).getReg_Number();
        }else{
            reg_number = undergraduate.findUndergraduate(student_id).getReg_Number();
        }
        ArrayList<studentsubject> all  = studentsubject.findstudentSubject(semester_id,reg_number);
        for(studentsubject a:all)
        {
            subject.getItems().add(a.getSubject_id());
        }
    }
}
