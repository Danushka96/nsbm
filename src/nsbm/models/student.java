package nsbm.models;
import nsbm.controllers.ConnectionManager;

import java.sql.*;

public class student extends UniversityMemeber{
    private Connection con = ConnectionManager.getConnection();
    private String reg_Number, registration_date;
    private int intake_number;

//  Constructors
    public student(String reg_Number, String firstName ,String lastName,String gender ,String faculty,String nic, String email, String DOB, String address, String tp, String registration_date, int intake_number){
        super(nic,faculty,firstName,lastName,gender,email,DOB,address,tp);
        this.reg_Number = reg_Number;
        this.registration_date=registration_date;
        this.intake_number=intake_number;
    }
//    Setters
    private void setReg_Number(String id){ this.reg_Number = id; }
    private void setRegistration_date(String registration_date){ this.registration_date=registration_date;}
    private void setIntake_number(int intake_number){ this.intake_number=intake_number;}

//    Getters
    private String getReg_Number(){ return this.reg_Number; }
    private String getRegistration_date(){ return this.registration_date;}
    private int getIntake_number(){ return this.intake_number;}

//    DB Actions
    public void save() throws SQLException {
        super.save();
        String query = "INSERT INTO students (reg_Number, intake_number, registration_date, nic) VALUES (?,?,?,?)";
        PreparedStatement queryins = con.prepareStatement(query);
        queryins.setString(1,this.reg_Number);
        queryins.setInt(2,this.intake_number);
        queryins.setString(3,this.registration_date);
        queryins.setString(4,getNic());
//        System.out.println(queryins);
        queryins.execute();
    }
    public void update() throws SQLException {
        super.update();
        String query = "UPDATE students set intake_number=?,registration_date=?,nic=? where reg_Number=?";
        PreparedStatement queryupd = con.prepareStatement(query);
        queryupd.setInt(1,this.intake_number);
        queryupd.setString(2,registration_date);
        queryupd.setString(3,getNic());
        queryupd.setString(4,this.reg_Number);
        queryupd.execute();
    }
    public void delete() throws SQLException{
        super.delete();
        String query="DELETE from students where reg_Number=?";
        PreparedStatement querydel=con.prepareStatement(query);
        querydel.setString(1,this.reg_Number);
        querydel.execute();
    }
    public static student findstudent(String nic) throws SQLException {
        Connection con=ConnectionManager.getConnection();
        UniversityMemeber memeber = UniversityMemeber.findmember(nic);
        String query="SELECT * FROM students WHERE nic=? LIMIT 1";
        PreparedStatement selectquery=con.prepareStatement(query);
        selectquery.setString(1,nic);
        String firstname=null,lastname=null,gender=null,email=null,dob=null,mobile=null,address=null,registration_date=null,reg_Number=null,faculty=null;
        int intake_number=0;
        ResultSet result=selectquery.executeQuery();
        while (result.next()){
            firstname=memeber.getFirstName();
            lastname=memeber.getLastName();
            gender=memeber.getGender();
            email=memeber.getEmail();
            dob=memeber.getDob();
            mobile=memeber.getMobile();
            address=memeber.getAddress();
            reg_Number=result.getString("reg_Number");
            intake_number=result.getInt("intake_number");
            registration_date=result.getString("registration_date");
            faculty=result.getString("faculty");
        }
        con.close();
        return new student(reg_Number,firstname,lastname,gender,faculty,nic,email,dob,address,mobile,registration_date,intake_number);
    }
}
