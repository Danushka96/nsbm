package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class timeslot {
    Connection con=ConnectionManager.getConnection();
    String timeslot_id, course_id, faculty_id, from, to, date, subject_id;
    timeslot(String timeslot_id, String course_id, String faculty_id, String from, String to, String date, String subject_id){
        this.timeslot_id=timeslot_id;
        this.course_id=course_id;
        this.faculty_id=faculty_id;
        this.from=from;
        this.to=to;
        this.date=date;
        this.subject_id=subject_id;
    }

    public String getTimeslot_id() {
        return timeslot_id;
    }
    public String getCourse_id() {
        return course_id;
    }
    public String getFaculty_id() {
        return faculty_id;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public String getDate() {
        return date;
    }
    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setFaculty_id(String faculty_id) {
        this.faculty_id = faculty_id;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public void setTimeslot_id(String timeslot_id) {
        this.timeslot_id = timeslot_id;
    }
    public void setTo(String to) {
        this.to = to;
    }

    public void save() throws SQLException{
        String query="INSERT INTO timeslots (id, course_id, faculty_id, fromtime, totime, dateof, subject_id) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.timeslot_id);
        insq.setString(2,this.course_id);
        insq.setString(3,this.faculty_id);
        insq.setString(4,this.from);
        insq.setString(5,this.to);
        insq.setString(6,this.date);
        insq.setString(7,this.subject_id);
        insq.execute();
    }

    public void update() throws SQLException{
        String query="UPDATE timeslots set course_id=?, faculty_id=?, fromtime=?, totime=?, dateof=?, subject_id=? WHERE id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.course_id);
        upq.setString(2,this.faculty_id);
        upq.setString(3,this.from);
        upq.setString(4,this.to);
        upq.setString(5,this.date);
        upq.setString(6,this.subject_id);
        upq.setString(7,this.timeslot_id);
        upq.execute();
    }

    public void delete() throws SQLException{
        String query="DELETE FROM timeslots WHERE id=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.timeslot_id);
        delq.execute();
    }

    public static timeslot findtimeslot(String timeslot_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM timeslots WHERE id=? LIMIT 1";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,timeslot_id);
        ResultSet result= selectq.executeQuery();
        String course_id=null, faculty_id=null, from=null, to=null, date=null, subject_id=null;
        while (result.next()){
            course_id=result.getString("course_id");
            faculty_id=result.getString("faculty_id");
            from=result.getString("fromtime");
            to=result.getString("totime");
            date=result.getString("dateof");
            subject_id=result.getString("subject_id");
        }
        return new timeslot(timeslot_id,course_id,faculty_id,from,to,date,subject_id);
    }

    public static ArrayList<timeslot> getall(String course_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM timeslots WHERE course_id=?";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,course_id);
        ResultSet result=selectq.executeQuery();
        ArrayList<timeslot> all=new ArrayList<>();
        while (result.next()){
            String insid = result.getString("id");
            all.add(findtimeslot(insid));
        }
        return all;
    }
}
