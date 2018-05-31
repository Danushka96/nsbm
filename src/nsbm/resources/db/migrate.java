package nsbm.resources.db;

import nsbm.controllers.ConnectionManager;

import java.io.*;
import java.sql.*;

public class migrate implements Serializable{
    private static void mirateFresh() throws SQLException, IOException {
        String[] files= new String[]{"create_universityMember_table.sql","create_students_table.sql"};
        Connection con=ConnectionManager.getConnection();
        ScriptRunner runner=new ScriptRunner(con,false,false);

        dropdatabase();
        String current=new File("").getCanonicalPath();
        String filesql;
        for(String file:files){
            //System.out.println(current);
            filesql=current+"\\"+ "src" +"\\"+"nsbm" +"\\" + "resources" +"\\"+"db"+"\\"+"migrations"+"\\"+file;
            //System.out.println(filesql);
            runner.runScript(new BufferedReader(new FileReader(filesql)));
        }
    }
    private static void dropdatabase() throws SQLException{
        Connection con=ConnectionManager.getConnection();
        //Drop Current Tables
        String dropquery="DROP DATABASE nsbm;";
        String createquery="CREATE database nsbm;";
        Statement droper=con.createStatement();
        droper.execute(dropquery);
        droper.execute(createquery);
    }
    public static void main(String args[]) throws SQLException, IOException {
        mirateFresh();
    }
}
