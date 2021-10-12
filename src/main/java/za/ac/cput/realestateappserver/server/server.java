/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.realestateappserver.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import za.ac.cput.realestateapp.domain.customer;
import za.ac.cput.realestateapp.domain.agent;
import za.ac.cput.realestateapp.domain.house;

import za.ac.cput.realestateappserver.dao.CustomerDAO;
import za.ac.cput.realestateappserver.dao.AgentDAO;
import za.ac.cput.realestateappserver.dao.HouseDAO;

/**
 *
 * @author smann
 */
public class server {
    // Server socket
    private ServerSocket listener;
    
    // Client connection
    private Socket client;
    
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    CustomerDAO customerDao;
    AgentDAO agentDao;
    HouseDAO houseDao;
    
    String request = "";
    
    /** Creates a new instance of server */
    public server() throws SQLException {
        startServer();
        listen();
        createStreams();
        processClient();
        
        startServer();
        listen();
        createStreams();
        processClient();
    }
    
    public void startServer() {
        // Create server socket
        try {
            listener = new ServerSocket(4000, 10);
        }
        catch (IOException ioe) {
          System.out.println("IO Exception: " + ioe.getMessage());
        }
    }
    
    public void listen() {
        // Start listening for client connections
        try {
          System.out.println("Server is listening");
          client = listener.accept();
          System.out.println("Connection Accepted");
          System.out.println("Now moving onto process Client");
          
        }
        catch(IOException ioe) {
            System.out.println("IO Exception: " + ioe.getMessage());
        }
    }
    
    public void createStreams(){
        try{
            out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            in = new ObjectInputStream(client.getInputStream());
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    
    public void processClient()
    {
        try {
            do {
                request = (String)in.readObject();
                if(request.equalsIgnoreCase("addCustomer")){
                        System.out.println("Client requesting addCustomer method");
                    customer customer = (customer)in.readObject();
                        //System.out.println(customer);
                    customerDao = new CustomerDAO();
                    boolean response = customerDao.addCustomer(customer);
                        System.out.println("Result of DAO add Customer success>>" + response);
                    out.writeBoolean(response);
                        out.flush(); 
                }
                else if(request.equalsIgnoreCase("addHouse")){
                        System.out.println("Client requesting addHouse method");
                    house house = (house)in.readObject();
                        System.out.println("Reading in new house information...");
                    houseDao = new HouseDAO();
                        System.out.println("adding new house...");
                    boolean response = houseDao.addHouse(house);
                        System.out.println("Result of DAO add House success>>" + response);
                    out.writeObject(response);
                        out.flush();
                }
                else if(request.equalsIgnoreCase("addAgent")){
                        System.out.println("Client requesting addAgent method");
                    agent agent = (agent)in.readObject();
                        System.out.println("Reading in new agent information...");
                    agentDao = new AgentDAO();
                        System.out.println("adding new agent...");
                    boolean response = agentDao.addAgent(agent);
                        System.out.println("Result of DAO add agent success>>" + response);
                    out.writeObject(response);
                        out.flush();
                }
            }
            while(!request.equalsIgnoreCase("terminate"));

            // Step 3:close down
            out.close();
            in.close();
            client.close();        
        }
        catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  
    public static void main(String[] args) throws SQLException
    {
        // Create application
        new server();

    }
}
