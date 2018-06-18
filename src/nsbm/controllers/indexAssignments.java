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
import javafx.stage.Stage;
import javafx.util.Callback;
import nsbm.models.assignment;
import nsbm.models.semester;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class indexAssignments {

    @FXML
    private JFXTreeTableView<assign> treeview;
    private static JFXTreeTableView<assign> treeview1;

    @FXML
    private JFXButton addassignment;

    public void initialize() throws SQLException {

        //ID
        JFXTreeTableColumn<indexAssignments.assign, String> id = new JFXTreeTableColumn<>("ID");
        id.setPrefWidth(150);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexAssignments.assign, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexAssignments.assign, String> param) {
                return param.getValue().getValue().id;
            }
        });

        //Name
        JFXTreeTableColumn<indexAssignments.assign, String> name = new JFXTreeTableColumn<>("Name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexAssignments.assign, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexAssignments.assign, String> param) {
                return param.getValue().getValue().name;
            }
        });

        //Marks
        JFXTreeTableColumn<indexAssignments.assign, String> marks = new JFXTreeTableColumn<>("Marks");
        marks.setPrefWidth(150);
        marks.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexAssignments.assign, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexAssignments.assign, String> param) {
                return param.getValue().getValue().marks;
            }
        });

        //Start_date
        JFXTreeTableColumn<indexAssignments.assign, String> subject = new JFXTreeTableColumn<>("Subject");
        subject.setPrefWidth(150);
        subject.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexAssignments.assign, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexAssignments.assign, String> param) {
                return param.getValue().getValue().subject_id;
            }
        });


        ObservableList<indexAssignments.assign> semesters= getall();

        final TreeItem<indexAssignments.assign> root = new RecursiveTreeItem<indexAssignments.assign>(semesters,RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(id,name,marks,subject);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview1=treeview;

    }

    class assign extends RecursiveTreeObject<indexAssignments.assign>{
        StringProperty id,name,marks,subject_id;
        String assignment_id;
        assign(String id, String name, String marks, String subject_id){
            this.assignment_id=id;
            this.id=new SimpleStringProperty(id);
            this.name=new SimpleStringProperty(name);
            this.marks=new SimpleStringProperty(marks);
            this.subject_id=new SimpleStringProperty(subject_id);
        }
        String getid(){
            //System.out.println(this.id.toString());
            return this.assignment_id;
        }

    }

    private ObservableList<indexAssignments.assign> getall() throws SQLException {
        ObservableList<indexAssignments.assign> assignments= FXCollections.observableArrayList();
        ArrayList<assignment> all=assignment.getall();
        for(assignment assginemt:all){
            assignments.add(new indexAssignments.assign(assginemt.getAssignment_id(),assginemt.getName(),Integer.toString(assginemt.getMarks()),assginemt.getSubject_id()));
        }
        return assignments;
    }

    static String getSelecter(){

        TreeItem<indexAssignments.assign> slected= treeview1.getSelectionModel().getSelectedItem();
        return slected==null?null:slected.getValue().getid();
    }

    @FXML
    void addassignment(ActionEvent event) throws IOException {
        Stage thistage = (Stage) addassignment.getScene().getWindow();
        thistage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/assignment/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add New Assignment");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void editassignment(ActionEvent event) throws IOException {
        Stage thistage = (Stage) addassignment.getScene().getWindow();
        thistage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/assignment/edit.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Edit Assignment");
        stage.setScene(new Scene(root1));
        stage.show();
    }

}