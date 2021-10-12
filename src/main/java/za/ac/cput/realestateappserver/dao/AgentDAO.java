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

import za.ac.cput.realestateapp.domain.agent;

/**
 * AgentDAO functionality
 * @author Manasseh Barnes - 218009615
 */
public class AgentDAO {
    private final Connection con;
    
    private PreparedStatement ps;
    
    private boolean added = false;
    
    public AgentDAO() throws SQLException {
        this.con = DBconnection.derbyConnection();
    }
    
    public boolean addAgent(agent agent) {
            try{
                String insertSQL = "INSERT INTO agent (employee_id, name, surname, mobile_number, email, is_Active) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = this.con.prepareStatement(insertSQL);
                    System.out.println("Inserting Values");
                ps.setInt(1,agent.getEmployeeID());
                ps.setString(2,agent.getName());
                ps.setString(3,agent.getSurname());
                ps.setInt(4,agent.getMobileNum());
                ps.setString(5,agent.getEmailAddress());
                ps.setBoolean(6, agent.isIsActive());
                    System.out.println("dao add Agent, executing...");
                ps.executeUpdate();
                    System.out.println("dao add Agent, completed...");
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
    
    //DEBUGGING
    /*
    public static void main(String[] args) {  
        try {
            AgentDAO dao = new AgentDAO();
            dao.addAgent(new agent(1, "manny", "barnes", 456231, "manny@gmail.com", true));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    */
}
