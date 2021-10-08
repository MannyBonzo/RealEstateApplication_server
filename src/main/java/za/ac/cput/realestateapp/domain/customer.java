/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.realestateapp.domain;

import java.io.Serializable;

/**
 * customer worker class - server side 
 * @author Manasseh Barnes - 218009615
 */

public class customer implements Serializable{
    private int mobileNumber,custID;
    private String Name, Surname, emailAddress;
    
    public customer() {
    }
    
    public customer(int custID, String Name, String Surname, int mobileNumber, String emailAddress) {
        this.custID = custID;
        this.Name = Name;
        this.Surname = Surname;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
