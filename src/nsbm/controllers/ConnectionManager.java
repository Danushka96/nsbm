package nsbm.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionManager {
//    private static String url = "jdbc:mysql://localhost:3306/";
//    private static String drivername = "com.mysql.jdbc.Driver";
//    private static String username = "root";    // Database Username
//    private static String password = "";    // Database Password
//    private static String database = "javasql";    // Database Name
    private static Connection con;
//    private static String urlString1;

    static Connection getConnection(){
//        String urlString =url+database;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            try{
                con=DriverManager.getConnection("jdbc:mysql://localhost/javasql","root","");
            } catch (SQLException e) {
                //System.out.println("Error Connecting with the Database");
//                databaseAlert.display("Databse Error","Check Database Connection");
                databaseAlert alert = new databaseAlert();
                alert.show("Database Error");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Not Found");
        }
        return con;
    }
}
