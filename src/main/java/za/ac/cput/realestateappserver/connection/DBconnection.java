/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.realestateappserver.connection;

//import java.sql.*;

import java.sql.*;


/**
 * DB Connection 
 * @author Manasseh Barnes
 */
public class DBconnection {
    public static Connection derbyConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/RealEstateCompany";
        String user = "ADMINISTRATOR";
        String password = "admin";
        return DriverManager.getConnection(url, user, password);
    }
}
