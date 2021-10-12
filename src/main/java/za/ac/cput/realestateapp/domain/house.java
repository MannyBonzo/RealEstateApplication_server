/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.realestateapp.domain;

import java.io.Serializable;

/**
 * house worker class - Server side 
 * @author Manasseh Barnes - 218009615
 */

public class house implements Serializable{
    private int houseID, houseNum, numofRooms, rentalPrice;
    private String streetName, area;
    private boolean isAvailable;

    public house(int houseID, int houseNum, String streetName, String area, int numofRooms, int rentalPrice, boolean isAvailable) {
        this.houseID = houseID;
        this.houseNum = houseNum;
        this.streetName = streetName;
        this.area = area;
        this.numofRooms = numofRooms;
        this.rentalPrice = rentalPrice;
        this.isAvailable = isAvailable;
    }

    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }
    
    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public int getNumofRooms() {
        return numofRooms;
    }

    public void setNumofRooms(int numofRooms) {
        this.numofRooms = numofRooms;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "house{" + "houseID=" + houseID + ", houseNum=" + houseNum + ", numofRooms=" + numofRooms + ", rentalPrice=" + rentalPrice + ", streetName=" + streetName + ", area=" + area + ", isAvailable=" + isAvailable + '}';
    }
}
