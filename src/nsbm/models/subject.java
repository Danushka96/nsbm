package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class subject {
    Connection con = ConnectionManager.getConnection();
    String code, name, lecturer_id, course_id;
    Double fee;
    int numberofCredits, numberofHours;
    subject(String code, String name, Double fee, int numberofCredits, String lecturer_id, int numberofHours, String course_id){
        this.code=code;
        this.name=name;
        this.fee=fee;
        this.numberofCredits=numberofCredits;
        this.lecturer_id=lecturer_id;
        this.numberofHours=numberofHours;
        this.course_id=course_id;
    }

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public Double getFee() {
        return fee;
    }
    public int getNumberofCredits() {
        return numberofCredits;
    }
    public String getLecturer_id() {
        return lecturer_id;
    }
    public int getNumberofHours() {
        return numberofHours;
    }
    public String getCourse_id() {
        return course_id;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFee(Double fee) {
        this.fee = fee;
    }
    public void setNumberofCredits(int numberofCredits) {
        this.numberofCredits = numberofCredits;
    }
    public void setLecturer_id(String lecturer_id) {
        this.lecturer_id = lecturer_id;
    }
    public void setNumberofHours(int numberofHours) {
        this.numberofHours = numberofHours;
    }
    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void save() throws SQLException{
        String query="INSERT INTO subjects (code, name, fee, numberofCredits, numberofHours, lecturer_id, course_id) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.code);
        insq.setString(2,this.name);
        insq.setDouble(3,this.fee);
        insq.setInt(4,this.numberofCredits);
        insq.setInt(5,this.numberofHours);
        insq.setString(6,this.lecturer_id);
        insq.setString(7,this.course_id);
        insq.execute();
    }

    public void update() throws SQLException{
        String query="UPDATE subjects SET name=?, fee=?, numberofCredits=?, numberofHours=?, lecturer_id=?, course_id=? WHERE code=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.name);
        upq.setDouble(2,this.fee);
        upq.setInt(3,this.numberofCredits);
        upq.setInt(4,this.numberofHours);
        upq.setString(5,this.lecturer_id);
        upq.setString(6,this.course_id);
        upq.setString(7,this.code);
        upq.execute();
    }

    public void delete() throws SQLException{
        String query="DELETE FROM subjects WHERE code=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.code);
        delq.execute();
    }

    public static subject findsubject(String code) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM subjects WHERE code=? LIMIT 1";
        PreparedStatement findq=con.prepareStatement(query);
        findq.setString(1,code);
        ResultSet result=findq.executeQuery();
        String name=null, lecturer_id=null, course_id=null;
        Double fee=0.0;
        int numberofCredits=0, numberofHours=0;
        while (result.next()){
            name=result.getString("name");
            lecturer_id=result.getString("lecturer_id");
            course_id=result.getString("course_id");
            fee=result.getDouble("fee");
            numberofCredits=result.getInt("numberofCredits");
            numberofHours=result.getInt("numberofHours");
        }
        return new subject(code,name,fee,numberofCredits,lecturer_id,numberofHours,course_id);
    }
}
