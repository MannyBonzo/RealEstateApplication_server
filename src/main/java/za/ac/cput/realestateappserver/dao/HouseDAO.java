/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.realestateappserver.dao;

import java.sql.Connection;
import java.sql.SQLException;
import za.ac.cput.realestateappserver.connection.DBconnection;

/**
 *
 * @author smann
 */
public class HouseDAO {
    private final Connection con;
    
    public HouseDAO() throws SQLException {
        this.con = DBconnection.derbyConnection();
    }
}