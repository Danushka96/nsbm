package nsbm.controllers;
import nsbm.models.undergraduate;
import nsbm.models.alresult;

import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.util.Pair;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class editundergrad {

    @FXML
    public JFXButton tosecond1;
    public JFXDatePicker regdate;
    public JFXTextField intake;
    public JFXComboBox course;
    public JFXComboBox faculty;
    public JFXTextField alindex;
    public JFXTextField zscore;
    public JFXTextField rank;
    public JFXComboBox stream;
    public JFXComboBox subject1;
    public JFXComboBox subject2;
    public JFXComboBox subject3;
    public JFXTextField score1;
    public JFXTextField score2;
    public JFXTextField score3;
    public JFXTextField firstname;
    public JFXTextField lastname;
    public JFXTextField nic;
    public JFXTextField email;
    public JFXRadioButton male;
    public JFXRadioButton female;
    public JFXDatePicker dob;
    public JFXTextField mobile;
    public JFXTextArea address;
    public JFXTextField studentid;
    public JFXTextField regnumber;

    @FXML
    private AnchorPane third;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton tosecond;

    @FXML
    private JFXButton cancel3;

    @FXML
    private AnchorPane second;

    @FXML
    private JFXButton tothird;

    @FXML
    private JFXButton tofirst;

    @FXML
    private JFXButton cancel2;

    @FXML
    private AnchorPane first;

    @FXML
    private JFXButton cancel1;

    private String student_id=nsbm.controllers.findstudent.getStudent_id();

    private ToggleGroup group=new ToggleGroup();

    public void initialize() throws SQLException {
        setfaculty();
        setStream();

        undergraduate findstu=undergraduate.findUndergraduate(student_id);

        male.setToggleGroup(group);
        male.setUserData("M");
        male.setSelected(true);
        female.setToggleGroup(group);
        female.setUserData("F");

        setData(findstu);
    }


    @FXML
    void changepane(ActionEvent event) {
        if(event.getSource()==tofirst){
            first.toFront();
        }
        else if(event.getSource()==tosecond){
            second.toFront();
        }
        else if(event.getSource()==tothird){
            third.toFront();
        }
        else if(event.getSource()==tosecond1){
            second.toFront();
        }
    }

    @FXML
    void closewindow(ActionEvent event) {
        Stage thisstage= (Stage) save.getScene().getWindow();
        thisstage.close();
    }

    @FXML
    void update(ActionEvent event) throws SQLException, IOException {
        int intakenum=Integer.parseInt(intake.getText());
        int ranknum=Integer.parseInt(rank.getText());
        String gender="M";
        if(group.getSelectedToggle()!=null) {
            gender = group.getSelectedToggle().getUserData().toString();
        }
        String streamid=stream.getSelectionModel().getSelectedItem().toString();
        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        String courseid=course.getSelectionModel().getSelectedItem().toString();
        undergraduate undergrad=new undergraduate(
                regnumber.getText(),
                firstname.getText(),
                lastname.getText(),
                gender,
                fac,
                nic.getText(),
                email.getText(),
                dob.getValue().toString(),
                address.getText(),
                mobile.getText(),
                regdate.getValue().toString(),
                intakenum,
                studentid.getText(),
                streamid,
                courseid,
                ranknum
        );
        boolean insresult=undergrad.update();

//  Save in A/L Result Table
        String subj1=subject1.getSelectionModel().getSelectedItem().toString();
        String subj2=subject2.getSelectionModel().getSelectedItem().toString();
        String subj3=subject3.getSelectionModel().getSelectedItem().toString();
        String student=studentid.getText();
        alresult result1=new alresult(student,subj1,score1.getText());
        alresult result2=new alresult(student,subj2,score2.getText());
        alresult result3=new alresult(student,subj3,score3.getText());
        result1.update();
        result2.update();
        result3.update();

//        Alert Box
        System.out.println("Update Success");
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Student Registration");
        if(insresult){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Undergraduate Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin=(Stage) firstname.getScene().getWindow();
            thiswin.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Undergraduate Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        }
    }

    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

    public void setCourses(ActionEvent inputMethodEvent) throws SQLException {
        course.getItems().clear();
        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        ArrayList<String> all=nsbm.models.course.getall(fac);
        for(String code:all){
            course.getItems().add(code);
        }
    }

    public void setCourses() throws SQLException {
        course.getItems().clear();
        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        ArrayList<String> all=nsbm.models.course.getall(fac);
        for(String code:all){
            course.getItems().add(code);
        }
    }

    private void setStream(){
        stream.getItems().addAll(
                "Science",
                "Commerce",
                "Art",
                "Technology"
        );
    }
    @FXML
    private void setSubjects(){
        subject1.getItems().clear();
        subject2.getItems().clear();
        subject3.getItems().clear();

        String[] science={"Combined Maths", "Biology", "Physics", "Chemistry","ICT","Agriculture"};
        String[] arts={"ICT", "Geography", "Law","Agriculture"};
        String[] tech={"Science for Technology","Agriculture","Engineering Science","Biology for Technology"};
        String[] commerce={"Buisness Studies","Accounting","Managment","ICT"};

        String st=stream.getSelectionModel().getSelectedItem().toString();
        switch (st) {
            case "Science":
                for (String subject : science) {
                    subject1.getItems().add(subject);
                    subject2.getItems().add(subject);
                    subject3.getItems().add(subject);
                }
                break;
            case "Commerce":
                for (String subject : commerce) {
                    subject1.getItems().add(subject);
                    subject2.getItems().add(subject);
                    subject3.getItems().add(subject);
                }
                break;
            case "Art":
                for (String subject : arts) {
                    subject1.getItems().add(subject);
                    subject2.getItems().add(subject);
                    subject3.getItems().add(subject);
                }
                break;
            case "Technology":
                for (String subject : tech) {
                    subject1.getItems().add(subject);
                    subject2.getItems().add(subject);
                    subject3.getItems().add(subject);
                }
                break;
        }
    }

    private void setData(undergraduate student) throws SQLException {
        ArrayList<alresult> thisresult=alresult.find(student.getStudent_id());
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        nic.setText(student.getNic());
        studentid.setText(student.getStudent_id());
        firstname.setText(student.getFirstName());
        lastname.setText(student.getLastName());
        group.setUserData(student.getGender());
        dob.setValue(LocalDate.parse(student.getDob(),fomatter));
        email.setText(student.getEmail());
        address.setText(student.getAddress());
        mobile.setText(student.getMobile());
        rank.setText(Integer.toString(student.getRank()));
        stream.getSelectionModel().select(student.getStream());
        setSubjects();
        subject1.getSelectionModel().select(thisresult.get(0).getSubject_id());
        score1.setText(thisresult.get(0).getResult());
        subject2.getSelectionModel().select(thisresult.get(1).getSubject_id());
        score2.setText(thisresult.get(1).getResult());
        subject3.getSelectionModel().select(thisresult.get(2).getSubject_id());
        score3.setText(thisresult.get(2).getResult());
        regnumber.setText(student.getReg_Number());
        intake.setText(Integer.toString(student.getIntake_number()));
        regdate.setValue(LocalDate.parse(student.getRegistration_date(), fomatter));
        faculty.getSelectionModel().select(student.getFaculty());
        setCourses();
        course.getSelectionModel().select(student.getCourse_id());
    }

    public void removeSub(ActionEvent actionEvent) {

    }
}
