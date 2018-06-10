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
import nsbm.models.postgraduate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class indexPostgrad {

    public JFXTextField student_id;
    @FXML
    private JFXTreeTableView<postgrad> treeview;



    public void initialize() throws SQLException {

        //Student ID
        JFXTreeTableColumn<postgrad, String> student_id = new JFXTreeTableColumn<>("Student ID");
        student_id.setPrefWidth(150);
        student_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<postgrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<postgrad, String> param) {
                return param.getValue().getValue().student_id;
            }
        });

        //Name
        JFXTreeTableColumn<postgrad, String> Name = new JFXTreeTableColumn<>("Name");
        Name.setPrefWidth(150);
        Name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<postgrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<postgrad, String> param) {
                return param.getValue().getValue().Name;
            }
        });

        //nic
        JFXTreeTableColumn<postgrad, String> nic = new JFXTreeTableColumn<>("NIC");
        nic.setPrefWidth(150);
        nic.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<postgrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<postgrad, String> param) {
                return param.getValue().getValue().nic;
            }
        });

        //email
        JFXTreeTableColumn<postgrad, String> email = new JFXTreeTableColumn<>("Email");
        email.setPrefWidth(150);
        email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<postgrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<postgrad, String> param) {
                return param.getValue().getValue().email;
            }
        });

        //Faculty
        JFXTreeTableColumn<postgrad, String> faculty = new JFXTreeTableColumn<>("Faculty");
        faculty.setPrefWidth(150);
        faculty.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<postgrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<postgrad, String> param) {
                return param.getValue().getValue().faculty;
            }
        });

        //course
        JFXTreeTableColumn<postgrad, String> course = new JFXTreeTableColumn<>("Course");
        course.setPrefWidth(150);
        course.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<postgrad, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<postgrad, String> param) {
                return param.getValue().getValue().course_id;
            }
        });

        ObservableList<postgrad> students= getall();

        final TreeItem<postgrad> root = new RecursiveTreeItem<postgrad>(students,RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(student_id,Name,nic,email,faculty,course);
        treeview.setRoot(root);
        treeview.setShowRoot(false);

        student_id.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<postgrad>>() {
                    @Override
                    public boolean test(TreeItem<postgrad> undergradTreeItem) {
                        return undergradTreeItem.getValue().student_id.getValue().contains(newValue);
                    }
                });
            }
        });
    }

    class postgrad extends RecursiveTreeObject<postgrad>{
        StringProperty student_id,Name,nic,email,faculty,course_id;
        postgrad(String student_id, String Name, String nic, String email, String faculty, String course_id){
            this.student_id=new SimpleStringProperty(student_id);
            this.Name=new SimpleStringProperty(Name);
            this.nic=new SimpleStringProperty(nic);
            this.email=new SimpleStringProperty(email);
            this.faculty=new SimpleStringProperty(faculty);
            this.course_id=new SimpleStringProperty(course_id);
        }
    }

    private ObservableList<postgrad> getall() throws SQLException {
        ObservableList<postgrad> students= FXCollections.observableArrayList();
        ArrayList<postgraduate> all=postgraduate.getall();
        for(postgraduate student:all){
            String name=student.getFirstName()+" "+student.getLastName();
            students.add(new postgrad(student.getStudent_id(),name,student.getNic(),student.getEmail(),student.getFaculty(),student.getCourse_id()));
        }
        return students;
    }
}
