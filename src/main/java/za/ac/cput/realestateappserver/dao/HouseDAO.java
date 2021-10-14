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
                String insertSQL = "INSERT INTO house (house_id, house_number, street_name, area, number_of_rooms, rent_price, type, is_Available) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = this.con.prepareStatement(insertSQL);
                ps.setInt(1,house.getHouseID());
                ps.setInt(2,house.getHouseNum());
                ps.setString(3,house.getStreetName());
                ps.setString(4,house.getArea());
                ps.setInt(5,house.getNumofRooms());
                ps.setInt(6,house.getRentalPrice());
                ps.setString(7, house.getType());
                ps.setBoolean(8,house.isIsAvailable());
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
    
    public List<String> getAllType_Available(String type) throws SQLException {
        List<String> houseID = new ArrayList<>();
            
        String getAll_type1 = "SELECT house_id FROM house WHERE type = 'Free Standing House' AND is_available = true";
        String getAll_type2 = "SELECT house_id FROM house WHERE type = 'Condo' AND is_available = true";
        String getAll_type3 = "SELECT house_id FROM house WHERE type = 'Flat' AND is_available = true";
        String getAll_type4 = "SELECT house_id FROM house WHERE type = 'Mansion' AND is_available = true";
        
        String type1 = "Free Standing House";
        String type2 = "Condo";
        String type3 = "Flat";
        String type4 = "Mansion";
        
        //House chosen
        if(type.equalsIgnoreCase(type1)) {
          PreparedStatement ps = this.con.prepareStatement(getAll_type1);
          ResultSet rs = ps.executeQuery();
          try{
            while(rs.next()) {
                int house_id = rs.getInt("house_id");
                
                houseID.add(String.valueOf(house_id));
            }
          }    
          catch(SQLException sqle) {
                System.out.println();
          }
          
        System.out.println(houseID); //Debug
        return houseID;
        }
        //CONDO chosen
        else if(type.equalsIgnoreCase(type2)) {
            PreparedStatement ps = this.con.prepareStatement(getAll_type2);
            ResultSet rs = ps.executeQuery();
            try{
                while(rs.next()) {
                    int house_id = rs.getInt("house_id");
                
                    houseID.add(String.valueOf(house_id));
                } 
            } 
            catch(SQLException sqle) {
                System.out.println();
            }   
            
        System.out.println(houseID); //Debug
        return houseID;
        }
        //Flat chosen
        else if(type.equalsIgnoreCase(type3)) {
            PreparedStatement ps = this.con.prepareStatement(getAll_type3);
            ResultSet rs = ps.executeQuery();
            try{
                while(rs.next()) {
                    int house_id = rs.getInt("house_id");
                
                    houseID.add(String.valueOf(house_id));
                } 
            } 
            catch(SQLException sqle) {
                System.out.println();
            }   
            
        System.out.println(houseID); //Debug
        return houseID;
        }
        //Mansion chosen
        else if(type.equalsIgnoreCase(type4)) {
            PreparedStatement ps = this.con.prepareStatement(getAll_type4);
            ResultSet rs = ps.executeQuery();
            try{
                while(rs.next()) {
                    int house_id = rs.getInt("house_id");
                
                    houseID.add(String.valueOf(house_id));
                } 
            } 
            catch(SQLException sqle) {
                System.out.println();
            }   
            
        System.out.println(houseID); //Debug
        return houseID;
        }
    
        return houseID;
    }
    
    public List<String> getAllHouseINFO(String available) throws SQLException {
        house house = null;
        List<String> houseInfo = new ArrayList<>();
        
        String insertSQL = "SELECT house_number, street_name, number_of_rooms, rent_price FROM house WHERE house_id = "+available+"";
        
        PreparedStatement ps = this.con.prepareStatement(insertSQL);
        ResultSet rs = ps.executeQuery();
            try{
                while(rs.next()) {
                    int house_num = rs.getInt("house_number");
                    String street_name = rs.getString("street_name");
                    int num_rooms = rs.getInt("number_of_rooms");
                    int rent_price = rs.getInt("rent_price");

                    houseInfo.add(String.valueOf(house_num));
                    houseInfo.add(street_name);
                    houseInfo.add(String.valueOf(num_rooms));
                    houseInfo.add(String.valueOf(rent_price));
                    
                    System.out.println("dao fetching house info, executing...");
               
                    System.out.println("dao fetching house info, completed...");
                }
            }
            catch(SQLException sqle) {
                 System.out.println();
            }
        System.out.println(houseInfo); //Debug
        return houseInfo; 
    }
    
    public List<String> getAll_houseID() throws SQLException {
        List<String> houseID = new ArrayList<>();
            
        String getAll_houseid = "SELECT house_id FROM house";
 
        //House chosen
        
          PreparedStatement ps = this.con.prepareStatement(getAll_houseid);
          ResultSet rs = ps.executeQuery();
          try{
            while(rs.next()) {
                int house_id = rs.getInt("house_id");
                
                houseID.add(String.valueOf(house_id));
            }
          }    
          catch(SQLException sqle) {
                System.out.println();
          }
        System.out.println(houseID); //Debug
        return houseID;
    }
    
    public List<String> getAllEDITHouseINFO(String rental) throws SQLException {
        house house = null;
        List<String> EDIThouseInfo = new ArrayList<>();
        
        String insertSQL = "SELECT house_number, street_name, area, number_of_rooms, rent_price, is_available FROM house WHERE house_id = "+rental+"";
        
        PreparedStatement ps = this.con.prepareStatement(insertSQL);
        ResultSet rs = ps.executeQuery();
            try{
                while(rs.next()) {
                    int house_num = rs.getInt("house_number");
                    String street_name = rs.getString("street_name");
                    String area = rs.getString("area");
                    int num_rooms = rs.getInt("number_of_rooms");
                    int rent_price = rs.getInt("rent_price");
                    String is_available = rs.getString("is_available");

                    EDIThouseInfo.add(String.valueOf(house_num));
                    EDIThouseInfo.add(street_name);
                    EDIThouseInfo.add(area);
                    EDIThouseInfo.add(String.valueOf(num_rooms));
                    EDIThouseInfo.add(String.valueOf(rent_price));
                    EDIThouseInfo.add(is_available);
                    
                    System.out.println("dao fetching house info, executing...");
               
                    System.out.println("dao fetching house info, completed...");
                }
            }
            catch(SQLException sqle) {
                 System.out.println();
            }
        System.out.println(EDIThouseInfo); //Debug
        return EDIThouseInfo; 
    }
    //DEBUGGING
    /*
    public static void main(String[] args) {  
        try {
            HouseDAO dao = new HouseDAO();
            //dao.getAllLocations();
            dao.getAllEDITHouseINFO("7");
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    */
}
