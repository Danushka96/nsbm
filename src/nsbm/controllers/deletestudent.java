package nsbm.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import nsbm.models.postgraduate;
import nsbm.models.undergraduate;

import java.sql.SQLException;

public class deletestudent {

    @FXML
    private JFXTextField nic;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField faculty;

    @FXML
    private JFXTextField course;

    public void initialize(){
        int type=delfindstudent.getType();
        if(type==0){
            undergraduate under=delfindstudent.getUnder();
            String fname=under.getFirstName()+" "+under.getLastName();
            nic.setText(under.getNic());
            name.setText(fname);
            faculty.setText(under.getFaculty());
            course.setText(under.getCourse_id());
        }else if(type==1){
            postgraduate post=delfindstudent.getPost();
            String fname=post.getFirstName()+" "+post.getLastName();
            nic.setText(post.getNic());
            name.setText(fname);
            faculty.setText(post.getFaculty());
            course.setText(post.getCourse_id());
        }
    }

    @FXML
    void close(ActionEvent event) {
        Stage thiswin=(Stage) name.getScene().getWindow();
        thiswin.close();
    }

    @FXML
    void delete(ActionEvent event) throws SQLException {
        delete();
        Stage thiswin=(Stage) name.getScene().getWindow();
        thiswin.close();
    }

    private void delete() throws SQLException {
        int type=nsbm.controllers.delfindstudent.getType();
        if(type==0){
            undergraduate under=nsbm.controllers.delfindstudent.getUnder();
            under.delete();
        }else if(type==1){
            postgraduate post=nsbm.controllers.delfindstudent.getPost();
            post.delete();
        }
    }

}
