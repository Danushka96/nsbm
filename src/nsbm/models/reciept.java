package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reciept {
    private Connection con=ConnectionManager.getConnection();
    private String issueDate;
    private double amount;
    public reciept(Double amount, String issueDate){
        this.amount=amount;
        this.issueDate=issueDate;
    }

    public String getIssueDate() {
        return issueDate;
    }
    public Double getAmount() {
        return amount;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    public void setReciept_id(Double amount) {
        this.amount = amount;
    }

    public int save() throws SQLException{
        String query="INSERT INTO reciept (amount, issued_date) VALUES (?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setDouble(1,this.amount);
        insq.setString(2,this.issueDate);
        System.out.println(insq);
        insq.executeUpdate();
        ResultSet resultset=insq.getGeneratedKeys();
        return resultset.next()?resultset.getInt(1):0;
    }

    public void update(int id) throws SQLException{
        String query="UPDATE reciept SET issued_date=?, amount=? WHERE id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.issueDate);
        upq.setDouble(2,this.amount);
        upq.setInt(3,id);
        upq.execute();
    }

    public static reciept findreicept(String reciept_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM reciept WHERE id=?";
        PreparedStatement findq=con.prepareStatement(query);
        findq.setString(1,reciept_id);
        ResultSet result=findq.executeQuery();
        String issuedate=null;
        Double amount=null;
        while (result.next()){
            issuedate=result.getString("issued_date");
            amount=result.getDouble("amount");
        }
        return new reciept(amount,issuedate);
    }
}
