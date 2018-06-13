package nsbm.controllers;

import com.jfoenix.controls.*;
import javafx.beans.property.StringProperty;
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

public class editins {

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

        setData();
    }

    @FXML
    void closewindow(ActionEvent event) {
        Stage thisstage= (Stage) save.getScene().getWindow();
        thisstage.close();
    }

    @FXML
    void update(ActionEvent event) throws SQLException, IOException {
        String gen="M";
        if(gender.getSelectedToggle()!=null) {
            //System.out.println("hi");
            gen = gender.getSelectedToggle().getUserData().toString();
        }
        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        instructor ins= new instructor(nic.getText(),fac,firstname.getText(), lastname.getText(), gen, email.getText(), dob.getValue().toString(),address.getText(),mobile.getText(), register.getText());
        boolean insq=ins.update();

        if(insq){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Instructor Update");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin=(Stage) firstname.getScene().getWindow();
            thiswin.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Instructor Update");
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

    public void setData() throws SQLException{
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String id= indexInstructor.getSelecter();
        instructor inst=instructor.findInstructor(id);
        //System.out.println("Name: "+inst.getFirstName());
        nic.setText(inst.getNic());
        register.setText(inst.getInstructor_id());
        firstname.setText(inst.getFirstName());
        lastname.setText(inst.getLastName());
        gender.setUserData(inst.getGender());
        dob.setValue(LocalDate.parse(inst.getDob(),fomatter));
        email.setText(inst.getEmail());
        address.setText(inst.getAddress());
        faculty.getSelectionModel().select(inst.getFaculty());
        mobile.setText(inst.getMobile());
    }

}
