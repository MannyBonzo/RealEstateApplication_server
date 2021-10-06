/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.realestateappserver.domain;

/**
 *
 * @author smann
 */
public class customer {
    private int custID, mobileNumber, IDnumber;
    private String Name, Surname, emailAddress;
    

    public customer(int custID, int mobileNumber, int IDnumber, String Name, String Surname, String emailAddress) {
        this.mobileNumber = mobileNumber;
        this.IDnumber = IDnumber;
        this.Name = Name;
        this.Surname = Surname;
        this.emailAddress = emailAddress;
        this.custID = custID;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(int IDnumber) {
        this.IDnumber = IDnumber;
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

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    @Override
    public String toString() {
        return "customer{" + "custID=" + custID + ", mobileNumber=" + mobileNumber + ", IDnumber=" + IDnumber + ", Name=" + Name + ", Surname=" + Surname + ", emailAddress=" + emailAddress + '}';
    }
    
}
