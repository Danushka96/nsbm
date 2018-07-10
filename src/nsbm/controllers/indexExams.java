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
import nsbm.controllers.alert.deleteAlertShow;
import nsbm.models.assignment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Danushka
 */
public class indexExams {
    @FXML
    private JFXTreeTableView<indexExams.assign> treeview;
    private static JFXTreeTableView<indexExams.assign> treeview1;

    @FXML
    private JFXButton addexam;

    public void initialize() throws SQLException {

        //ID
        JFXTreeTableColumn<indexExams.assign, String> id = new JFXTreeTableColumn<>("ID");
        id.setPrefWidth(150);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexExams.assign, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexExams.assign, String> param) {
                return param.getValue().getValue().id;
            }
        });

        //Name
        JFXTreeTableColumn<indexExams.assign, String> name = new JFXTreeTableColumn<>("Name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexExams.assign, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexExams.assign, String> param) {
                return param.getValue().getValue().name;
            }
        });

        //Marks
        JFXTreeTableColumn<indexExams.assign, String> marks = new JFXTreeTableColumn<>("Marks");
        marks.setPrefWidth(150);
        marks.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexExams.assign, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexExams.assign, String> param) {
                return param.getValue().getValue().marks;
            }
        });

        //Start_date
        JFXTreeTableColumn<indexExams.assign, String> subject = new JFXTreeTableColumn<>("Subject");
        subject.setPrefWidth(150);
        subject.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexExams.assign, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexExams.assign, String> param) {
                return param.getValue().getValue().subject_id;
            }
        });


        ObservableList<indexExams.assign> semesters= getall();

        final TreeItem<indexExams.assign> root = new RecursiveTreeItem<indexExams.assign>(semesters,RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(id,name,marks,subject);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview1=treeview;

    }

    class assign extends RecursiveTreeObject<indexExams.assign>{
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

    private ObservableList<indexExams.assign> getall() throws SQLException {
        ObservableList<indexExams.assign> assignments= FXCollections.observableArrayList();
        ArrayList<assignment> all=assignment.getallexams();
        for(assignment assginemt:all){
            assignments.add(new indexExams.assign(assginemt.getAssignment_id(),assginemt.getName(),Integer.toString(assginemt.getMarks()),assginemt.getSubject_id()));
        }
        return assignments;
    }

    static String getSelecter(){

        TreeItem<indexExams.assign> slected= treeview1.getSelectionModel().getSelectedItem();
        return slected==null?null:slected.getValue().getid();
    }

    @FXML
    void addexam(ActionEvent event) throws IOException {
        Stage thistage = (Stage) addexam.getScene().getWindow();
        thistage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/exam/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add New Exam");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void deleteExam(ActionEvent actionEvent) throws SQLException, IOException {
        deleteAlertShow alert = new deleteAlertShow();

        if(getSelecter()!=null) {
            String currentId = getSelecter();
            assignment current = assignment.findassignment(currentId);
            current.delete();
            initialize();
        }else{
            alert.showError();
        }
    }

    @FXML
    void editexam(ActionEvent event) throws IOException {
        if(getSelecter()!=null) {
            Stage thistage = (Stage) addexam.getScene().getWindow();
            thistage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/exam/edit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Exam");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/select.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error on Exam");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

}
