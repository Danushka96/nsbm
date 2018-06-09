package nsbm.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import nsbm.models.undergraduate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class indexUndergrad {

    public JFXTextField student_id;
    @FXML
    private JFXTreeTableView<undergrad> treeview;

    

    public void initialize() throws SQLException {

        //Student ID
        JFXTreeTableColumn<undergrad, String> student_id = new JFXTreeTableColumn<>("Student ID");
        student_id.setPrefWidth(150);
        student_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<undergrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<undergrad, String> param) {
                return param.getValue().getValue().student_id;
            }
        });

        //Name
        JFXTreeTableColumn<undergrad, String> Name = new JFXTreeTableColumn<>("Name");
        Name.setPrefWidth(150);
        Name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<undergrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<undergrad, String> param) {
                return param.getValue().getValue().Name;
            }
        });

        //nic
        JFXTreeTableColumn<undergrad, String> nic = new JFXTreeTableColumn<>("NIC");
        nic.setPrefWidth(150);
        nic.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<undergrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<undergrad, String> param) {
                return param.getValue().getValue().nic;
            }
        });

        //email
        JFXTreeTableColumn<undergrad, String> email = new JFXTreeTableColumn<>("Email");
        email.setPrefWidth(150);
        email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<undergrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<undergrad, String> param) {
                return param.getValue().getValue().email;
            }
        });

        //Faculty
        JFXTreeTableColumn<undergrad, String> faculty = new JFXTreeTableColumn<>("Faculty");
        faculty.setPrefWidth(150);
        faculty.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<undergrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<undergrad, String> param) {
                return param.getValue().getValue().faculty;
            }
        });

        //course
        JFXTreeTableColumn<undergrad, String> course = new JFXTreeTableColumn<>("Course");
        course.setPrefWidth(150);
        course.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<undergrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<undergrad, String> param) {
                return param.getValue().getValue().course_id;
            }
        });

        ObservableList<undergrad> students= getall();

        final TreeItem<undergrad> root = new RecursiveTreeItem<undergrad>(students,RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(student_id,Name,nic,email,faculty,course);
        treeview.setRoot(root);
        treeview.setShowRoot(false);

        student_id.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<undergrad>>() {
                    @Override
                    public boolean test(TreeItem<undergrad> undergradTreeItem) {
                        return undergradTreeItem.getValue().student_id.getValue().contains(newValue);
                    }
                });
            }
        });
    }

    class undergrad extends RecursiveTreeObject<undergrad>{
        StringProperty student_id,Name,nic,email,faculty,course_id;
        undergrad(String student_id, String Name, String nic, String email, String faculty, String course_id){
            this.student_id=new SimpleStringProperty(student_id);
            this.Name=new SimpleStringProperty(Name);
            this.nic=new SimpleStringProperty(nic);
            this.email=new SimpleStringProperty(email);
            this.faculty=new SimpleStringProperty(faculty);
            this.course_id=new SimpleStringProperty(course_id);
        }
    }

    private ObservableList<undergrad> getall() throws SQLException {
        ObservableList<undergrad> students= FXCollections.observableArrayList();
        ArrayList<undergraduate> all=undergraduate.getall();
        for(undergraduate student:all){
            String name=student.getFirstName()+" "+student.getLastName();
            students.add(new undergrad(student.getStudent_id(),name,student.getNic(),student.getEmail(),student.getFaculty(),student.getCourse_id()));
        }
        return students;
    }
}
