package nsbm.controllers.alert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Danushka
 */
public class deleteAlertShow {
    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/view/alertbox/deleteConfirm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Error on Deleting");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void showError() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resources/view/alertbox/select.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Error");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
