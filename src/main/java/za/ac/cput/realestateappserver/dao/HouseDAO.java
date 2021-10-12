/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.realestateappserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<String> getAllLocations() throws SQLException {
        List<String> houseLocation = new ArrayList<>();
            
        String getAll_SQL = "SELECT * FROM house";
        
        PreparedStatement ps = this.con.prepareStatement(getAll_SQL);
        ResultSet rs = ps.executeQuery();
        try{
            while(rs.next()) {
                /*
                int house_id = rs.getInt("house_id");
                int house_number = rs.getInt("house_number");
                String street_name = rs.getString("street_name");
                */
                String area = rs.getString("area");
                
                /*
                int numRooms = rs.getInt("number_of_rooms");
                int rent = rs.getInt("rent_price");
                boolean is_available = rs.getBoolean("is_available");
                
                house house = new house(house_id, house_number, street_name, area, numRooms, rent, is_available);
                */
                
                houseLocation.add(area);
            }
        }
        catch(SQLException sqle) {
                System.out.println();
        }
        finally {
            try{
                if(ps != null){
                    ps.close();
                    con.close();
                }
            }
            catch(Exception exception) {
                    
            }
        }
        System.out.println(houseLocation); //Debug
        return houseLocation;
    }
    //DEBUGGING
    /*
    public static void main(String[] args) {  
        try {
            HouseDAO dao = new HouseDAO();
            dao.getAllLocations();
            //dao.addHouse(new house(52, 50, "Betty", "Blue Downs", 9, 1000, true));
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    */
}
