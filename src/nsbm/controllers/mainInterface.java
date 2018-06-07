package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

public class mainInterface {

    @FXML
    public Text systemtime;

    @FXML
    public Label studentcount;

    @FXML
    public Label lecturecount;

    @FXML
    public JFXButton regpost;

    @FXML
    public JFXButton regunder;

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

    public void initialize() throws SQLException {
        settime();
        setDashboard();
    }

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

    private void settime(){
//        Thread.sleep(1000);
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        String time=sdf.format(cal.getTime());
        systemtime.setText(time);
    }
    private void setDashboard() throws SQLException {
        int students=0,lecturers=0;
        Connection con = ConnectionManager.getConnection();
        String squery="SELECT * FROM students";
        String lquery="SELECT * FROM lecturers";
        Statement sq=con.prepareStatement(squery);
        Statement lq=con.prepareStatement(lquery);
        ResultSet rlq=((PreparedStatement) lq).executeQuery();
        ResultSet rsq=((PreparedStatement) sq).executeQuery();
        while (rsq.next()){
            students++;
        }
        while (rlq.next()){
            lecturers++;
        }
        studentcount.setText(Integer.toString(students));
        lecturecount.setText(Integer.toString(lecturers));
    }

    public void regunder(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/regundergrad.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Undergraduate Registration");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void regpost(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/regpostgrad.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Postgraduate Registration");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
