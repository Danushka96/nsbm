package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import java.util.ArrayList;

public class Reglect {

    public JFXButton tofirst1;
    public AnchorPane second;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton tosecond1;

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

    @FXML
    private JFXTextField studentid;

    public void initialize() throws SQLException {

        setfaculty();

        male.setToggleGroup(gender);
        male.setUserData("M");
        male.setSelected(true);
        female.setToggleGroup(gender);
        female.setUserData("F");
    }

    @FXML
    void changepane(ActionEvent event) {
        if(event.getSource()==tofirst1) first.toFront();
        else if(event.getSource()==tosecond) second.toFront();
    }

    @FXML
    void closewindow(ActionEvent event) {
        Stage thisstage= (Stage) save.getScene().getWindow();
        thisstage.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException, IOException {
        String gen="M";
        if(gender.getSelectedToggle()!=null) {
            System.out.println("hi");
            gen = gender.getSelectedToggle().getUserData().toString();
        }
        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        lecturer lec= new lecturer(nic.getText(), fac,firstname.getText(),lastname.getText(),gen,email.getText(),dob.getValue().toString(),address.getText(),mobile.getText(),regnumber.getText(),research.getText(),office.getText());
        boolean inslec= lec.save();

        if(inslec){
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

    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

}
