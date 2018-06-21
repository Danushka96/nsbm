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
import jdk.nashorn.api.tree.Tree;
import nsbm.models.lecturer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class indexLecturer {

    @FXML
    private JFXTreeTableView<lect> treeview;
    private static JFXTreeTableView<lect> treeview1;

    @FXML
    private JFXButton editlecturer;

    public void initialize() throws SQLException {

        // Lecturer Name
        JFXTreeTableColumn<indexLecturer.lect, String> name = new JFXTreeTableColumn<>("Lecturer Name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexLecturer.lect, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<lect, String> param) {
                return param.getValue().getValue().name;
            }
        });

        // NIC
        JFXTreeTableColumn<indexLecturer.lect, String> nic = new JFXTreeTableColumn<>("NIC");
        nic.setPrefWidth(150);
        nic.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexLecturer.lect, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<lect, String> param) {
                return param.getValue().getValue().nic;
            }
        });

        // Office
        JFXTreeTableColumn<indexLecturer.lect, String> office = new JFXTreeTableColumn<>("Office");
        office.setPrefWidth(150);
        office.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexLecturer.lect, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<lect, String> param) {
                return param.getValue().getValue().office;
            }
        });

        // Faculty
        JFXTreeTableColumn<indexLecturer.lect, String> faculty = new JFXTreeTableColumn<>("Faculty");
        faculty.setPrefWidth(150);
        faculty.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexLecturer.lect, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<lect, String> param) {
                return param.getValue().getValue().faculty;
            }
        });

        // ResearchSpeciality
        JFXTreeTableColumn<indexLecturer.lect, String> Research = new JFXTreeTableColumn<>("Research");
        Research.setPrefWidth(150);
        Research.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexLecturer.lect, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<lect, String> param) {
                return param.getValue().getValue().researchSpeciality;
            }
        });

        ObservableList<lect> lecturers= getall();
        final TreeItem<lect> root = new RecursiveTreeItem<lect>(lecturers, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(name,nic,office,faculty,Research);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview1 = treeview;
    }


    class lect extends RecursiveTreeObject<indexLecturer.lect> {
        StringProperty name, nic, office, faculty, researchSpeciality;
        String id;

        lect(String id, String name, String nic, String office, String faculty, String researchSpeciality) {
            this.id=id;
            this.name = new SimpleStringProperty(name);
            this.nic = new SimpleStringProperty(nic);
            this.office = new SimpleStringProperty(office);
            this.faculty = new SimpleStringProperty(faculty);
            this.researchSpeciality = new SimpleStringProperty(researchSpeciality);
        }
        String getId(){ return this.id; }
    }

    private ObservableList<indexLecturer.lect> getall() throws SQLException {
        ObservableList<lect> lecturers= FXCollections.observableArrayList();
        ArrayList<lecturer> all= lecturer.getall();
        for(lecturer lectr:all){
            String name=lectr.getFirstName()+" "+lectr.getLastName();
            lecturers.add(new lect(lectr.getLecture_id(),name, lectr.getNic(), lectr.getOffice_number(), lectr.getFaculty(), lectr.getResearchSpeciality()));
        }
        return lecturers;
    }

    @FXML
    void editlecturer(ActionEvent event) throws IOException {
        if (getSelecter()!=null) {
            Stage button = (Stage) editlecturer.getScene().getWindow();
            button.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/lecturer/edit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Lecturer");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/select.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error on Lecturer");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    public static String getSelecter(){
        TreeItem<lect> slected= treeview1.getSelectionModel().getSelectedItem();
        return slected==null?null:slected.getValue().getId();
    }

}