package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class faculty {
    private Connection con=ConnectionManager.getConnection();
    private String code, name, telephone;
    faculty(String code, String name, String telephone){
        this.code=code;
        this.name=name;
        this.telephone=telephone;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getTelephone() {
        return telephone;
    }

    public void save() throws SQLException{
        String query="INSERT INTO faculties (code, name, telephone) VALUES (?,?,?)";
        PreparedStatement insquery=con.prepareStatement(query);
        insquery.setString(1,this.code);
        insquery.setString(2,this.name);
        insquery.setString(3,this.telephone);
        insquery.execute();
    }
    public void update() throws SQLException{
        String query="UPDATE faculties SET name=?,telephone=? WHERE code=?";
        PreparedStatement upquery=con.prepareStatement(query);
        upquery.setString(1,this.name);
        upquery.setString(2,this.telephone);
        upquery.setString(3,this.code);
        upquery.execute();
    }
    public void delete() throws SQLException{
        String query="DELETE FROM faculties where code=?";
        PreparedStatement delquery=con.prepareStatement(query);
        delquery.setString(1,this.code);
        delquery.execute();
    }
    public static faculty find(String code) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM faculties where code=? LIMIT 1";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,code);
        ResultSet result=selectq.executeQuery();
        String name=null,telephone=null;
        while (result.next()){
            name=result.getString("name");
            telephone=result.getString("telephone");
        }
        return new faculty(code,name,telephone);
    }
    public static ArrayList<String> getall() throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM faculties";
        PreparedStatement findq=con.prepareStatement(query);
        ResultSet result=findq.executeQuery();

        ArrayList<String> arr = new ArrayList<String>();
        while (result.next()){
            arr.add(result.getString("code"));
        }
        return arr;
    }
    public static ArrayList<faculty> getallfaculty() throws SQLException{
        Connection con= ConnectionManager.getConnection();
        String query="SELECT * FROM faculties";
        PreparedStatement findq=con.prepareStatement(query);
        ResultSet result=findq.executeQuery();
        ArrayList<faculty> arr = new ArrayList<faculty>();
        while (result.next()){
            String faculty_id=result.getString("code");
            arr.add(find(faculty_id));
        }
        return arr;
    }
}
