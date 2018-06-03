package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class studentassignment {
    private Connection con=ConnectionManager.getConnection();
    private String assignment_id, student_id;
    private int marks;
    studentassignment(String assignment_id, String student_id, int marks){
        this.assignment_id=assignment_id;
        this.student_id=student_id;
        this.marks=marks;
    }

    public String getAssignment_id() {
        return assignment_id;
    }
    public int getMarks() {
        return marks;
    }
    public String getStudent_id() {
        return student_id;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void save() throws SQLException{
        String query="INSERT INTO studentassignments (assignment_id, student_id, marks) VALUES (?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.assignment_id);
        insq.setString(2,this.student_id);
        insq.setInt(3,this.marks);
        insq.execute();
    }

    public void update() throws SQLException{
        String query="UPDATE studentassignments set marks=? WHERE student_id=? AND assignment_id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setInt(1,this.marks);
        upq.setString(2,this.student_id);
        upq.setString(3,this.assignment_id);
        upq.execute();
    }

    public void delete() throws SQLException{
        String quest="DELETE FROM studentassignments WHERE student_id=? AND assignment_id=?";
        PreparedStatement delq=con.prepareStatement(quest);
        delq.setString(1,this.student_id);
        delq.setString(2,this.assignment_id);
        delq.execute();
    }

    public static studentassignment findStudentassignment(String assignment_id, String student_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM studentassignments WHERE assignment_id=? AND student_id=? LIMIT 1";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,assignment_id);
        selectq.setString(2,student_id);
        ResultSet result= selectq.executeQuery();
        int marks=0;
        while (result.next()){
            marks=result.getInt("marks");
        }
        return new studentassignment(assignment_id,student_id,marks);
    }
}
