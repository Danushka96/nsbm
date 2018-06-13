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
import nsbm.models.lecturer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class viewlect {

    @FXML
    private AnchorPane second;

    @FXML
    private JFXButton update;

    @FXML
    private JFXButton tofirst1;

    @FXML
    private JFXComboBox faculty;

    @FXML
    private JFXButton cancel3;

    @FXML
    private JFXTextField regnumber;

    @FXML
    private JFXTextField office;

    @FXML
    private JFXTextArea research;

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
    private JFXButton tosecond;

    @FXML
    private JFXButton cancel1;

    public void initialize() throws SQLException {

        setfaculty();

        male.setToggleGroup(gender);
        male.setUserData("M");
        male.setSelected(true);
        female.setToggleGroup(gender);
        female.setUserData("F");
        setData();
    }

    @FXML
    void changepane(ActionEvent event) {
        if(event.getSource()==tofirst1) first.toFront();
        else if(event.getSource()==tosecond) second.toFront();
    }

    @FXML
    void closewindow(ActionEvent event) {
        Stage thisstage= (Stage) nic.getScene().getWindow();
        thisstage.close();
    }

    @FXML

    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

    private void setData() throws SQLException{
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String id = findstaff.getStaffmember();
        lecturer lect=lecturer.findLecturer(id);
//
        if(lect.getLecture_id()!=null) {
            nic.setText(lect.getNic());
            firstname.setText(lect.getFirstName());
            lastname.setText(lect.getLastName());
            gender.setUserData(lect.getGender());
            dob.setValue(LocalDate.parse(lect.getDob(), fomatter));
            email.setText(lect.getEmail());
            address.setText(lect.getAddress());
            mobile.setText(lect.getMobile());
            regnumber.setText(lect.getLecture_id());
            faculty.getSelectionModel().select(lect.getFaculty());
            office.setText(lect.getOffice_number());
            research.setText(lect.getResearchSpeciality());
        }

    }

}
