package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyPermission;

public final class user {
    private Connection con=ConnectionManager.getConnection();
    private String username, password, faculty_id;
    private user(String username, String password, String faculty_id){
        this.username=username;
        this.password=password;
        this.faculty_id=faculty_id;
    }

    public String getFaculty_id() {
        return faculty_id;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

    public void setFaculty_id(String faculty_id) {
        this.faculty_id = faculty_id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void save() throws SQLException{
        String query="INSERT INTO users (username, password, faculty_id) VALUES (?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.username);
        insq.setString(2,this.password);
        insq.setString(3,this.faculty_id);
        insq.execute();
    }

    public void update() throws SQLException{
        String query="UPDATE users SET password=?, faculty_id=? WHERE username=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.password);
        upq.setString(2,this.faculty_id);
        upq.setString(3,this.username);
        upq.execute();
    }

    public void delete() throws SQLException{
        String query="DELETE FROM users WHERE username=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.username);
        delq.execute();
    }

    public static user finduser(String username) throws SQLException{
        Connection con= ConnectionManager.getConnection();
        String query="SELECT * FROM users WHERE username=?";
        PreparedStatement findq=con.prepareStatement(query);
        findq.setString(1,username);
        ResultSet result = findq.executeQuery();
        String password=null, faculty_id=null;
        while (result.next()){
            password=result.getString("password");
            faculty_id=result.getString("faculty_id");
        }
        return new user(username,password,faculty_id);
    }
}
