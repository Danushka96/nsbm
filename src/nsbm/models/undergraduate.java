package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class undergraduate extends student {
    private Connection con = ConnectionManager.getConnection();
    private int result_id,rank;
    undergraduate(String student_id, String firstName,String lastName,String gender,String faculty,String nic,String email, String DOB, String address, String tp, String registration_date, int intake_number,int result_id, int rank){
        super(student_id,firstName,lastName,gender,faculty,nic,email,DOB,address,tp,registration_date,intake_number);
        this.result_id=result_id;
        this.rank=rank;
    }
//    Setters
    public void setResult_id(int result_id){ this.result_id=result_id; }
    public void setRank(int rank){ this.rank=rank; }
// Getters
    public int getRank(){ return this.rank; }
    public int getResult_id(){ return this.result_id; }
//    DB Save
    public void save() throws SQLException{
        super.save();
        String query="";
        PreparedStatement insQuery=con.prepareStatement(query);
    }
}
