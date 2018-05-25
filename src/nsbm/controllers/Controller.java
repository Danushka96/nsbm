package nsbm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.sql.*;

public class Controller {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Text error;

    private Connection con;
    private Statement stmnt;
    private ResultSet result;


    public void login(ActionEvent actionEvent) throws SQLException {
        con = ConnectionManager.getConnection();
        stmnt = con.createStatement();
        int resultCount=0;
        String query = "SELECT * from users where username=? AND password=? ";
        PreparedStatement stat = con.prepareStatement(query);
        stat.setString(1,username.getText());
        stat.setString(2,password.getText());
        result=stat.executeQuery();
        while (result.next()){
            resultCount++;
        }
        if(resultCount>0){
            System.out.println("login Success");
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
