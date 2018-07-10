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
import nsbm.models.subject;
import nsbm.models.timeslot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class indexTimetable {

    @FXML
    private JFXTreeTableView<timet> treeview;
    private static JFXTreeTableView<timet> treeview1;

    @FXML
    private JFXButton addnew;

    public void initialize() throws SQLException {

        // Time
        JFXTreeTableColumn<indexTimetable.timet, String> id = new JFXTreeTableColumn<>("Time ID");
        id.setPrefWidth(150);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexTimetable.timet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexTimetable.timet, String> param) {
                return param.getValue().getValue().id;
            }
        });

        // Date
        JFXTreeTableColumn<indexTimetable.timet, String> date = new JFXTreeTableColumn<>("Date");
        date.setPrefWidth(150);
        date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexTimetable.timet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexTimetable.timet, String> param) {
                return param.getValue().getValue().date;
            }
        });

        // From
        JFXTreeTableColumn<indexTimetable.timet, String> from = new JFXTreeTableColumn<>("From");
        from.setPrefWidth(150);
        from.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexTimetable.timet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexTimetable.timet, String> param) {
                return param.getValue().getValue().from;
            }
        });

        // To
        JFXTreeTableColumn<indexTimetable.timet, String> to = new JFXTreeTableColumn<>("To");
        to.setPrefWidth(150);
        to.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexTimetable.timet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexTimetable.timet, String> param) {
                return param.getValue().getValue().to;
            }
        });

        // Subject
        JFXTreeTableColumn<indexTimetable.timet, String> subject = new JFXTreeTableColumn<>("Subject");
        subject.setPrefWidth(150);
        subject.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<indexTimetable.timet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<indexTimetable.timet, String> param) {
                return param.getValue().getValue().subjectid;
            }
        });


        ObservableList<indexTimetable.timet> subjects= getall();
        final TreeItem<indexTimetable.timet> root = new RecursiveTreeItem<indexTimetable.timet>(subjects, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(id,date,from,to,subject);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview1=treeview;
    }

    class timet extends RecursiveTreeObject<indexTimetable.timet> {
        StringProperty id,course_id, date, from, to, subjectid;
        String timeid;

        timet(String id, String course_id, String date, String from, String to, String subjectid) {
            this.timeid=id;
            this.course_id=new SimpleStringProperty(course_id);
            this.date=new SimpleStringProperty(date);
            this.from=new SimpleStringProperty(from);
            this.to = new SimpleStringProperty(to);
            this.subjectid = new SimpleStringProperty(subjectid);
        }
        String getid(){
            //System.out.println(this.id.toString());
            return this.timeid;
        }

    }

    private ObservableList<indexTimetable.timet> getall() throws SQLException {
        ObservableList<indexTimetable.timet> timdata= FXCollections.observableArrayList();
        String course=selectTimetable.getcode();
        ArrayList<timeslot> all= timeslot.getall(course);
        for(timeslot time:all){
            timdata.add(new indexTimetable.timet(time.getTimeslot_id(),time.getCourse_id(),time.getDate(), time.getFrom(), time.getTo(), time.getSubject_id()));
        }
        return timdata;
    }

    static String getSelecter(){

        TreeItem<indexTimetable.timet> slected= treeview1.getSelectionModel().getSelectedItem();
        return slected==null?null:slected.getValue().getid();
    }

    @FXML
    void addnew(ActionEvent event) throws IOException {
        close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/timetable/create.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add new Timeslot");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void deleteTime(ActionEvent actionEvent) throws SQLException, IOException {
        deleteAlertShow alert = new deleteAlertShow();
        if(getSelecter()!=null) {
            String currentId = getSelecter();
            timeslot current = timeslot.findtimeslot(currentId);
            current.delete();
            initialize();
        }else{
            alert.showError();
        }
    }

    @FXML
    void edit(ActionEvent event) throws IOException {
        if(getSelecter()!=null) {
            close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/timetable/update.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Timeslot");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/select.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error on Edit Timetable");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    void close(){
        Stage thiswindow= (Stage) addnew.getScene().getWindow();
        thiswindow.close();
    }
}
