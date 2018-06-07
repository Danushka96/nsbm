package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;

public class postgraduate extends student{
    private Connection con=ConnectionManager.getConnection();
    private String student_id, qualification_type, institiue, yearofcompletion,course_id;
    public postgraduate(String reg_Number, String firstName ,String lastName,String gender ,String faculty,String nic, String email, String DOB, String address, String tp, String registration_date, int intake_number,String student_id, String qualification_type, String institiue, String yearofcompletion,String course_id){
        super(reg_Number, firstName, lastName, gender, faculty, nic, email, DOB, address, tp, registration_date, intake_number);
        this.student_id=student_id;
        this.qualification_type=qualification_type;
        this.institiue=institiue;
        this.yearofcompletion=yearofcompletion;
        this.course_id=course_id;
    }

    public String getStudent_id() {
        return student_id;
    }
    public String getQualification_type() {
        return qualification_type;
    }
    public String getInstitiue() {
        return institiue;
    }
    public String getYearofcompletion() {
        return yearofcompletion;
    }
    public String getCourse_id() {
        return course_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
    public void setQualification_type(String qualification_type) {
        this.qualification_type = qualification_type;
    }
    public void setInstitiue(String institiue) {
        this.institiue = institiue;
    }
    public void setYearofcompletion(String yearofcompletion) {
        this.yearofcompletion = yearofcompletion;
    }
    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void save() throws SQLException{
        super.save();
        String query="INSERT INTO postgraduates(student_id, qualification_type, institute, yearof_Completion, reg_number, course_id) VALUE (?,?,?,?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.student_id);
        insq.setString(2,this.qualification_type);
        insq.setString(3,this.institiue);
        insq.setString(4,this.yearofcompletion);
        insq.setString(5,super.getReg_Number());
        insq.setString(6,this.course_id);
        insq.execute();
    }
    public void update() throws SQLException{
        super.update();
        String query="UPDATE postgraduates SET qualification_type=?, institute=?, yearof_Completion=?, course_id=? WHERE student_id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.qualification_type);
        upq.setString(2,this.institiue);
        upq.setString(3,this.yearofcompletion);
        upq.setString(4,this.course_id);
        upq.setString(5,this.student_id);
        upq.execute();
    }
    public void delete() throws SQLException{
        String query="DELETE FROM postgraduates WHERE student_id=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.student_id);
        delq.execute();
    }
    public static postgraduate findPostgraduate(String student_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM postgraduates WHERE student_id=?";
        PreparedStatement fincq=con.prepareStatement(query);
        fincq.setString(1,student_id);
        ResultSet result=fincq.executeQuery();
        String qualification_type=null, institute=null, yearof_Completion=null,reg_number=null, course_id=null;
        while (result.next()){
            qualification_type=result.getString("qualification_type");
            institute=result.getString("institute");
            yearof_Completion=result.getString("yearof_Completion");
            reg_number=result.getString("reg_number");
            course_id=result.getString("course_id");
        }
        student stu = findstudent(reg_number);
        return new postgraduate(reg_number,stu.getFirstName(),stu.getLastName(),stu.getGender(),stu.getFaculty(),stu.getNic(),stu.getEmail(),stu.getDob(),stu.getAddress(),stu.getMobile(),stu.getRegistration_date(),stu.getIntake_number(),student_id,qualification_type,institute,yearof_Completion,course_id);
    }
}
