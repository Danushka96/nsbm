package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;

public class mainInterface {

    @FXML
    private AnchorPane menuuni;

    @FXML
    private AnchorPane menufaculty;

    @FXML
    private AnchorPane menustaff;

    @FXML
    private AnchorPane menuhome;

    @FXML
    private AnchorPane menustudent;

    @FXML
    private JFXButton btnhome;

    @FXML
    private JFXButton btnstudent;

    @FXML
    private JFXButton btnstaff;

    @FXML
    private JFXButton btnfaculty;

    @FXML
    private JFXButton btnuniversity;

    @FXML
    private JFXButton btnusers;

    @FXML
    private JFXButton btnlogout;

    @FXML
    public void handleButtonAction(javafx.event.ActionEvent actionEvent) {
        if(actionEvent.getSource()==btnhome){
            menuhome.toFront();
        }
        else if(actionEvent.getSource()==btnstudent){
            menustudent.toFront();
        }
        else if(actionEvent.getSource()==btnstaff){
            menustaff.toFront();
        }
        else if(actionEvent.getSource()==btnfaculty){
            menufaculty.toFront();
        }
        else if(actionEvent.getSource()==btnuniversity){
            menuuni.toFront();
        }
        else if(actionEvent.getSource()==btnusers){
            System.out.println("Users Menu");
        }
        else if(actionEvent.getSource()==btnlogout){
            System.out.println("Logout Command");
        }
    }
}
