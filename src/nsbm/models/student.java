package nsbm.models;
import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class student extends UniversityMemeber{
    private Connection con = ConnectionManager.getConnection();
    private String student_id, registration_date;
    private int intake_number;

//  Constructors
    public student(String student_id, String firstName,String lastName,String nic,String email, String DOB, String address, String tp, String registration_date, int intake_number){
        super(nic,firstName,lastName,email,DOB,address,tp);
        this.student_id=student_id;
        this.registration_date=registration_date;
        this.intake_number=intake_number;
    }
//    Setters
    private void setStudent_id(String id){ this.student_id = id; }
    private void setRegistration_date(String registration_date){ this.registration_date=registration_date;}
    private void setIntake_number(int intake_number){ this.intake_number=intake_number;}

//    Getters
    private String getStudent_id(){ return this.student_id; }
    private String getRegistration_date(){ return this.registration_date;}
    private int getIntake_number(){ return this.intake_number;}

//    DB Actions
    public void save() throws SQLException {
        super.save();
        String query = "INSERT INTO students (student_id, intake_number, registration_date, nic) VALUES (?,?,?,?)";
        PreparedStatement queryins = con.prepareStatement(query);
        queryins.setString(1,this.student_id);
        queryins.setInt(2,this.intake_number);
        queryins.setString(3,this.registration_date);
        queryins.setString(4,getNic());
//        System.out.println(queryins);
        queryins.execute();
    }
    public void update() throws SQLException {
        super.update();
        String query = "UPDATE students set intake_number=?,registration_date=?,nic=? where student_id=?";
        PreparedStatement queryupd = con.prepareStatement(query);
        queryupd.setInt(1,this.intake_number);
        queryupd.setString(2,registration_date);
        queryupd.setString(3,getNic());
        queryupd.setString(4,this.student_id);
        queryupd.execute();
    }
    public void delete() throws SQLException{
        String query="DELETE from students where student_id=?";
        PreparedStatement querydel=con.prepareStatement(query);
        querydel.setString(1,this.student_id);
        querydel.execute();
    }
}
