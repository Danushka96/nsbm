package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.PropertyPermission;

public class lab {
    Connection con=ConnectionManager.getConnection();
    String id, name, faculty;
    int numberofSeats;
    public lab(String id, String name, String faculty, int numberofSeats){
        this.id=id;
        this.name=name;
        this.faculty=faculty;
        this.numberofSeats=numberofSeats;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getFaculty() {
        return faculty;
    }
    public int getNumberofSeats() {
        return numberofSeats;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public void setNumberofSeats(int numberofSeats) {
        this.numberofSeats = numberofSeats;
    }

    public boolean save() throws SQLException{
        String query="INSERT INTO labs (id, name, numberofSeats, faculty) VALUES (?,?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.id);
        insq.setString(2,this.name);
        insq.setInt(3,this.numberofSeats);
        insq.setString(4,this.faculty);
        int result=insq.executeUpdate();
        return result>0;
    }

    public boolean update() throws SQLException{
        String query="UPDATE labs set name=?, numberofSeats=?, faculty=? WHERE id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.name);
        upq.setInt(2,this.numberofSeats);
        upq.setString(3,this.faculty);
        upq.setString(4,this.id);
        int result=upq.executeUpdate();
        return result>0;
    }

    public void delete() throws SQLException{
        String query="DELETE FROM labs WHERE id=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.id);
        delq.execute();
    }

    public static lab findlab(String id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM labs WHERE id=?";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,id);
        ResultSet result=selectq.executeQuery();
        String name=null, faculty=null;
        int numberofSeats=0;
        while (result.next()){
            name=result.getString("name");
            numberofSeats=result.getInt("numberofSeats");
            faculty=result.getString("faculty");
        }
        return new lab(id,name,faculty,numberofSeats);
    }

    public static ArrayList<lab> getall() throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM labs";
        PreparedStatement selectq=con.prepareStatement(query);
        ResultSet result=selectq.executeQuery();
        ArrayList<lab> all=new ArrayList<>();
        while (result.next()){
            String lab_id = result.getString("id");
            all.add(findlab(lab_id));
        }
        return all;
    }
}
