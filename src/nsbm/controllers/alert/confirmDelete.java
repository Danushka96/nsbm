package nsbm.controllers.alert;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * @author Danushka
 */
public class confirmDelete {

    private static boolean confirmed = false;

    @FXML
    private JFXButton btnok;

    @FXML
    void close(ActionEvent event) {
        Stage current = (Stage) btnok.getScene().getWindow();
        current.close();
    }

    @FXML
    void delete(ActionEvent event) {
        confirmed = true;
        Stage current = (Stage) btnok.getScene().getWindow();
        current.close();
    }

    public static boolean getResult(){
        return confirmed;
    }

}
