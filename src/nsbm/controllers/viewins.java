package nsbm.controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nsbm.models.instructor;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class viewins {

    public JFXComboBox faculty;
    public JFXTextField register;
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
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXDatePicker dob;

    @FXML
    private JFXTextField mobile;

    @FXML
    private JFXTextArea address;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton cancel1;

    @FXML
    private JFXTextField nic1;

    public void initialize() throws SQLException {

        setfaculty();
        male.setToggleGroup(gender);
        male.setUserData("M");
        male.setSelected(true);
        female.setToggleGroup(gender);
        female.setUserData("F");
//
        setData();
    }

    @FXML
    void closewindow(ActionEvent event) {
        Stage thisstage= (Stage) nic.getScene().getWindow();
        thisstage.close();
    }

    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

    public void setData() throws SQLException{
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String id= findstaff.getStaffmember();
        instructor inst=instructor.findInstructor(id);
        if(inst.getInstructor_id()!=null) {
            nic.setText(inst.getNic());
            register.setText(inst.getInstructor_id());
            firstname.setText(inst.getFirstName());
            lastname.setText(inst.getLastName());
            gender.setUserData(inst.getGender());
            dob.setValue(LocalDate.parse(inst.getDob(), fomatter));
            email.setText(inst.getEmail());
            address.setText(inst.getAddress());
            faculty.getSelectionModel().select(inst.getFaculty());
            mobile.setText(inst.getMobile());
        }
    }

}
