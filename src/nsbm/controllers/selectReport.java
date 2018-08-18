package nsbm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import nsbm.models.UniversityMemeber;
import nsbm.models.postgraduate;
import nsbm.models.semester;
import nsbm.models.undergraduate;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Danushka
 */
public class selectReport {

    @FXML
    private JFXTextField studentid;

    @FXML
    private JFXComboBox semester1;

    @FXML
    void generate(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) throws SQLException {
        String studentId = studentid.getText();
        String nic;
        if(undergraduate.findUndergraduate(studentId).getCourse_id()!=null){
            nic = undergraduate.findUndergraduate(studentId).getReg_Number();
        }else{
            nic = postgraduate.findPostgraduate(studentId).getReg_Number();
        }
        UniversityMemeber unimem = UniversityMemeber.findmember(nic);

    }
}
