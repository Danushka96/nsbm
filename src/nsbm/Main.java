package nsbm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nsbm.models.*;

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
        launch(args);
        //student st1 = new student("2016cs055","Danushka","Herath","961003059V","danushkaherath96@gmail.com","1996-04-09","Alawwa","0717705526","2017-02-05",14);
        //st1.setAddress("kurunegala");
        //st1.update();
        //UniversityMemeber mem1 =new UniversityMemeber("961003059V","danushka","herath","danushkaherath96@gmail.com","1996-04-09","Alawwa","0717705526");
        //mem1.save();
        //UniversityMemeber finder = UniversityMemeber.finduser("961003059V");
        //finder.setAddress("Kurunegala");
        //System.out.println(finder.getAddress());
        //finder.update();
        //student student1=student.findstudent("961003059V");
        //System.out.println(student1.getAddress());
       // student1.setAddress("Alawwa2");
        //student1.update();
    }
}
