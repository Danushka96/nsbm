package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class instructor extends UniversityMemeber {
    private String instructor_id;
    private Connection con=ConnectionManager.getConnection();
    public instructor(String nic,String faculty,String firstName,String lastName,String gender,String email, String dob, String address, String mobile,String instructor_id){
        super(nic, faculty, firstName, lastName, gender, email, dob, address, mobile);
        this.instructor_id=instructor_id;
    }

    public String getInstructor_id(){
        return instructor_id;
    }

    @Override
    public boolean save() throws SQLException {
        super.save();
        String query="INSERT INTO instructors (id, nic) VALUE (?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.instructor_id);
        insq.setString(2,super.getNic());
        int action= insq.executeUpdate();
        return action > 0;
    }

    @Override
    public boolean update() throws SQLException {
        super.update();
        String quety="UPDATE instructors set id=?, nic=? WHERE id=?";
        PreparedStatement upq=con.prepareStatement(quety);
        upq.setString(1,this.instructor_id);
        upq.setString(2,super.getNic());
        upq.setString(3,this.instructor_id);
        int action= upq.executeUpdate();
        return action > 0;
    }

    @Override
    public void delete() throws SQLException {
        super.delete();
        String query="DELETE FROM instructors WHERE id=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.instructor_id);
        delq.execute();
    }

    public static instructor findInstructor(String instructor_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM instructors WHERE id=?";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,instructor_id);
        //System.out.println(selectq);
        ResultSet result=selectq.executeQuery();
        String nic=null;
        while (result.next()){
            nic=result.getString("nic");
        }
        UniversityMemeber mem=findmember(nic);
        return new instructor(nic,mem.getFaculty(),mem.getFirstName(),mem.getLastName(),mem.getGender(),mem.getEmail(),mem.getDob(),mem.getAddress(),mem.getMobile(),instructor_id);
    }

    public static ArrayList<instructor> getall() throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM instructors";
        PreparedStatement selectq=con.prepareStatement(query);
        ResultSet result=selectq.executeQuery();
        ArrayList<instructor> all=new ArrayList<>();
        while (result.next()){
            String insid = result.getString("id");
            all.add(findInstructor(insid));
        }
        return all;
    }

}