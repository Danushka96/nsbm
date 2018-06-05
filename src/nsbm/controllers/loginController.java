package nsbm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.*;

public class loginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text error;


    public void login(ActionEvent actionEvent) throws SQLException, IOException {
        Connection con = ConnectionManager.getConnection();
        Statement stmnt = con.createStatement();
        int resultCount=0;
        String query = "SELECT * from users where username=? AND password=? ";
        PreparedStatement stat = con.prepareStatement(query);
        stat.setString(1,username.getText());
        stat.setString(2,password.getText());
        ResultSet result = stat.executeQuery();
        while (result.next()){
            resultCount++;
        }
        if(resultCount>0){
            Stage login=(Stage)username.getScene().getWindow();
            login.close();
            System.out.println("login Success");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/mainInterface.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            root1.getStylesheets().add(getClass().getResource("../resources/css/button.css").toExternalForm());
            Stage stage = new Stage();
            //login.setRoot(root1);
            stage.setTitle("Main Interface");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
//            System.out.println("Login Failed");
            password.clear();
            username.clear();
            error.setVisible(true);
        }
    }
    public void errorDel(KeyEvent mouseEvent) {
        error.setVisible(false);
    }
}
