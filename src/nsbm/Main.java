package nsbm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nsbm.models.student;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/view/newlogin.fxml"));
        primaryStage.setTitle("Login to the System");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        //launch(args);
        student st1 = new student("2016cs055","Danushka","961003059V","danushkaherat","1996-04-09","Alawwa","0717705526");
        st1.save();
    }
}
