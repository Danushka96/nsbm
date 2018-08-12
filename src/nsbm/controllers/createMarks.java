package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nsbm.models.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Danushka
 */
public class createMarks {
    public JFXTextField markss;
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
    void addmarks(ActionEvent event) throws SQLException {
        String assign = assignment.getSelectionModel().getSelectedItem().toString();
        String student1 = selectstudent.getStudent_id();
        String reg;
        if(selectstudent.getStudentType()==1){
            System.out.println("under");
            reg = undergraduate.findUndergraduate(student1).getReg_Number();
        }else{
            System.out.println("post");
            reg = postgraduate.findPostgraduate(student1).getReg_Number();
        }
        studentassignment stuass = new studentassignment(assign,reg,Integer.parseInt(markss.getText()));
        stuass.save();

        Stage thiswin = (Stage) marks.getScene().getWindow();
        thiswin.close();
    }

    @FXML
    void loadassignments(ActionEvent event) throws SQLException {
        int type = selectstudent.getType();
        String sem = selectstudent.getSemester_id();
        String sub = subject.getSelectionModel().getSelectedItem().toString();
        ArrayList<assignment> all = nsbm.models.assignment.findassignmentSubject(sub,type,sem);
        for(assignment a:all){
            assignment.getItems().add(a.getAssignment_id());
        }
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

    public void close(ActionEvent actionEvent) {
        Stage thiswin = (Stage) marks.getScene().getWindow();
        thiswin.close();
    }
}
