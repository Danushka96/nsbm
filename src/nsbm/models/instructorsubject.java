package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class instructorsubject {
    private Connection con=ConnectionManager.getConnection();
    private String instructor_id, subject_id;
    instructorsubject(String instructor_id, String subject_id){
        this.instructor_id=instructor_id;
        this.subject_id=subject_id;
    }

    public String getInstructor_id() {
        return instructor_id;
    }
    public String getSubject_id() {
        return subject_id;
    }

    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }
    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public void save() throws SQLException{
        String query="INSERT INTO instructorsubject (instructor_id, subject_id) VALUES (?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.instructor_id);
        insq.setString(2,this.subject_id);
        insq.execute();
    }

    public void delete() throws SQLException{
        String query="DELETE FROM instructorsubject WHERE instructor_id=? AND subject_id=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.instructor_id);
        delq.setString(2,this.subject_id);
        delq.execute();
    }

    public static instructorsubject findinstructorSubject(String instructor_id, String subject_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM instructorsubject WHERE subject_id=? AND instructor_id=?";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,subject_id);
        selectq.setString(2,instructor_id);
        ResultSet result=selectq.executeQuery();
        return new instructorsubject(result.getString("instructor_id"),result.getString("subject_id"));
    }
}
