package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
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
import nsbm.models.semester;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class indexSemester {

    @FXML
    private JFXTreeTableView<sem> treeview;
    private static JFXTreeTableView<sem> treeview1;

    @FXML
    private JFXButton addsubject;

    public void initialize() throws SQLException {

        //Faculty
        JFXTreeTableColumn<indexSemester.sem, String> faculty = new JFXTreeTableColumn<>("Faculty");
        faculty.setPrefWidth(150);
        faculty.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSemester.sem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSemester.sem, String> param) {
                return param.getValue().getValue().faculty;
            }
        });

        //SemNumber
        JFXTreeTableColumn<indexSemester.sem, String> semnumber = new JFXTreeTableColumn<>("Semester");
        semnumber.setPrefWidth(150);
        semnumber.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSemester.sem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSemester.sem, String> param) {
                return param.getValue().getValue().sem_number;
            }
        });

        //year
        JFXTreeTableColumn<indexSemester.sem, String> year = new JFXTreeTableColumn<>("Year");
        year.setPrefWidth(150);
        year.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSemester.sem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSemester.sem, String> param) {
                return param.getValue().getValue().year;
            }
        });

        //Start_date
        JFXTreeTableColumn<indexSemester.sem, String> start = new JFXTreeTableColumn<>("Start Date");
        start.setPrefWidth(150);
        start.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSemester.sem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSemester.sem, String> param) {
                return param.getValue().getValue().start_date;
            }
        });

        //End_Date
        JFXTreeTableColumn<indexSemester.sem, String> end = new JFXTreeTableColumn<>("End Date");
        end.setPrefWidth(150);
        end.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexSemester.sem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexSemester.sem, String> param) {
                return param.getValue().getValue().end_date;
            }
        });

        ObservableList<indexSemester.sem> semesters= getall();

        final TreeItem<indexSemester.sem> root = new RecursiveTreeItem<indexSemester.sem>(semesters,RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(faculty,semnumber,year,start,end);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview1=treeview;

    }

    class sem extends RecursiveTreeObject<indexSemester.sem>{
        StringProperty id,faculty,sem_number,year,start_date,end_date;
        String sem_id;
        sem(String id, String faculty, String sem_number, String year, String start_date, String end_date){
            this.sem_id=id;
            this.id=new SimpleStringProperty(id);
            this.faculty=new SimpleStringProperty(faculty);
            this.sem_number=new SimpleStringProperty(sem_number);
            this.year=new SimpleStringProperty(year);
            this.start_date=new SimpleStringProperty(start_date);
            this.end_date=new SimpleStringProperty(end_date);
        }
        String getid(){
            //System.out.println(this.id.toString());
            return this.sem_id;
        }

    }

    private ObservableList<indexSemester.sem> getall() throws SQLException {
        ObservableList<indexSemester.sem> semesters= FXCollections.observableArrayList();
        ArrayList<semester> all=semester.getall();
        for(semester semes:all){
            semesters.add(new indexSemester.sem(semes.getSemester_id(),semes.getFaculty(),semes.getSemester_number(),semes.getYear() ,semes.getStart_date(),semes.getEnd_date()));
        }
        return semesters;
    }

    static String getSelecter(){

        TreeItem<indexSemester.sem> slected= treeview1.getSelectionModel().getSelectedItem();
        return slected==null?null:slected.getValue().getid();
    }

    @FXML
    void addsemester(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/semester/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Semester Registration");
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        Stage thiswin=(Stage) addsubject.getScene().getWindow();
        thiswin.close();
    }

    public void deleteSemester(ActionEvent actionEvent) throws SQLException, IOException {
        deleteAlertShow alert = new deleteAlertShow();

        if(getSelecter()!=null) {
            String currentId = getSelecter();
            semester current = semester.findsemester(currentId);
            current.delete();
            initialize();
        }else{
            alert.showError();
        }
    }

    @FXML
    void editsemester(ActionEvent event) throws IOException {
        if (getSelecter()!=null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/semester/edit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Semester Registration");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            Stage thiswin = (Stage) addsubject.getScene().getWindow();
            thiswin.close();
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/select.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error on Edit Semester");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

}
