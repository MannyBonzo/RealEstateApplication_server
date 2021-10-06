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
public class house {
    private int houseID, houseNum, postalCode, numofRooms, rentalPrice;
    private String streetName, city, area;
    private boolean isAvailable;

    public house(int houseID, int houseNum, int postalCode, int numofRooms, int rentalPrice, String streetName, String city, String area, boolean isAvailable) {
        this.houseID = houseID;
        this.houseNum = houseNum;
        this.postalCode = postalCode;
        this.numofRooms = numofRooms;
        this.rentalPrice = rentalPrice;
        this.streetName = streetName;
        this.city = city;
        this.area = area;
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        return "house{" + "houseID=" + houseID + ", houseNum=" + houseNum + ", postalCode=" + postalCode + ", numofRooms=" + numofRooms + ", rentalPrice=" + rentalPrice + ", streetName=" + streetName + ", city=" + city + ", area=" + area + ", isAvailable=" + isAvailable + '}';
    }
    
}
