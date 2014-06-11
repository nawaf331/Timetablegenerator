/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DBConnectTT implements DBInterfaceTT {

    //SQLITE database Drivers
    public static String driver = "org.sqlite.JDBC";
    public static String DB_URL = "jdbc:sqlite:TimeTable.sqlite";
    public static Connection connection = null;

    public static void getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to database...");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectTT.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cant connect");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnectTT.class.getName()).log(Level.SEVERE, null, ex);
        }

        Statement stmt, stmt1, stmt2, stmt3;

        String query1 = "CREATE TABLE " + TT_FACULTY_DETAILS + " ('" + TT_F_ID + "' VARCHAR PRIMARY KEY , '" + TT_F_NAME + "' VARCHAR NOT NULL  )";
        String query2 = "CREATE TABLE " + TT_SUBJECT_DETAILS + " ('" + TT_SUB_CODE + "' VARCHAR PRIMARY KEY , '" + TT_SUB_SUBNAME + "' VARCHAR NOT NULL , '" + TT_SUB_TYPE + "' VARCHAR NOT NULL , '" + TT_SUB_FID + "' VARCHAR  , '" + TT_SUB_SEM + "' VARCHAR NOT NULL )";//, FOGEIGN KEY ("+ TT_SUB_FID + ") REFERENCES " + TT_FACULTY_DETAILS + " (" + TT_F_ID + ") ON DELETE CASCADE  )";
        String query3 = "CREATE TABLE " + TT_CLASSROOM_DETAILS + " ('" + TT_CR_NO + "' VARCHAR PRIMARY KEY , '" + TT_CR_TYPE + "' VARCHAR NOT NULL , '" + TT_CR_STRENGTH + "' VARCHAR NOT NULL )";
        String query4 = "CREATE TABLE " + TT_TT + "('" + TT_batch + "' INTEGER , '" + TT_rowid + "' INTEGER , '" + TT_colid + "' INTEGER ,'" + TT_code + "' VARCHAR )";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query1);
            System.out.println("faculty_details created");
        } catch (SQLException ex) {
            //Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DBCONNECT-GETconnection-try-catch" + ex.getMessage());
        }
        try {
            stmt1 = connection.createStatement();
            stmt1.executeUpdate(query2);
            System.out.println("subject_details created");
        } catch (SQLException ex) {
            //Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DBCONNECT-GETconnection-try-catch" + ex.getMessage());
        }
        try {
            stmt2 = connection.createStatement();
            stmt2.executeUpdate(query3);
            System.out.println("class room created");
        } catch (SQLException ex) {
            //Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DBCONNECT-GETconnection-try-catch" + ex.getMessage());
        }
        try {
            stmt3 = connection.createStatement();
            stmt3.executeUpdate(query4);
            System.out.println("TimeTable DB table  created");
        } catch (SQLException ex) {
            //Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DBCONNECT-GETconnection-try-catch" + ex.getMessage());
        }

    }
}
