package nsbm.controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import nsbm.models.postgraduate;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class editpostgrad {

    public JFXTextField qualification;
    public JFXTextField year;
    public JFXTextField institute;
    @FXML
    private AnchorPane third;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton tosecond1;

    @FXML
    private JFXDatePicker regdate;

    @FXML
    private JFXTextField intake;

    @FXML
    private JFXComboBox course;

    @FXML
    private JFXComboBox faculty;

    @FXML
    private JFXButton cancel3;

    @FXML
    private JFXTextField regnumber;

    @FXML
    private AnchorPane second;

    @FXML
    private JFXTextField alindex;

    @FXML
    private JFXTextField zscore;

    @FXML
    private JFXTextField rank;

    @FXML
    private JFXButton tothird;

    @FXML
    private JFXButton tofirst;

    @FXML
    private JFXButton cancel2;

    @FXML
    private AnchorPane first;

    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField nic;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXDatePicker dob;

    @FXML
    private JFXTextField mobile;

    @FXML
    private JFXTextArea address;

    @FXML
    private JFXButton tosecond;

    @FXML
    private JFXButton cancel1;

    @FXML
    private JFXTextField studentid;


    private ToggleGroup group=new ToggleGroup();
    private String student_id=nsbm.controllers.findstudent.getStudent_id();

    public void initialize() throws SQLException {

        setfaculty();
        male.setToggleGroup(group);
        male.setUserData("M");
        male.setSelected(true);
        female.setToggleGroup(group);
        female.setUserData("F");

        postgraduate stu=postgraduate.findPostgraduate(student_id);
        setdata(stu);
    }


    @FXML
    void changepane(ActionEvent event) {
        if(event.getSource()==tofirst) first.toFront();
        else if(event.getSource()==tosecond) second.toFront();
        else if(event.getSource()==tothird) third.toFront();
        else if(event.getSource()==tosecond1) second.toFront();
    }

    @FXML
    void closewindow(ActionEvent event) {
        Stage thisstage= (Stage) save.getScene().getWindow();
        thisstage.close();
    }

    @FXML
    void update(ActionEvent event) throws SQLException, IOException {
        int intakenum=Integer.parseInt(intake.getText());
        String gender="M";
        if(group.getSelectedToggle()!=null) {
            System.out.println("hi");
            gender = group.getSelectedToggle().getUserData().toString();
        }
        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        String courseid=course.getSelectionModel().getSelectedItem().toString();
        postgraduate postgrad=new postgraduate(
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
                qualification.getText(),
                institute.getText(),
                year.getText(),
                courseid
        );
        boolean insresult=postgrad.update();

//        Alert Box
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Student Registration");
        if(insresult){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Postgraduate Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin=(Stage) firstname.getScene().getWindow();
            thiswin.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Postgraduate Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        }
    }

    @FXML
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

    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

    private void setdata(postgraduate student) throws SQLException {
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
        qualification.setText(student.getQualification_type());
        institute.setText(student.getInstitiue());
        year.setText(student.getYearofcompletion());
        regnumber.setText(student.getReg_Number());
        intake.setText(Integer.toString(student.getIntake_number()));
        regdate.setValue(LocalDate.parse(student.getRegistration_date(), fomatter));
        faculty.getSelectionModel().select(student.getFaculty());
        setCourses();
        course.getSelectionModel().select(student.getCourse_id());
    }
}
