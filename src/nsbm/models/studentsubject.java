package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class studentsubject {
    private Connection con=ConnectionManager.getConnection();
    String semester_id, student_id, subject_id;
    public studentsubject(String semester_id, String student_id, String subject_id){
        this.semester_id=semester_id;
        this.student_id=student_id;
        this.subject_id=subject_id;
    }

    public String getSemester_id() {
        return semester_id;
    }
    public String getStudent_id() {
        return student_id;
    }
    public String getSubject_id() {
        return subject_id;
    }

    public void setSemester_id(String semester_id) {
        this.semester_id = semester_id;
    }
    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void save() throws SQLException{
        String query="INSERT INTO studentsubject (sem_id, student_id, subject_id) VALUES (?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.semester_id);
        insq.setString(2,this.student_id);
        insq.setString(3,this.subject_id);
        insq.execute();
    }

    public void delete() throws SQLException{
        String query="DELETE FROM studentsubject WHERE student_id=? AND sem_id=? AND subject_id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.student_id);
        upq.setString(2,this.semester_id);
        upq.setString(3,this.subject_id);
        upq.execute();
    }

    public static ArrayList<studentsubject> findstudentSubject(String semester_id, String student_id) throws SQLException{
        Connection con = ConnectionManager.getConnection();
        String query="SELECT * FROM studentsubject where student_id=? AND sem_id=?";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,student_id);
        selectq.setString(2,semester_id);
        ResultSet result= selectq.executeQuery();
        String subjectid=null;
        ArrayList<studentsubject> all=new ArrayList<>();
        while (result.next()){
            subjectid=result.getString("subject_id");
            all.add(new studentsubject(semester_id,student_id,subjectid));
        }
        return all;
    }
}
