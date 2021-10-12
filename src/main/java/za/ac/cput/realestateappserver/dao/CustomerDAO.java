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

import za.ac.cput.realestateappserver.connection.DBconnection;
import za.ac.cput.realestateapp.domain.customer;

/**
 * CustomerDAO functionality
 * @author Manasseh Barnes - 218009615
 */
public class CustomerDAO {
    private final Connection con;
    
    private PreparedStatement ps;
    
    private boolean added = false;
    
    public CustomerDAO() throws SQLException {
        this.con = DBconnection.derbyConnection();
    }
    
    public boolean addCustomer(customer customer) {
            try{
                String insertSQL = "INSERT INTO customer (customer_id, name, surname, mobile_number, email) "
                + "VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = this.con.prepareStatement(insertSQL);
                ps.setInt(1,customer.getCustID());
                ps.setString(2,customer.getName());
                ps.setString(3,customer.getSurname());
                ps.setInt(4,customer.getMobileNumber());
                ps.setString(5,customer.getEmailAddress());
                    System.out.println("dao add Customer, executing...");
                ps.executeUpdate();
                    System.out.println("dao add Customer, completed...");
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
        return true;
    }
    
    public List<customer> getAllDetails() throws SQLException { 
        List<customer> customers = new ArrayList<>();
            
        String getAll_SQL = "SELECT * FROM customer";

        PreparedStatement ps = this.con.prepareStatement(getAll_SQL);
        ResultSet rs = ps.executeQuery();
        try {
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int mobile_num = rs.getInt("mobile_number");
                String email = rs.getString("email");
                
                customer customer = new customer(customer_id, name, surname, mobile_num, email);
                customers.add(customer);
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
        System.out.println(customers); //Debug
        return customers;
    }
    
    //DEBUGGING
    /*
    public static void main(String[] args) {  
        try {
            CustomerDAO dao = new CustomerDAO();
            dao.addCustomer(new customer(456,"123","123",123,"123"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    */
}
