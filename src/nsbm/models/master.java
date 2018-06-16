package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class master extends course{
    private Connection con=ConnectionManager.getConnection();
    private String code;
    public master(String code, String faculty, String name, int credits, int numberofyears, int can_extend){
        super(code, faculty, name, credits, numberofyears, can_extend);
        this.code=code;
    }
    public boolean save() throws SQLException {
        super.save();
        String query="INSERT INTO masters (id) VALUES (?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.code);
        int result=insq.executeUpdate();
        return result>0;
    }
    public boolean update() throws SQLException{
        boolean result=super.update();
        String query="UPDATE masters SET id=? WHERE id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.code);
        upq.setString(2,this.code);
        upq.executeUpdate();
        return result;
    }
    public void delete() throws SQLException{

    }
}
