package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;

public final class undergraduate extends student {
    private Connection con = ConnectionManager.getConnection();
    private int rank;
    private String stream,student_id,course_id;
    public undergraduate(String registrationNumber, String firstName, String lastName, String gender, String faculty, String nic, String email, String DOB, String address, String tp, String registration_date, int intake_number, String student_id, String stream, String course_id, int rank){
        super(registrationNumber,firstName,lastName,gender,faculty,nic,email,DOB,address,tp,registration_date,intake_number);
        this.student_id=student_id;
        this.rank=rank;
        this.course_id=course_id;
        this.stream=stream;
    }
//    Setters
    public void setStudent_id(String student_id){ this.student_id=student_id; }
    public void setRank(int rank){ this.rank=rank; }
    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
    public void setStream(String stream) {
        this.stream = stream;
    }

    // Getters
    public int getRank(){ return this.rank; }
    public String getStudent_id() {
        return student_id;
    }
    public String getStream() {
        return stream;
    }
    public String getCourse_id() {
        return course_id;
    }

    //    DB Save
    public boolean save() throws SQLException{
        super.save();
        String query="INSERT INTO undergraduates(student_id, rank, stream, reg_number, course_id) VALUES (?,?,?,?,?)";
        PreparedStatement insQuery=con.prepareStatement(query);
        insQuery.setString(1,this.student_id);
        insQuery.setInt(2,this.rank);
        insQuery.setString(3,this.stream);
        insQuery.setString(4,super.getReg_Number());
        insQuery.setString(5,this.course_id);
        int action= insQuery.executeUpdate();
        return action > 0;
    }
    public boolean update() throws SQLException{
        super.update();
        String query="UPDATE undergraduates set rank=?, stream=?, reg_number=?, course_id=? WHERE student_id=?";
        PreparedStatement upquery=con.prepareStatement(query);
        upquery.setInt(1,this.rank);
        upquery.setString(2,this.stream);
        upquery.setString(3,super.getReg_Number());
        upquery.setString(4,this.course_id);
        upquery.setString(5,this.student_id);
        int action= upquery.executeUpdate();
        return action > 0;
    }
    public void delete() throws SQLException{
        String query="DELETE FROM undergraduates WHERE student_id=?";
        PreparedStatement delquery=con.prepareStatement(query);
        delquery.setString(1,this.student_id);
        delquery.execute();
    }
    public static undergraduate findUndergraduate(String student_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM undergraduates WHERE student_id=? LIMIT 1";
        PreparedStatement findq=con.prepareStatement(query);
        findq.setString(1,student_id);
        ResultSet result=findq.executeQuery();
        int rank=0;
        String stream=null, reg_number=null, course_id=null;
        while (result.next()){
            rank=result.getInt("rank");
            stream=result.getString("stream");
            reg_number=result.getString("reg_number");
            course_id=result.getString("course_id");
        }
        student st1=findstudent(reg_number);
        return new undergraduate(reg_number,st1.getFirstName(),st1.getLastName(),st1.getGender(),st1.getFaculty(),st1.getNic(),st1.getEmail(),st1.getDob(),st1.getAddress(),st1.getMobile(),st1.getRegistration_date(),st1.getIntake_number(),student_id,stream,course_id,rank);
    }
    public static ArrayList<undergraduate> getall() throws SQLException{
        ArrayList<undergraduate> all=new ArrayList<>();
        Connection con=ConnectionManager.getConnection();
        String query="SELECT student_id FROM undergraduates";
        Statement allq=con.prepareStatement(query);
        ResultSet result=((PreparedStatement) allq).executeQuery();
        while (result.next()){
            String student_id=result.getString("student_id");
            all.add(findUndergraduate(student_id));
        }
        return all;
    }

    public static undergraduate getStudentID(String reg_num) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT student_id FROM undergraduates WHERE reg_number=?";
        PreparedStatement precon=con.prepareStatement(query);
        precon.setString(1,reg_num);
        ResultSet result=precon.executeQuery();
        String student_id="";
        while (result.next()) {
            student_id = result.getString("student_id");
        }
        //System.out.println(student_id);
        return findUndergraduate(student_id);
    }
}
