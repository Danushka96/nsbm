package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class lecturer extends UniversityMemeber{
    Connection con=ConnectionManager.getConnection();
    private String lecture_id,researchSpeciality,office_number;
    public lecturer(String nic,String faculty,String firstName,String lastName,String gender,String email, String dob, String address, String mobile,String lecture_id, String researchSpeciality, String office_number){
        super(nic, faculty, firstName, lastName, gender, email, dob, address, mobile);
        this.lecture_id=lecture_id;
        this.researchSpeciality=researchSpeciality;
        this.office_number=office_number;
    }

    public String getLecture_id() {
        return lecture_id;
    }
    public String getResearchSpeciality() {
        return researchSpeciality;
    }
    public String getOffice_number() {
        return office_number;
    }

    public void setLecture_id(String lecture_id) {
        this.lecture_id = lecture_id;
    }
    public void setResearchSpeciality(String researchSpeciality) {
        this.researchSpeciality = researchSpeciality;
    }
    public void setOffice_number(String office_number) {
        this.office_number = office_number;
    }

    @Override
    public boolean save() throws SQLException {
        super.save();
        String query="INSERT INTO lecturers (id, researchSpeciality, office_number, nic) VALUE (?,?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.lecture_id);
        insq.setString(2,this.researchSpeciality);
        insq.setString(3,this.office_number);
        insq.setString(4,super.getNic());
        int action= insq.executeUpdate();
        return action > 0;
    }

    @Override
    public boolean update() throws SQLException {
        super.update();
        String query="UPDATE lecturers SET researchSpeciality=?,office_number=?, nic=? WHERE id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.researchSpeciality);
        upq.setString(2,this.office_number);
        upq.setString(3,super.getNic());
        upq.setString(4,this.lecture_id);
        int action= upq.executeUpdate();
        return action > 0;
    }

    @Override
    public void delete() throws SQLException {
        //super.delete();
        String query="DELETE FROM lecturers WHERE id=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.lecture_id);
        delq.execute();
    }

    public static lecturer findLecturer(String lecture_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM lecturers WHERE id=?";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,lecture_id);
        ResultSet result=selectq.executeQuery();
        String researchSpeciality=null,office_number=null,nic=null;
        while (result.next()){
            researchSpeciality=result.getString("researchSpeciality");
            office_number=result.getString("office_number");
            nic=result.getString("nic");
        }
        UniversityMemeber mem=findmember(nic);
        return new lecturer(nic,mem.getFaculty(),mem.getFirstName(),mem.getLastName(),mem.getGender(),mem.getEmail(),mem.getDob(),mem.getAddress(),mem.getMobile(),lecture_id,researchSpeciality,office_number);
    }

    public static ArrayList<lecturer> getall() throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM lecturers";
        PreparedStatement selectq=con.prepareStatement(query);
        ResultSet result=selectq.executeQuery();
        ArrayList<lecturer> all=new ArrayList<>();
        while (result.next()){
            String lecturer_id = result.getString("id");
            all.add(findLecturer(lecturer_id));
        }
        return all;
    }
}
