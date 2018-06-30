package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

/**
 * @author Danushka
 */
public class enrollCreate {

    public Text label2;
    public Text label3;
    public Text label4;
    @FXML
    private JFXComboBox sub1;

    @FXML
    private JFXComboBox sub2;

    @FXML
    private JFXComboBox sub3;

    @FXML
    private JFXComboBox sub4;

    @FXML
    private Text credits;

    @FXML
    private TextField credit1;

    @FXML
    private TextField credit2;

    @FXML
    private TextField credit3;

    @FXML
    private TextField credit4;

    private String student1;
    private String semester1;

    @FXML
    public void initialize() throws SQLException{
        student1=enrollSelect.getStudent();
        semester1=enrollSelect.getSemester();
    }

    @FXML
    void save(ActionEvent event) {
    }


    public void checkCredits(ActionEvent actionEvent) {
    }
}
