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
import nsbm.models.instructor;
import nsbm.models.lecturer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;



public class indexInstructor {

    private static String selected;
    @FXML
    private JFXTreeTableView<ins> treeview;
    private static JFXTreeTableView<ins> treeview1;
    @FXML
    private JFXButton editins;

    public void initialize() throws SQLException {

        // Lecturer Name
        JFXTreeTableColumn<indexInstructor.ins, String> name = new JFXTreeTableColumn<>("Instructor Name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexInstructor.ins, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ins, String> param) {
                return param.getValue().getValue().name;
            }
        });

        // NIC
        JFXTreeTableColumn<indexInstructor.ins, String> nic = new JFXTreeTableColumn<>("NIC");
        nic.setPrefWidth(150);
        nic.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexInstructor.ins, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ins, String> param) {
                return param.getValue().getValue().nic;
            }
        });

        // Contact
        JFXTreeTableColumn<indexInstructor.ins, String> contact = new JFXTreeTableColumn<>("Contact");
        contact.setPrefWidth(150);
        contact.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexInstructor.ins, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ins, String> param) {
                return param.getValue().getValue().contact;
            }
        });

        JFXTreeTableColumn<indexInstructor.ins, String> faculty = new JFXTreeTableColumn<>("Faculty");
        faculty.setPrefWidth(150);
        faculty.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexInstructor.ins, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ins, String> param) {
                return param.getValue().getValue().faculty;
            }
        });

        ObservableList<ins> lecturers= getall();
        final TreeItem<ins> root = new RecursiveTreeItem<ins>(lecturers, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(name,nic,contact,faculty);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview1=treeview;
    }

    class ins extends RecursiveTreeObject<indexInstructor.ins> {
        StringProperty name, nic, contact, faculty;
        String id;

        ins(String id,String name, String nic, String contact, String faculty) {
            this.id=id;
            this.name = new SimpleStringProperty(name);
            this.nic = new SimpleStringProperty(nic);
            this.contact = new SimpleStringProperty(contact);
            this.faculty=new SimpleStringProperty(faculty);
        }
        String getid(){
            //System.out.println(this.id.toString());
            return this.id;
        }

    }

    private ObservableList<indexInstructor.ins> getall() throws SQLException {
        ObservableList<ins> lecturers= FXCollections.observableArrayList();
        ArrayList<instructor> all= instructor.getall();
        for(instructor lectr:all){
            String name=lectr.getFirstName()+" "+lectr.getLastName();
            lecturers.add(new ins(lectr.getInstructor_id(),name, lectr.getNic(), lectr.getMobile(), lectr.getFaculty()));
        }
        return lecturers;
    }

    public void deleteInstructor(ActionEvent actionEvent) throws SQLException, IOException {
        deleteAlertShow alert = new deleteAlertShow();

        if(getSelecter()!=null) {
            String currentId = getSelecter();
            instructor current = instructor.findInstructor(currentId);
            current.delete();
            initialize();
        }else{
            alert.showError();
        }
    }

    @FXML
    void editins(ActionEvent event) throws IOException {
        //System.out.println(getSelecter());
        if (getSelecter()!=null) {
            Stage button = (Stage) editins.getScene().getWindow();
            button.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/instructor/edit.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Instructor");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/select.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error on Instructor Edit");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    static String getSelecter(){

        TreeItem<ins> slected= treeview1.getSelectionModel().getSelectedItem();
        //System.out.println(slected.getValue().getid());
        return slected==null?null:slected.getValue().getid();
    }

}