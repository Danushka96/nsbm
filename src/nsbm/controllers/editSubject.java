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
import nsbm.models.course;
import nsbm.models.lecturer;
import nsbm.models.subject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class editSubject {

    public JFXComboBox subtype;
    @FXML
    private JFXTextField code;

    @FXML
    private JFXComboBox course;

    @FXML
    private JFXTextField credits;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField fee;

    @FXML
    private JFXTextField hours;

    @FXML
    private JFXComboBox lecturer;

    public void initialize() throws SQLException {
        setCourses();
        setData();
    }

    @FXML
    void close(ActionEvent event) throws SQLException {
        Stage thisstage= (Stage) course.getScene().getWindow();
        thisstage.close();
    }

    @FXML
    void edit(ActionEvent event) throws SQLException, IOException {
        subject subj=new subject(code.getText(),name.getText(),Double.parseDouble(fee.getText()),Integer.parseInt(credits.getText()),lecturer.getSelectionModel().getSelectedItem().toString(),Integer.parseInt(hours.getText()),course.getSelectionModel().getSelectedItem().toString(),getType());
        boolean result=subj.update();
        if(result){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/updateSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add new Subject");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin=(Stage) lecturer.getScene().getWindow();
            thiswin.close();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/updateFailed.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add new Subject");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin=(Stage) lecturer.getScene().getWindow();
            thiswin.close();
        }
    }

    private void setCourses() throws SQLException{
        ArrayList<nsbm.models.course> all=nsbm.models.course.getall();
        for(course crse:all){
            course.getItems().add(crse.getCode());
        }
        ArrayList<nsbm.models.lecturer> alllec=nsbm.models.lecturer.getall();
        for(lecturer lect:alllec){
            lecturer.getItems().add(lect.getLecture_id());
        }
        subtype.getItems().add("compulsary");
        subtype.getItems().add("optional");
    }

    private void setData() throws SQLException{
        String subid=indexSubject.getSelecter();
        subject sub=subject.findsubject(subid);
        code.setText(sub.getCode());
        name.setText(sub.getName());
        fee.setText(sub.getFee().toString());
        credits.setText(Integer.toString(sub.getNumberofCredits()));
        hours.setText(Integer.toString(sub.getNumberofHours()));
        lecturer.getSelectionModel().select(sub.getLecturer_id());
        course.getSelectionModel().select(sub.getCourse_id());
        subtype.getSelectionModel().select(sub.getType());
    }

    private int getType(){
        if(subtype.getSelectionModel().getSelectedIndex()==1){
            return 0;
        }else{
            return 1;
        }
    }

}
