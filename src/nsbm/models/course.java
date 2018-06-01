package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class course {
    private String code,name;
    private Connection con=ConnectionManager.getConnection();
    private int credits,numberofyears,can_extend;
    course(String code,String name, int credits,int numberofyears, int can_extend){
        this.code=code;
        this.name=name;
        this.credits=credits;
        this.numberofyears=numberofyears;
        this.can_extend=can_extend;
    }
    public void setCode(String code){
        this.code=code;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setCredits(int credits){
        this.credits=credits;
    }
    public void setNumberofyears(int numberofyears){
        this.numberofyears=numberofyears;
    }
    public void setCan_extend(int can_extend){
        this.can_extend=can_extend;
    }
    public String getCode(){
        return this.code;
    }
    public String getName(){
        return this.name;
    }
    public int getNumberofyears() {
        return numberofyears;
    }
    public int getCan_extend() {
        return can_extend;
    }
    public int getCredits() {
        return credits;
    }
    public void save() throws SQLException {
        String query="INSERT INTO courses(code, name, credits, numberofyears, can_extend) values (?,?,?,?,?)";
        PreparedStatement insquery=con.prepareStatement(query);
        insquery.setString(1,this.code);
        insquery.setString(2,this.name);
        insquery.setInt(3,this.credits);
        insquery.setInt(4,this.numberofyears);
        insquery.setInt(5,this.can_extend);
        insquery.execute();
    }
    public void update() throws SQLException{
        String query="UPDATE courses set code=?, name=?, credits=?, numberofyears=?, can_extend=?";
        PreparedStatement updatequery=con.prepareStatement(query);
        updatequery.setString(1,this.code);
        updatequery.setString(2,this.name);
        updatequery.setInt(3,this.credits);
        updatequery.setInt(4,this.numberofyears);
        updatequery.setInt(5,this.can_extend);
        updatequery.execute();
    }
    public void delete() throws SQLException{
        String query="DELETE FROM courses where code=?";
        PreparedStatement delquery=con.prepareStatement(query);
        delquery.setString(1,this.code);
        delquery.execute();
    }
}
