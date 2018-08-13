package nsbm.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author Danushka
 */
public class generateReport {

    @FXML
    private JFXTreeTableView<?> datatable;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField regnum;

    @FXML
    private JFXTextField intake;

    @FXML
    private JFXTextField fac;

    @FXML
    private JFXTextField sem;

    @FXML
    void sendmail(ActionEvent event) {
    }
}
