/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.realestateappserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import za.ac.cput.realestateapp.domain.house;
import za.ac.cput.realestateappserver.connection.DBconnection;

/**
 * HouseDAO functionality
 * @author Manasseh Barnes - 218009615
 */
public class HouseDAO {
    private final Connection con;
    
    private PreparedStatement ps;
    
    private boolean added = false;
    
    public HouseDAO() throws SQLException {
        this.con = DBconnection.derbyConnection();
    }
    
    public boolean addHouse(house house) {
            try{
                String insertSQL = "INSERT INTO house (house_id, house_number, street_name, area, number_of_rooms, rent_price, is_Available) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = this.con.prepareStatement(insertSQL);
                ps.setInt(1,house.getHouseID());
                ps.setInt(2,house.getHouseNum());
                ps.setString(3,house.getStreetName());
                ps.setString(4,house.getArea());
                ps.setInt(5,house.getNumofRooms());
                ps.setInt(6,house.getRentalPrice());
                ps.setBoolean(7,house.isIsAvailable());
                    System.out.println("dao add new house, executing...");
                ps.executeUpdate();
                    System.out.println("dao add new house, completed...");
            }
            catch(SQLException sqle) {
                sqle.printStackTrace();
            }
            finally {
                try{
                    if(ps != null){
                        ps.close();
                        con.close();
                    }
                }
                catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        return true;
    }
    //DEBUGGING
    /*
    public static void main(String[] args) {  
        try {
            HouseDAO dao = new HouseDAO();
            dao.addHouse(new house(52, 50, "Betty", "Blue Downs", 9, 1000, true));
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    */
}
