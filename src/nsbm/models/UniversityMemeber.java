package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UniversityMemeber {
    private Connection con = ConnectionManager.getConnection();
    private String nic,firstName,lastName,email,dob,address,mobile;
    public UniversityMemeber(String nic,String firstName,String lastName,String email, String dob, String address, String mobile){
        this.nic=nic;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.dob=dob;
        this.address=address;
        this.mobile=mobile;
    }
    public void setNic(String nic){
        this.nic=nic;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setDob(String dob){
        this.dob=dob;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setMobile(String mobile){
        this.mobile=mobile;
    }
//    Getter
    String getNic(){
        return this.nic;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getDob(){
        return this.dob;
    }
    public String getAddress(){
        return this.address;
    }
    public String getMobile(){
        return this.mobile;
    }
//    DB Actions
    public void save() throws SQLException {
        String query="INSERT INTO universitymembers(nic, firstName, lastName,email, dob, mobile, address) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement insquery=con.prepareStatement(query);
        insquery.setString(1,this.nic);
        insquery.setString(2,this.firstName);
        insquery.setString(3,this.lastName);
        insquery.setString(4,this.email);
        insquery.setString(5,this.dob);
        insquery.setString(6,this.mobile);
        insquery.setString(7,this.address);
        insquery.execute();
    }
    public void update() throws SQLException{
        String query="update universitymembers set firstName=?, lastName=?,email=?,dob=?,mobile=?,address=? where nic=?";
        PreparedStatement upquery=con.prepareStatement(query);
        upquery.setString(1,this.firstName);
        upquery.setString(2,this.lastName);
        upquery.setString(3,this.email);
        upquery.setString(4,this.dob);
        upquery.setString(5,this.mobile);
        upquery.setString(6,this.address);
        upquery.setString(7,this.nic);
        upquery.execute();
    }
    public void delete() throws SQLException{
        String query="DELETE from universitymembers where nic=?";
        PreparedStatement delquery=con.prepareStatement(query);
        delquery.setString(1,this.nic);
        delquery.execute();
    }
    public static UniversityMemeber findmember(String nic) throws SQLException{
        String query="SELECT * from universitymembers where nic=? LIMIT 1";
        Connection con=ConnectionManager.getConnection();
        PreparedStatement findquery=con.prepareStatement(query);
        findquery.setInt(1,100);
        findquery.setString(1,nic);
        ResultSet result = findquery.executeQuery();
        String firstname=null,lastname=null,email=null,dob=null,mobile=null,address=null;
        while(result.next()) {
            firstname = result.getString("firstname");
            //System.out.println(firstname);
            lastname = result.getString("lastname");
            email = result.getString("email");
            dob = result.getString("dob");
            mobile = result.getString("mobile");
            address = result.getString("address");
        }
        return new UniversityMemeber(nic,firstname,lastname,email,dob,address,mobile);
    }
}
