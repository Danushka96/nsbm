package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyPermission;

public class alresult {
    Connection con=ConnectionManager.getConnection();
    String student_id, subject_id, result;
    alresult(String student_id, String subject_id, String result){
        this.student_id=student_id;
        this.subject_id=subject_id;
        this.result=result;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getStudent_id() {
        return student_id;
    }
    public String getSubject_id() {
        return subject_id;
    }
    public String getResult() {
        return result;
    }


    public void save() throws SQLException{
        String query="INSERT INTO alresults (student_id, subject_name, result) VALUES (?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.student_id);
        insq.setString(1,this.subject_id);
        insq.setString(1,this.result);
        insq.execute();
    }

    public void update() throws SQLException{
        String query="UPDATE alresults SET subject_name=?, result=? where student_id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.subject_id);
        upq.setString(2,this.result);
        upq.setString(3,this.subject_id);
        upq.execute();
    }

    public static alresult[] find(String student_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM alresults WHERE student_id=?";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,student_id);
        ResultSet resultset=selectq.executeQuery();
        String subjectname=null,result=null;
        int count=0;
        while (resultset.next()){
            count++;
        }
        alresult[] all = new alresult[count];
        while (resultset.next()){
            subjectname=resultset.getString("subject_name");
            result=resultset.getString("result");
            all[count-1]=new alresult(student_id,subjectname,result);
            count--;
        }
        return all;
    }
}
