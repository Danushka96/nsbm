package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyPermission;

public class labtime {
    private Connection con=ConnectionManager.getConnection();
    String lab_id, timeslot_id, instructor_id;
    labtime(String lab_id, String timeslot_id, String instructor_id){
        this.lab_id=lab_id;
        this.timeslot_id=timeslot_id;
        this.instructor_id=instructor_id;
    }

    public void setTimeslot_id(String timeslot_id) {
        this.timeslot_id = timeslot_id;
    }
    public void setLab_id(String lab_id) {
        this.lab_id = lab_id;
    }
    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getTimeslot_id() {
        return timeslot_id;
    }
    public String getInstructor_id() {
        return instructor_id;
    }
    public String getLab_id() {
        return lab_id;
    }

    public void save() throws SQLException{
        String query="INSERT INTO labtime (lab_id, timeslot_id, instructor_id) VALUES (?,?,?)";
        PreparedStatement insq=con.prepareStatement(query);
        insq.setString(1,this.lab_id);
        insq.setString(2,this.timeslot_id);
        insq.setString(3,this.instructor_id);
        insq.execute();
    }

    public void update() throws SQLException{
        String query="UPDATE labtime SET timeslot_id=?, instructor_id=? WHERE lab_id=?";
        PreparedStatement upq=con.prepareStatement(query);
        upq.setString(1,this.timeslot_id);
        upq.setString(2,this.instructor_id);
        upq.setString(3,this.lab_id);
        upq.execute();
    }

    public void delete() throws SQLException{
        String query="DELETE FROM labtime WHERE lab_id=?";
        PreparedStatement delq=con.prepareStatement(query);
        delq.setString(1,this.lab_id);
        delq.execute();
    }

    public static labtime[] findlabTime(String lab_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM labtime WHERE lab_id=? LIMIT 1";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,lab_id);
        ResultSet result=selectq.executeQuery();
        int count=0;
        while (result.next()){
            count++;
        }
        labtime[] all=new labtime[count];
        String timeslot_id=null, instructor_id=null;
        while (result.next()){
            timeslot_id=result.getString("timeslot_id");
            instructor_id=result.getString("instructor_id");
            all[count-1]=new labtime(lab_id,timeslot_id,instructor_id);
            count--;
        }
        return all;
    }
}
