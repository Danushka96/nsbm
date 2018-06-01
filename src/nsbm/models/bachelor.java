package nsbm.models;

import java.sql.SQLException;

public class bachelor extends course{
    bachelor(String code,String faculty,String name, int credits,int numberofyears, int can_extend){
        super(code, faculty, name, credits, numberofyears, can_extend);
    }
    public void save() throws SQLException{
        super.save();
    }
    public void update() throws SQLException{
        super.update();
    }
    public void delete() throws SQLException{

    }
}
