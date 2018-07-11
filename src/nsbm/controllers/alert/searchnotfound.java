package nsbm.controllers.alert;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * @author Danushka
 */
public class searchnotfound {
    public JFXButton close;

    public void close(ActionEvent actionEvent) {
        Stage current =(Stage) close.getScene().getWindow();
        current.close();
    }
}