package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import nsbm.models.course;
import nsbm.models.instructor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class indexCourse {

    @FXML
    private JFXTreeTableView<crse> treeview;
    private static JFXTreeTableView<crse> treeview1;
    private static String selectedid;

    @FXML
    private JFXButton addcourse;

    public void initialize() throws SQLException {

        // Subject Code
        JFXTreeTableColumn<indexCourse.crse, String> code = new JFXTreeTableColumn<>("Subject Code");
        code.setPrefWidth(150);
        code.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexCourse.crse, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexCourse.crse, String> param) {
                return param.getValue().getValue().code;
            }
        });

        // Subject Name
        JFXTreeTableColumn<indexCourse.crse, String> name = new JFXTreeTableColumn<>("Subject Name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexCourse.crse, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexCourse.crse, String> param) {
                return param.getValue().getValue().name;
            }
        });

        // NIC
        JFXTreeTableColumn<indexCourse.crse, String> credits = new JFXTreeTableColumn<>("Credits");
        credits.setPrefWidth(150);
        credits.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexCourse.crse, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexCourse.crse, String> param) {
                return param.getValue().getValue().credits;
            }
        });

        // Contact
        JFXTreeTableColumn<indexCourse.crse, String> years = new JFXTreeTableColumn<>("years");
        years.setPrefWidth(150);
        years.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexCourse.crse, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexCourse.crse, String> param) {
                return param.getValue().getValue().numberofyears;
            }
        });

        JFXTreeTableColumn<indexCourse.crse, String> faculty = new JFXTreeTableColumn<>("Faculty");
        faculty.setPrefWidth(150);
        faculty.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexCourse.crse, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexCourse.crse, String> param) {
                return param.getValue().getValue().faculty;
            }
        });

        ObservableList<indexCourse.crse> lecturers= getall();
        final TreeItem<indexCourse.crse> root = new RecursiveTreeItem<indexCourse.crse>(lecturers, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(code,name,credits,years,faculty);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview1=treeview;
    }

    class crse extends RecursiveTreeObject<indexCourse.crse> {
        StringProperty code,name, credits, numberofyears, faculty;
        String sub_code;

        crse(String code,String name, String credits, String numberofyears, String faculty) {
            this.sub_code=code;
            this.code=new SimpleStringProperty(code);
            this.name = new SimpleStringProperty(name);
            this.credits = new SimpleStringProperty(credits);
            this.numberofyears = new SimpleStringProperty(numberofyears);
            this.faculty=new SimpleStringProperty(faculty);
        }
        String getid(){
            //System.out.println(this.id.toString());
            return this.sub_code;
        }

    }

    private ObservableList<indexCourse.crse> getall() throws SQLException {
        ObservableList<indexCourse.crse> courses= FXCollections.observableArrayList();
        ArrayList<course> all= course.getall();
        for(course cour:all){
            courses.add(new indexCourse.crse(cour.getCode(),cour.getName(), Integer.toString(cour.getCredits()), Integer.toString(cour.getNumberofyears()), cour.getFaculty()));
        }
        return courses;
    }

    static String getSelecter(){

        TreeItem<indexCourse.crse> slected= treeview1.getSelectionModel().getSelectedItem();
        //System.out.println(slected.getValue().getid());
        return slected==null?null:slected.getValue().getid();
    }


    @FXML
    void addcourse(ActionEvent event) throws IOException {
        Stage thistage = (Stage) addcourse.getScene().getWindow();
        thistage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/course/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Index Courses");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void editcourse(ActionEvent event) throws IOException {
        if(getSelecter()!=null) {
            Stage thistage = (Stage) addcourse.getScene().getWindow();
            thistage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/course/edit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Courses");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/select.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error on Course");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }
}
