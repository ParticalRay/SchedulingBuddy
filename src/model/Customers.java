/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.sql.*;
/**
 *
 * @author jonat
 */
public class Customers {
    private int id;
    private String name;
    private String address;
    private String postal;
    private String phone;
    private Date Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Division_ID;//FK

    /**
     *
     * @param name
     * @param address
     * @param postal
     * @param phone
     */
    public Customers(String name, String address, String postal, String phone) {
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
    }
    
    /**
     * 
     * @param name 
     */
    public Customers(String name){
        this.name = name;
    }
    
    /**
     *
     * @param name
     * @param address
     * @param postal
     * @param phone
     */
    public Customers(String name, String address, String postal, String phone, int division) {
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
        this.Division_ID = division;
    }

    /**
     *
     * @param id
     * @param name
     * @param address
     * @param postal
     * @param phone
     */
    public Customers(int id, String name, String address, String postal, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
    }

    /**
     *
     * @param id
     * @param name
     * @param address
     * @param postal
     * @param phone
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     * @param Division_ID
     */
    public Customers(int id, String name, String address, String postal, String phone, Date Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Division_ID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Division_ID = Division_ID;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return getID()+ ", " + getName() + ", " + getAddress() + ", "
                + getPostal() +", " + getPhone() + ", " + getDivision_ID();  
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return
     */
    public String getPostal() {
        return postal;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @return
     */
    public Date getCreate_Date() {
        return Create_Date;
    }

    /**
     *
     * @return
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     *
     * @return
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     *
     * @return
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     *
     * @return
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     *
     * @param Customer_Name
     */
    public void setCustomer_Name(String Customer_Name) {
        this.name = Customer_Name;
    }

    /**
     *
     * @param Address
     */
    public void setAddress(String Address) {
        this.address = Address;
    }

    /**
     *
     * @param Postal_Code
     */
    public void setPostal_Code(String Postal_Code) {
        this.postal = Postal_Code;
    }

    /**
     *
     * @param Phone
     */
    public void setPhone(String Phone) {
        this.phone = Phone;
    }

    /**
     *
     * @param Create_Date
     */
    public void setCreate_Date(Date Create_Date) {
        this.Create_Date = Create_Date;
    }

    /**
     *
     * @param Created_By
     */
    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }

    /**
     *
     * @param Last_Update
     */
    public void setLast_Update(Timestamp Last_Update) {
        this.Last_Update = Last_Update;
    }

    /**
     *
     * @param Last_Updated_By
     */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }

    /**
     *
     * @param Division_ID
     */
    public void setDivision_ID(int Division_ID) {
        this.Division_ID = Division_ID;
    }
    
    
    
}
