package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class payment {
    private Connection con=ConnectionManager.getConnection();
    private String payment_id, student_id, semester_id, reciept_id;
    public payment(String payment_id, String student_id, String semester_id, String reciept_id){
        this.payment_id=payment_id;
        this.student_id=student_id;
        this.semester_id=semester_id;
        this.reciept_id=reciept_id;
    }
    public payment(String student_id, String semester_id, String reciept_id){
        this.student_id=student_id;
        this.semester_id=semester_id;
        this.reciept_id=reciept_id;
    }

    public String getReciept_id() {
        return reciept_id;
    }
    public String getStudent_id() {
        return student_id;
    }
    public String getSemester_id() {
        return semester_id;
    }

    public String getPayment_id() {
        return payment_id;
    }
    public void setReciept_id(String reciept_id) {
        this.reciept_id = reciept_id;
    }
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
    public void setSemester_id(String semester_id) {
        this.semester_id = semester_id;
    }

    public boolean save() throws SQLException{
        String query="INSERT INTO payments ( student_id, semester_id, reciept_id) VALUES (?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.student_id);
        insq.setString(2,this.semester_id);
        insq.setString(3,this.reciept_id);
        int result=insq.executeUpdate();
        return result>0;
    }

    public boolean update() throws SQLException{
        String query="UPDATE payments SET student_id=?, semester_id=?, reciept_id=? WHERE id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.student_id);
        upq.setString(2,this.semester_id);
        upq.setString(3,this.reciept_id);
        upq.setString(4,this.payment_id);
        int result=upq.executeUpdate();
        return result>0;
    }

    public static payment findpayment(String payment_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM payments WHERE id=? LIMIT 1";
        PreparedStatement findq=con.prepareStatement(query);
        findq.setString(1,payment_id);
        ResultSet result=findq.executeQuery();
        String student_id=null, subject_id=null, reciept_id=null;
        while (result.next()){
            student_id=result.getString("student_id");
            subject_id=result.getString("subject_id");
            reciept_id=result.getString("reciept_id");
        }
        return new payment(payment_id,student_id,subject_id,reciept_id);
    }
}
