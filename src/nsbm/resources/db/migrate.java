package nsbm.resources.db;

import nsbm.controllers.ConnectionManager;

import java.io.*;
import java.sql.*;

public class migrate implements Serializable{
    private static void mirateFresh() throws SQLException, IOException {
        String current=new File("").getCanonicalPath();
        String path=current+"\\"+ "src" +"\\"+"nsbm" +"\\" + "resources" +"\\"+"db"+"\\"+"migrations";

        File migration = new File(path);

        String[] files= migration.list();
        Connection con=ConnectionManager.getConnection();
        ScriptRunner runner=new ScriptRunner(con,false,false);

        dropdatabase();
        String filesql;
        assert files != null;
        for(String file:files){
            System.out.println(file+"\t\t migrated Successfully");
            filesql=path+"\\"+file;
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
