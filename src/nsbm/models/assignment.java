package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class assignment {
    private Connection con=ConnectionManager.getConnection();
    private String assignment_id, name, subject_id;
    private int marks;
    public assignment(String assignment_id, String name, String subject_id, int marks){
        this.assignment_id=assignment_id;
        this.name=name;
        this.subject_id=subject_id;
        this.marks=marks;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getSubject_id() {
        return subject_id;
    }
    public String getName() {
        return name;
    }
    public int getMarks() {
        return marks;
    }
    public String getAssignment_id() {
        return assignment_id;
    }

    public boolean save() throws SQLException{
        String query="INSERT INTO assignments (id, name, marks, subject_id) values (?,?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.assignment_id);
        insq.setString(2,this.name);
        insq.setInt(3,this.marks);
        insq.setString(4,this.subject_id);
        int result=insq.executeUpdate();
        return result>0;
    }

    public boolean update() throws SQLException{
        String query="UPDATE assignments SET name=?, marks=?, subject_id=? WHERE id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.name);
        upq.setInt(2,this.marks);
        upq.setString(3,this.subject_id);
        upq.setString(4,this.assignment_id);
        int result=upq.executeUpdate();
        return result>0;
    }

    public void delete() throws SQLException{
        String query="DELETE FROM assignments WHERE id=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.assignment_id);
        delq.execute();
    }

    public static assignment findassignment(String assignment_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM assignments WHERE id=? LIMIT 1";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,assignment_id);
        ResultSet result=selectq.executeQuery();
        String name=null, subject_id=null;
        int marks=0;
        while (result.next()){
            name=result.getString("name");
            subject_id=result.getString("subject_id");
            marks=result.getInt("marks");
        }
        return new assignment(assignment_id,name,subject_id,marks);
    }

    public static ArrayList<assignment> getall() throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM assignments";
        PreparedStatement selectq=con.prepareStatement(query);
        ResultSet result=selectq.executeQuery();
        ArrayList<assignment> all=new ArrayList<>();
        while (result.next()){
            String insid = result.getString("id");
            all.add(findassignment(insid));
        }
        return all;
    }
}
