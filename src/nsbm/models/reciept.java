package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reciept {
    private Connection con=ConnectionManager.getConnection();
    private String reciept_id, issueDate;
    public reciept(String reciept_id, String issueDate){
        this.reciept_id=reciept_id;
        this.issueDate=issueDate;
    }

    public String getIssueDate() {
        return issueDate;
    }
    public String getReciept_id() {
        return reciept_id;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public void setReciept_id(String reciept_id) {
        this.reciept_id = reciept_id;
    }

    public void save() throws SQLException{
        String query="INSERT INTO reciept (id, issued_date) VALUES (?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.reciept_id);
        insq.setString(2,this.issueDate);
        insq.execute();
    }

    public void update() throws SQLException{
        String query="UPDATE reciept SET issued_date=? WHERE id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.issueDate);
        upq.setString(2,this.reciept_id);
        upq.execute();
    }

    public static reciept findreicept(String reciept_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM reciept WHERE id=?";
        PreparedStatement findq=con.prepareStatement(query);
        findq.setString(1,reciept_id);
        ResultSet result=findq.executeQuery();
        String issuedate=null;
        while (result.next()){
            issuedate=result.getString("issued_date");
        }
        return new reciept(reciept_id,issuedate);
    }
}
