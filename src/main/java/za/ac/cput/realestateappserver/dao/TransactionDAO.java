/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.realestateappserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import za.ac.cput.realestateappserver.connection.DBconnection;

import za.ac.cput.realestateapp.domain.rentTransaction;
import za.ac.cput.realestateapp.domain.house;

/**
 * TransactionDAO functionality
 * @author Manasseh Barnes
 */
public class TransactionDAO {
    private final Connection con;
    
    private PreparedStatement ps;
    
    private boolean added = false;
    
    public TransactionDAO() throws SQLException {
        this.con = DBconnection.derbyConnection();
    }
    
    public boolean recordTransaction(rentTransaction transaction) {
            try{
                String insertSQL = "INSERT INTO renttransaction (transaction_id, customer_id, house_id, rent_price, commission) "
                + "VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = this.con.prepareStatement(insertSQL);
                    System.out.println("\nInserting Values");
                    System.out.println("Recording transaction...");
                ps.setInt(1,transaction.id_increment());
                ps.setInt(2,transaction.getCustomer_id());
                ps.setInt(3,transaction.getHouse_id());
                ps.setInt(4,transaction.getRent_price());
                ps.setInt(5,transaction.commission());
                    System.out.println("dao add transaction, executing...");
                ps.executeUpdate();
                    System.out.println("dao add transaction, completed...");
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
    /*
    public boolean updateHouseAvailablity(String update) {
        try{
            String updateSQL = "UPDATE house SET house.is_available = "+update+" WHERE house_id ";
        }
    }
    //DEBUGGING
    /*
    public static void main(String[] args) {  
        try {
            TransactionDAO dao = new TransactionDAO();
            dao.recordTransaction(new rentTransaction(2, 1, 2, 1000, 0));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    */
}
