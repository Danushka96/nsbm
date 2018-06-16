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
import nsbm.models.subject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class indexSubject {

    @FXML
    private JFXTreeTableView<sbject> treeview;
    private static JFXTreeTableView<sbject> treeview1;

    @FXML
    private JFXButton addsubject;

    public void initialize() throws SQLException {

        // Subject Code
        JFXTreeTableColumn<indexSubject.sbject, String> code = new JFXTreeTableColumn<>("Subject Code");
        code.setPrefWidth(150);
        code.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSubject.sbject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSubject.sbject, String> param) {
                return param.getValue().getValue().code;
            }
        });

        // Subject Name
        JFXTreeTableColumn<indexSubject.sbject, String> name = new JFXTreeTableColumn<>("Subject Name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSubject.sbject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSubject.sbject, String> param) {
                return param.getValue().getValue().name;
            }
        });

        // Fee
        JFXTreeTableColumn<indexSubject.sbject, String> fee = new JFXTreeTableColumn<>("Fee");
        fee.setPrefWidth(150);
        fee.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSubject.sbject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSubject.sbject, String> param) {
                return param.getValue().getValue().fee;
            }
        });

        // Credits
        JFXTreeTableColumn<indexSubject.sbject, String> credits = new JFXTreeTableColumn<>("Credits");
        credits.setPrefWidth(150);
        credits.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSubject.sbject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSubject.sbject, String> param) {
                return param.getValue().getValue().numberofcredits;
            }
        });

        // Course_id
        JFXTreeTableColumn<indexSubject.sbject, String> course = new JFXTreeTableColumn<>("Course");
        course.setPrefWidth(150);
        course.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSubject.sbject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSubject.sbject, String> param) {
                return param.getValue().getValue().courseid;
            }
        });

        // Lecturer
        JFXTreeTableColumn<indexSubject.sbject, String> lecturer = new JFXTreeTableColumn<>("Lecturer");
        lecturer.setPrefWidth(150);
        lecturer.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSubject.sbject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSubject.sbject, String> param) {
                return param.getValue().getValue().lecturer;
            }
        });

        //Hours
        JFXTreeTableColumn<indexSubject.sbject, String> hours = new JFXTreeTableColumn<>("Hours");
        hours.setPrefWidth(150);
        hours.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSubject.sbject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSubject.sbject, String> param) {
                return param.getValue().getValue().hours;
            }
        });

        ObservableList<indexSubject.sbject> subjects= getall();
        final TreeItem<indexSubject.sbject> root = new RecursiveTreeItem<indexSubject.sbject>(subjects, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(code,name,fee,credits,course,lecturer,hours);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview1=treeview;
    }

    class sbject extends RecursiveTreeObject<indexSubject.sbject> {
        StringProperty code,name, fee, numberofcredits, courseid, lecturer, hours;
        String sub_code;

        sbject(String code, String name, String fee, String numberofcredits, String course_id, String lecturer, int hours) {
            this.sub_code=code;
            this.lecturer=new SimpleStringProperty(lecturer);
            this.hours=new SimpleStringProperty(Integer.toString(hours));
            this.code=new SimpleStringProperty(code);
            this.name = new SimpleStringProperty(name);
            this.fee = new SimpleStringProperty(fee);
            this.numberofcredits = new SimpleStringProperty(numberofcredits);
            this.courseid=new SimpleStringProperty(course_id);
        }
        String getid(){
            //System.out.println(this.id.toString());
            return this.sub_code;
        }

    }

    private ObservableList<indexSubject.sbject> getall() throws SQLException {
        ObservableList<indexSubject.sbject> subjects= FXCollections.observableArrayList();
        ArrayList<subject> all= subject.getall();
        for(subject sub:all){
            subjects.add(new indexSubject.sbject(sub.getCode(),sub.getName(), Double.toString(sub.getFee()), Integer.toString(sub.getNumberofCredits()), sub.getCourse_id(),sub.getLecturer_id(),sub.getNumberofHours()));
        }
        return subjects;
    }

    static String getSelecter(){

        TreeItem<indexSubject.sbject> slected= treeview1.getSelectionModel().getSelectedItem();
        return slected==null?null:slected.getValue().getid();
    }

    @FXML
    void addsubject(ActionEvent event) throws IOException {
        Stage thistage = (Stage) addsubject.getScene().getWindow();
        thistage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/subject/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add New Subject");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void editsubject(ActionEvent event) throws IOException {
        if(getSelecter()!=null) {
            Stage thistage = (Stage) addsubject.getScene().getWindow();
            thistage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/subject/edit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New Subject");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

}
