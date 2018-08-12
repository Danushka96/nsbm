package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nsbm.models.faculty;
import nsbm.models.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Danushka
 */
public class createUser {
    public JFXTextField username;
    public JFXPasswordField password;
    public JFXComboBox faculty1;

    public void initialize() throws SQLException{
        ArrayList<faculty> allfac = faculty.getallfaculty();
        for(faculty a:allfac){
            faculty1.getItems().add(a.getCode());
        }
    }

    public void createuser(ActionEvent actionEvent) throws SQLException, IOException {
        new user(username.getText(),password.getText(),faculty1.getSelectionModel().getSelectedItem().toString()).save();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/saveSuccess.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Save Successfull");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage thiswin = (Stage) username.getScene().getWindow();
        thiswin.close();
    }
}
