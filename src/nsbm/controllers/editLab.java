package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nsbm.models.lab;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class editLab {

    @FXML
    private JFXTextField id;

    @FXML
    private JFXComboBox faculty;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField seats;

    public void initialize() throws SQLException {
        setfaculty();
        setData();
    }

    @FXML
    void close(ActionEvent event) {
        Stage thiswin=(Stage) faculty.getScene().getWindow();
        thiswin.close();
    }

    @FXML
    void edit(ActionEvent event) throws SQLException, IOException {

        lab editlab=new lab(id.getText(),name.getText(),faculty.getSelectionModel().getSelectedItem().toString(),Integer.parseInt(seats.getText()));
        boolean result=editlab.update();
        if (result){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/updateSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Lab Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) id.getScene().getWindow();
            thiswin.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/updateFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Lab Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) id.getScene().getWindow();
            thiswin.close();
        }
    }

    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

    private void setData() throws SQLException{
        String labid=indexLab.getSelecter();
        lab found=lab.findlab(labid);
        id.setText(found.getId());
        name.setText(found.getName());
        seats.setText(Integer.toString(found.getNumberofSeats()));
        faculty.getSelectionModel().select(found.getFaculty());
    }

}
