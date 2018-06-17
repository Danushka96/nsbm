package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;

public class semester {
    private Connection con = ConnectionManager.getConnection();
    private String semester_id, year, semester_number, start_date, end_date, faculty;
    public semester(String semester_id, String year, String semester_number, String start_date, String end_date, String faculty){
        this.semester_number=semester_number;
        this.semester_id=semester_id;
        this.year=year;
        this.start_date=start_date;
        this.end_date=end_date;
        this.faculty=faculty;
    }

    public String getFaculty() {
        return faculty;
    }
    public String getEnd_date() {
        return end_date;
    }
    public String getSemester_id() {
        return semester_id;
    }
    public String getSemester_number() {
        return semester_number;
    }
    public String getStart_date() {
        return start_date;
    }
    public String getYear() {
        return year;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
    public void setSemester_id(String semester_id) {
        this.semester_id = semester_id;
    }
    public void setSemester_number(String semester_number) {
        this.semester_number = semester_number;
    }
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public boolean save() throws SQLException{
        String query="INSERT INTO semester (id, faculty, semesternumber, yearof, start_date, end_date) VALUES (?,?,?,?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.semester_id);
        insq.setString(2,this.faculty);
        insq.setString(3,this.semester_number);
        insq.setString(4,this.year);
        insq.setString(5,this.start_date);
        insq.setString(6,this.end_date);
        int result=insq.executeUpdate();
        return result>0;
    }
    public boolean update() throws SQLException{
        String query="UPDATE semester SET faculty=?, semesternumber=?, yearof=?, start_date=?, end_date=? WHERE id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.faculty);
        upq.setString(2,this.semester_number);
        upq.setString(3,this.year);
        upq.setString(4,this.start_date);
        upq.setString(5,this.end_date);
        upq.setString(6,this.semester_id);
        int result=upq.executeUpdate();
        return result>0;
    }
    public void delete() throws SQLException{
        String query="DELETE FROM semester WHERE id=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.semester_id);
        delq.execute();
    }
    public static semester findsemester(String semester_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM semester WHERE id=? LIMIT 1";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,semester_id);
        ResultSet result=selectq.executeQuery();
        String year=null, semester_number=null, start_date=null, end_date=null, faculty=null;
        while (result.next()){
            year=result.getString("yearof");
            semester_number=result.getString("semesternumber");
            start_date=result.getString("start_date");
            end_date=result.getString("end_date");
            faculty=result.getString("faculty");
        }
        return new semester(semester_id,year,semester_number,start_date,end_date,faculty);
    }

    public static ArrayList<semester> getall() throws SQLException{
        ArrayList<semester> all=new ArrayList<>();
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM semester";
        Statement allq=con.prepareStatement(query);
        ResultSet result=((PreparedStatement) allq).executeQuery();
        while (result.next()){
            String sem_id=result.getString("id");
            all.add(findsemester(sem_id));
        }
        return all;
    }
}
