package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nsbm.models.postgraduate;
import nsbm.models.undergraduate;

import java.io.IOException;
import java.sql.SQLException;

public class delfindstudent {

    private static String student_id;

    @FXML
    private JFXTextField undergrad;

    @FXML
    private JFXTextField postgrad;

    @FXML
    private JFXButton undersearch;

    @FXML
    private JFXButton postsearch;

    public static int type=0;
    public static undergraduate under;
    public static postgraduate post;

    @FXML
    void searchpostgrad(ActionEvent event) throws IOException, SQLException {
        String typed=postgrad.getText();
        if(!typed.equals("")){
            post=postgraduate.findPostgraduate(typed);
            type=1;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/delete/deletebox.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Postgraduate Update");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void searchundergrad(ActionEvent event) throws IOException, SQLException {
        String typed=undergrad.getText();
        if(!typed.equals("")){
            under=undergraduate.findUndergraduate(typed);
            type=0;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/delete/deletebox.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Undergraduate Update");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public static String getStudent_id(){
        return student_id;
    }
    public static undergraduate getUnder(){return under;}
    public static postgraduate getPost(){return post;}
    public static int getType(){return type;}

}
