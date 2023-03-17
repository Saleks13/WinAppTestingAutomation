package com.service;

import java.sql.*;
import java.util.HashMap;
import java.util.Objects;

public class MdbReader {


    public static int getRowsCount(String mdbFilePath) throws SQLException {

        int i = 0;
        //making URL with driver:
        String dbURL = "jdbc:ucanaccess://" + mdbFilePath;
        //connecting to the DB via driver:
        Connection conn = DriverManager.getConnection(dbURL);
        //new statement initialization:
        Statement stmt = conn.createStatement();
        //Query to get the data from DB
        String query = "SELECT * FROM OUTPUT";
        //result of the query
        ResultSet rs = stmt.executeQuery(query);
        //Loop to go through each row in the resultset
        while (rs.next()) {
            i++;
        }
        // Clean up resources
        rs.close();
        stmt.close();
        conn.close();
        //return number of rows
        return i;
    }


    public static void printResultsForPrinter(String mdbFilePath, String printerModel) throws SQLException {

        String dbURL = "jdbc:ucanaccess://" + mdbFilePath;
        Connection conn = DriverManager.getConnection(dbURL);

        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM OUTPUT WHERE NAME = " + "'" + printerModel + "'";
        ResultSet rs = stmt.executeQuery(query);

        HashMap<String, String> testResults = new HashMap<String, String>();

        while (rs.next()) {

            // Read the data from the result set
            String printerName = rs.getString("NAME");
            String barcodeName = rs.getString("KEY_SUB");
            String actualResult = rs.getString("CMP_DATA");
            String expectedResult = rs.getString("REF_DATA");

            if (printerName.contains(printerModel)) {

                if (Objects.equals(actualResult, expectedResult)) {
                    testResults.put(barcodeName, "OK");
                } else {
                    testResults.put(barcodeName, "Failed");
                }
            }
        }

        System.out.println(testResults);
        // Clean up resources
        rs.close();
        stmt.close();
        conn.close();
    }


    public static Boolean isTestPassed(String mdbFilePath, String printerModel) throws SQLException {

        Boolean isTestPassed = false;
        int fails = 0;
        int passes = 0;
        String dbURL = "jdbc:ucanaccess://" + mdbFilePath;
        Connection conn = DriverManager.getConnection(dbURL);

        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM OUTPUT WHERE NAME = " + "'" + printerModel + "'";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            // Read the data from the result set
            String printerName = rs.getString("NAME");
            String barcodeName = rs.getString("KEY_SUB");
            String actualResult = rs.getString("CMP_DATA");
            String expectedResult = rs.getString("REF_DATA");
            if (Objects.equals(actualResult, expectedResult)) {
                passes++;
            } else {
                fails++;
            }
        }
        // Clean up resources
        rs.close();
        stmt.close();
        conn.close();
        if (fails != 0) {
            isTestPassed = false;
        }
        else {
            isTestPassed = true;
        }
        return isTestPassed;
    }

}
