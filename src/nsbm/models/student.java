package nsbm.models;
import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class student {
    private Connection con = ConnectionManager.getConnection();
    private String id;
    private String name;
    private String nic;
    private String email;
    private String dateofBirth;
    private String address;
    private String tp;
//  Constructors
    public student(String id, String name,String nic,String email, String DOB, String address, String tp){
        this.id = id;
        this.name = name;
        this.nic=nic;
        this.email = email;
        this.dateofBirth = DOB;
        this.address = address;
        this.tp = tp;
    }
    public student(){
        this.id = null;
        this.name = null;
        this.nic = null;
        this.email = null;
        this.dateofBirth = null;
        this.address = null;
        this.tp = null;
    }
//    Setters
    private void setId(String id){ this.id = id; }
    private void setName(String name){ this.name = name; }
    private void setNic(String nic){ this.nic = nic;}
    private void setEmail(String email){ this.email = email; }
    private void setDateofBirth(String date){ this.dateofBirth = date; }
    private void setAddress(String address){ this.address = address; }
    private void setTp(String tp){ this.tp=tp; }
//    Getters
    private String getId(){ return this.id; }
    private String getName(){ return this.name; }
    private String getNic(){ return this.nic; }
    private String getEmail(){ return this.email; }
    private String getDateofBirth(){ return this.dateofBirth; }
    private String getAddress(){ return this.address; }
    private String getTp(){ return this.tp; }
//    DB Actions
    public void save() throws SQLException {
        String query = "INSERT INTO students (id,name,nic,email,address,tp,dateofbirth) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement queryins = con.prepareStatement(query);
        queryins.setString(1,this.id);
        queryins.setString(2,this.name);
        queryins.setString(3,this.nic);
        queryins.setString(4,this.email);
        queryins.setString(5,this.address);
        queryins.setString(6,this.tp);
        queryins.setString(7,this.dateofBirth);
//        System.out.println(queryins);
        queryins.execute();
    }
    public void update() throws SQLException {
        String query = "UPDATE students set name=?,nic=?,email=?,address=?,tp=?,dateofbirth=? where id=?";
        PreparedStatement queryupd = con.prepareStatement(query);
        queryupd.setString(1,this.name);
        queryupd.setString(2,this.nic);
        queryupd.setString(3,this.email);
        queryupd.setString(4,this.address);
        queryupd.setString(5,this.tp);
        queryupd.setString(6,this.dateofBirth);
        queryupd.setString(7,this.id);
        queryupd.execute();
    }
    public void delete() throws SQLException{
        String query="DELETE from students where id=?";
        PreparedStatement querydel=con.prepareStatement(query);
        querydel.setString(1,this.id);
        querydel.execute();
    }
}
