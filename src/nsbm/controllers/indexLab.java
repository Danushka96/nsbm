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
import nsbm.models.lab;
import nsbm.models.timeslot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class indexLab {

    @FXML
    private JFXTreeTableView<labc> treeview;
    private static JFXTreeTableView<labc> treeview1;

    @FXML
    private JFXButton addsubject;

    public void initialize() throws SQLException {

        // LabID
        JFXTreeTableColumn<indexLab.labc, String> id = new JFXTreeTableColumn<>("Lab ID");
        id.setPrefWidth(150);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexLab.labc, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexLab.labc, String> param) {
                return param.getValue().getValue().id;
            }
        });

        // Name
        JFXTreeTableColumn<indexLab.labc, String> name = new JFXTreeTableColumn<>("Name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexLab.labc, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexLab.labc, String> param) {
                return param.getValue().getValue().name;
            }
        });

        // Seats
        JFXTreeTableColumn<indexLab.labc, String> seats = new JFXTreeTableColumn<>("Seats");
        seats.setPrefWidth(150);
        seats.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexLab.labc, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexLab.labc, String> param) {
                return param.getValue().getValue().seats;
            }
        });

        // Faculty
        JFXTreeTableColumn<indexLab.labc, String> faculty = new JFXTreeTableColumn<>("Faculty");
        faculty.setPrefWidth(150);
        faculty.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexLab.labc, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexLab.labc, String> param) {
                return param.getValue().getValue().faculty;
            }
        });

        ObservableList<indexLab.labc> labs= getall();
        final TreeItem<indexLab.labc> root = new RecursiveTreeItem<indexLab.labc>(labs, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(id,name,seats,faculty);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview1=treeview;
    }

    class labc extends RecursiveTreeObject<indexLab.labc> {
        StringProperty id,name, seats, faculty;
        String labid;

        labc(String id, String name, String seats, String faculty) {
            this.labid=id;
            this.name=new SimpleStringProperty(name);
            this.seats=new SimpleStringProperty(seats);
            this.faculty=new SimpleStringProperty(faculty);
        }
        String getid(){
            //System.out.println(this.id.toString());
            return this.labid;
        }
    }

    private ObservableList<indexLab.labc> getall() throws SQLException {
        ObservableList<indexLab.labc> labdata= FXCollections.observableArrayList();
        ArrayList<lab> all= lab.getall();
        for(lab lab:all){
            labdata.add(new indexLab.labc(lab.getId(),lab.getName(),Integer.toString(lab.getNumberofSeats()), lab.getFaculty()));
        }
        return labdata;
    }

    static String getSelecter(){

        TreeItem<indexLab.labc> slected= treeview1.getSelectionModel().getSelectedItem();
        return slected==null?null:slected.getValue().getid();
    }

    @FXML
    void addlab(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/lab/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Lab Registration");
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        Stage thiswin = (Stage) addsubject.getScene().getWindow();
        thiswin.close();
    }

    @FXML
    void editlab(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/lab/edit.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Lab Registration");
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        Stage thiswin = (Stage) addsubject.getScene().getWindow();
        thiswin.close();
    }

}