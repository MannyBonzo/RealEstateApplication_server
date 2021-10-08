/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.realestateappserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.cput.realestateappserver.connection.DBconnection;

import za.ac.cput.realestateapp.domain.customer;

/**
 *
 * @author smann
 */
public class CustomerDAO {
    private final Connection con;
    
    private PreparedStatement ps;
    
    public CustomerDAO() throws SQLException {
        this.con = DBconnection.derbyConnection();
    }
    
    public boolean addCustomer(customer customer) {
        boolean added = false;
            try{
                String insertSQL = "INSERT INTO customer (customer_id, name, surname, phone_number, email) "
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
        return added = true;
    }
    /*
    public static void main(String[] args) {  
        try {
            CustomerDAO dao = new CustomerDAO();
            dao.addCustomer(new customer(456,"123","123",123,"123"));
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
}
