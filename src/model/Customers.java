/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author jonat
 */
public class Customers {
    private final int id;
    private String name;
    private String address;
    private String postal;
    private String phone;
    private Date Create_Date;
    private String Created_By;
    private Date Last_Update;
    private String Last_Updated_By;
    private int Division_ID;//FK

    public Customers(int id, String name, String address, String postal, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
    }//Everything else is done in the back end
    
    @Override
    public String toString(){
        return getID()+ ", " + getName() + ", " + getAddress() + ", "
                + getPostal() +", " + getPhone();  
    }

    
    
    public final int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostal() {
        return postal;
    }

    public String getPhone() {
        return phone;
    }

    public Date getCreate_Date() {
        return Create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public Date getLast_Update() {
        return Last_Update;
    }

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public int getDivision_ID() {
        return Division_ID;
    }


    public void setCustomer_Name(String Customer_Name) {
        this.name = Customer_Name;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public void setPostal_Code(String Postal_Code) {
        this.postal = Postal_Code;
    }

    public void setPhone(String Phone) {
        this.phone = Phone;
    }

    public void setCreate_Date(Date Create_Date) {
        this.Create_Date = Create_Date;
    }

    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }

    public void setLast_Update(Date Last_Update) {
        this.Last_Update = Last_Update;
    }

    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }

    public void setDivision_ID(int Division_ID) {
        this.Division_ID = Division_ID;
    }
    
    
    
}
