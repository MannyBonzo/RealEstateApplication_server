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
                String insertSQL = "INSERT INTO rent_transaction (transaction_id, customer_id, customer_surname, house_id, rent_price, commission, is_available) "
                + "VALUES (?, ?, ?, ?, ? ,? ,?)";
                PreparedStatement ps = this.con.prepareStatement(insertSQL);
                    System.out.println("\nInserting Values");
                    System.out.println("Recording transaction...");
                ps.setInt(1,transaction.id_increment());
                ps.setInt(2,transaction.getCustomer_id());
                ps.setString(3,transaction.getCustomer_surname());
                ps.setInt(4,transaction.getHouse_id());
                ps.setInt(5,transaction.getRent_price());
                ps.setInt(6,transaction.commission());
                ps.setBoolean(7,transaction.isIs_available());
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
    
    public List<String> getAll_transData() throws SQLException {
        List<String> transData = new ArrayList<>();
            
        String getAll_transID= "SELECT transaction_id, customer_id, customer_surname, house_id, rent_price, commission FROM rent_transaction";
 
        //Fetch Transactions
        
          PreparedStatement ps = this.con.prepareStatement(getAll_transID);
          ResultSet rs = ps.executeQuery();
          try{
            while(rs.next()) {
                int transID = rs.getInt("transaction_id");
                int customerID = rs.getInt("customer_id");
                String customerSurname = rs.getString("customer_surname");
                int HouseID = rs.getInt("house_id");
                int rent_price = rs.getInt("rent_price");
                int commission = rs.getInt("commission");
                
                
                transData.add(String.valueOf(transID));
                transData.add(String.valueOf(customerID));
                transData.add(customerSurname);
                transData.add(String.valueOf(HouseID));
                transData.add(String.valueOf(rent_price));
                transData.add(String.valueOf(commission));
            }
          }    
          catch(SQLException sqle) {
                System.out.println();
          }
        System.out.println(transData); //Debug
        return transData;
    }
    
    /*
    public boolean updateHouseAvailablity(String update) {
        try{
            String updateSQL = "UPDATE house SET house.is_available = "+update+" WHERE house_id ";
        }
    }
    */
    //DEBUGGING
    /*
    public static void main(String[] args) {  
        try {
            TransactionDAO dao = new TransactionDAO();
            //dao.getAll_transData();
            //dao.recordTransaction(new rentTransaction(0, 23, "Barnes", 22, 500, 0, false));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    */
}
