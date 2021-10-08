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
public class agent {
    private int employeeID, IDnumber, mobileNum;
    private String Name, Surname, emailAddress;
    private boolean isActive;

    public agent(int employeeID, int IDnumber, String Name, String Surname, int mobileNum, String emailAddress, boolean isActive) {
        this.employeeID = employeeID;
        this.IDnumber = IDnumber;
        this.Name = Name;
        this.Surname = Surname;
        this.mobileNum = mobileNum;
        this.emailAddress = emailAddress;
        this.isActive = isActive;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(int IDnumber) {
        this.IDnumber = IDnumber;
    }

    public int getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(int mobileNum) {
        this.mobileNum = mobileNum;
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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "agent{" + "employeeID=" + employeeID + ", IDnumber=" + IDnumber + ", mobileNum=" + mobileNum + ", Name=" + Name + ", Surname=" + Surname + ", emailAddress=" + emailAddress + ", isActive=" + isActive + '}';
    }

}
