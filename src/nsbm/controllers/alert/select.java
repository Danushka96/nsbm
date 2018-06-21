package nsbm.controllers.alert;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * @author Danushka
 */
public class select {
    @FXML
    private JFXButton close;

    @FXML
    void close(ActionEvent event) {
        Stage thiswin=(Stage) close.getScene().getWindow();
        thiswin.close();
    }

}
