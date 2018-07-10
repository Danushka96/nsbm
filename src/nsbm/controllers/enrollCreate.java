package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import nsbm.models.studentsubject;
import nsbm.models.subject;
import nsbm.models.undergraduate;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Danushka
 */
public class enrollCreate {

    public Text label1;
    public Text label2;
    public Text label3;
    public Text label4;
    public JFXButton saveb;

    @FXML
    private JFXComboBox sub1;

    @FXML
    private JFXComboBox sub2;

    @FXML
    private JFXComboBox sub3;

    @FXML
    private JFXComboBox sub4;

    @FXML
    private Text credits;

    @FXML
    private TextField credit1;

    @FXML
    private TextField credit2;

    @FXML
    private TextField credit3;

    @FXML
    private TextField credit4;

    private String student1;
    private String semester1;
    private undergraduate undergrad;

    private final int NumCredits=12;

    @FXML
    public void initialize() throws SQLException{
        student1=enrollSelect.getStudent();
        semester1=enrollSelect.getSemester();
        undergrad=undergraduate.getStudentID(student1);
        ArrayList<subject> allsub=subject.getall();
        setSubjects(allsub);
    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        if(sub1.getSelectionModel().getSelectedItem()!=null) {
            studentsubject sub1s = new studentsubject(semester1, student1, sub1.getSelectionModel().getSelectedItem().toString());
            sub1s.save();
        }
        if(sub1.getSelectionModel().getSelectedItem()!=null) {
            studentsubject sub2s = new studentsubject(semester1, student1, sub2.getSelectionModel().getSelectedItem().toString());
            sub2s.save();
        }
        if(sub1.getSelectionModel().getSelectedItem()!=null) {
            studentsubject sub3s = new studentsubject(semester1, student1, sub3.getSelectionModel().getSelectedItem().toString());
            sub3s.save();
        }
        if(sub1.getSelectionModel().getSelectedItem()!=null) {
            studentsubject sub4s = new studentsubject(semester1, student1, sub4.getSelectionModel().getSelectedItem().toString());
            sub4s.save();
        }

    }


    public void checkCredits(ActionEvent actionEvent) throws SQLException {
        int count=0;
        if(sub1.getSelectionModel().getSelectedItem()!=null){
            subject sub1s=subject.findsubject(sub1.getSelectionModel().getSelectedItem().toString());
            credit1.setText(Integer.toString(sub1s.getNumberofCredits()));
            count+=sub1s.getNumberofCredits();
            checkCurrent(count,1);
        }
        if(sub2.getSelectionModel().getSelectedItem()!=null){
            subject sub2s=subject.findsubject(sub2.getSelectionModel().getSelectedItem().toString());
            credit2.setText(Integer.toString(sub2s.getNumberofCredits()));
            count+=sub2s.getNumberofCredits();
            checkCurrent(count,2);
        }
        if(sub3.getSelectionModel().getSelectedItem()!=null){
            subject sub3s=subject.findsubject(sub3.getSelectionModel().getSelectedItem().toString());
            credit3.setText(Integer.toString(sub3s.getNumberofCredits()));
            count+=sub3s.getNumberofCredits();
            checkCurrent(count,3);
        }
        if(sub4.getSelectionModel().getSelectedItem()!=null){
            subject sub4s=subject.findsubject(sub4.getSelectionModel().getSelectedItem().toString());
            credit4.setText(Integer.toString(sub4s.getNumberofCredits()));
            count+=sub4s.getNumberofCredits();
            checkCurrent(count,4);
        }
        changeLabel(count);
    }

    private void setSubjects(ArrayList<subject> allsub){
        for(subject sub:allsub){
            if(sub.getCourse_id().equals(undergrad.getCourse_id())){
                sub1.getItems().add(sub.getCode());
                sub2.getItems().add(sub.getCode());
                sub3.getItems().add(sub.getCode());
                sub4.getItems().add(sub.getCode());
            }
        }
    }

    private void changeLabel(int current){
        String remain=Integer.toString(NumCredits-current);
        credits.setText(remain);
    }

    private void checkCurrent(int count, int subnum){
        if(subnum==1&&count<NumCredits){
            sub2.setVisible(true);
            credit2.setVisible(true);
            label2.setVisible(true);
        }else if(subnum==2&&count<NumCredits){
            sub3.setVisible(true);
            credit3.setVisible(true);
            label3.setVisible(true);
        }else if(subnum==3&&count<NumCredits){
            sub4.setVisible(true);
            credit4.setVisible(true);
            label4.setVisible(true);
        }else{
            if(count>=NumCredits){
                saveb.setDisable(false);
            }
        }
    }
}
