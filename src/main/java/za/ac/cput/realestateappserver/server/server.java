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
import java.util.List;

import za.ac.cput.realestateapp.domain.customer;
import za.ac.cput.realestateapp.domain.agent;
import za.ac.cput.realestateapp.domain.house;
import za.ac.cput.realestateapp.domain.rentTransaction;

import za.ac.cput.realestateappserver.dao.CustomerDAO;
import za.ac.cput.realestateappserver.dao.AgentDAO;
import za.ac.cput.realestateappserver.dao.HouseDAO;
import za.ac.cput.realestateappserver.dao.TransactionDAO;

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
          System.out.println("IO Exception: ");
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
            System.out.println("IO Exception: ");
        }
    }
    
    public void createStreams(){
        try{
            out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            in = new ObjectInputStream(client.getInputStream());
        }
        catch (IOException ioe) {
            System.out.println("IO Exception: ");
        }
    }
    
    public void processClient() {
        try {
            do {
                request = (String)in.readObject();
                if(request.equalsIgnoreCase("addCustomer")){
                        System.out.println("\nClient requesting addCustomer method");
                    customer customer = (customer)in.readObject();
                        //System.out.println(customer);
                    customerDao = new CustomerDAO();
                    boolean response = customerDao.addCustomer(customer);
                        System.out.println("Result of DAO add Customer success>>" + response + "\n");
                    out.writeBoolean(response);
                        out.flush(); 
                }
                else if(request.equalsIgnoreCase("addHouse")){
                        System.out.println("\nClient requesting addHouse method");
                    house house = (house)in.readObject();
                        System.out.println("Reading in new house information...");
                    houseDao = new HouseDAO();
                        System.out.println("adding new house...");
                    boolean response = houseDao.addHouse(house);
                        System.out.println("Result of DAO add House success>>" + response + "\n");
                    out.writeObject(response);
                        out.flush();
                }
                else if(request.equalsIgnoreCase("addAgent")){
                        System.out.println("\nClient requesting addAgent method");
                    agent agent = (agent)in.readObject();
                        System.out.println("Reading in new agent information...");
                    agentDao = new AgentDAO();
                        System.out.println("adding new agent...");
                    boolean response = agentDao.addAgent(agent);
                        System.out.println("Result of DAO add agent success>>" + response + "\n");
                    out.writeObject(response);
                        out.flush();
                }
                else if(request.equalsIgnoreCase("getAll_id")) {
                        System.out.println("Client requesting all houseID data for combobox");
                    String type = (String)in.readObject();
                        System.out.println("Reading Type of rental...");
                    houseDao = new HouseDAO();
                        //System.out.println("populating combobox...");
                    List response = houseDao.getAllType_Available(type);
                    
                        if(response !=null) {
                            System.out.println("SERVER>> " + response);
                            System.out.println("population successful");
                        }
                        else{
                            System.out.println("Sorry, could not populate combobox");
                        }
                    out.writeObject(response);
                        out.flush();  
                }
                else if(request.equalsIgnoreCase("getAll_houseInfo")) {
                        System.out.println("Client requesting all house data for TextField viewing...");
                    String available = (String)in.readObject();
                        System.out.println("Reading house_ID...");
                    houseDao = new HouseDAO();
                        //System.out.println("populating combobox...");
                    List response = houseDao.getAllHouseINFO(available);
                    
                        if(response !=null) {
                            System.out.println("SERVER>> " + response);
                            System.out.println("population successful");
                        }
                        else{
                            System.out.println("Sorry, could not populate TextFields");
                        }
                    out.writeObject(response);
                        out.flush();  
                }
                else if(request.equalsIgnoreCase("recordTransaction")){
                        System.out.println("\nClient requesting recordTransaction method");
                    rentTransaction transaction = (rentTransaction)in.readObject();
                        System.out.println("Reading in new transaction information...");
                    TransactionDAO transactionDao = new TransactionDAO();
                        System.out.println("adding new transaction...");
                    boolean response = transactionDao.recordTransaction(transaction);
                        System.out.println("Result of DAO add transaction success>>" + response + "\n");
                    out.writeObject(response);
                        out.flush();
                }
                else if(request.equalsIgnoreCase("getHouseID")){
                    System.out.println("Client requesting all houseID data for Editing combobox");
                    
                    houseDao = new HouseDAO();
                        //System.out.println("populating combobox...");    
                    List response = houseDao.getAll_houseID();
                        System.out.println("LIST >> " + response);
                        if(response !=null) {
                            System.out.println("SERVER>> " + response);
                            System.out.println("population successful\n");
                        }
                        else{
                            System.out.println("Sorry, could not populate combobox");
                        }
                    out.writeObject(response);
                        out.flush();
                }
                else if(request.equalsIgnoreCase("getAgentID")){
                    System.out.println("Client requesting all agentID data for Editing combobox");
                    
                    agentDao = new AgentDAO();
                        //System.out.println("populating combobox...");    
                    List response = agentDao.getAll_agentID();
                        System.out.println("LIST >> " + response);
                        if(response !=null) {
                            System.out.println("SERVER>> " + response);
                            System.out.println("population successful\n");
                        }
                        else{
                            System.out.println("Sorry, could not populate combobox");
                        }
                    out.writeObject(response);
                        out.flush();
                }
                else if(request.equalsIgnoreCase("getAll_EDIThouseInfo")) {
                        System.out.println("\nClient requesting all house data for TextField viewing...");
                    String rental = (String)in.readObject();
                        System.out.println("Reading house_ID...");
                    houseDao = new HouseDAO();
                        //System.out.println("populating combobox...");
                    List response = houseDao.getAllEDITHouseINFO(rental);
                    
                        if(response !=null) {
                            System.out.println("SERVER>> " + response);
                            System.out.println("population successful\n");
                        }
                        else{
                            System.out.println("Sorry, could not populate TextFields");
                        }
                    out.writeObject(response);
                        out.flush();  
                }
                //GET AGENT DETAILS FROM AGENT ID CBO
                else if(request.equalsIgnoreCase("getAll_EDITagentInfo")) {
                        System.out.println("\nClient requesting all agent data for TextField viewing...");
                    String agent_id = (String)in.readObject();
                        System.out.println("Reading Agent_ID...");
                    agentDao = new AgentDAO();
                        //System.out.println("populating combobox...");
                    List response = agentDao.getAllEDITAgentINFO(agent_id);
                    
                        if(response !=null) {
                            System.out.println("SERVER>> " + response);
                            System.out.println("population successful\n");
                        }
                        else{
                            System.out.println("Sorry, could not populate TextFields");
                        }
                    out.writeObject(response);
                        out.flush();  
                }
                else if(request.equalsIgnoreCase("updateHouse")){
                        System.out.println("\nClient requesting updateHouse method");
                    house house = (house)in.readObject();
                        System.out.println("Reading in house information...");
                    houseDao = new HouseDAO();
                        System.out.println("Updating in house information...");
                    boolean response = houseDao.updateHouse(house);
                        System.out.println("Result of DAO update house success>> " + response + "\n");
                    out.writeObject(response);
                        out.flush();
                }
                else if(request.equalsIgnoreCase("updateAgent")){
                        System.out.println("\nClient requesting updateAgent method");
                    agent agent = (agent)in.readObject();
                        System.out.println("Reading in agent information...");
                    houseDao = new HouseDAO();
                        System.out.println("Updating in agent information...");
                    boolean response = agentDao.updateAgent(agent);
                        System.out.println("Result of DAO update agent success>> " + response + "\n");
                    out.writeObject(response);
                        out.flush();
                }
                //getAll_transData
                else if(request.equalsIgnoreCase("getAll_transData")) {
                        System.out.println("\nClient requesting all getAll_transData for Table viewing...");
                    TransactionDAO transactionDao = new TransactionDAO();
                        //System.out.println("populating combobox...");
                    List response = transactionDao.getAll_transData();
                    
                        if(response !=null) {
                            System.out.println("SERVER>> " + response);
                            System.out.println("population successful\n");
                        }
                        else{
                            System.out.println("Sorry, could not populate table");
                        }
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
            System.out.println("Exception: ");;
        }
    }
  
    public static void main(String[] args) throws SQLException
    {
        // Create application
        new server();

    }
}
