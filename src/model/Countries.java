/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 * Countries is used to automatically keep track of countries once it has been
 *  loaded from the database.
 * @author jonat
 */
public class Countries {
    private int Country_ID;
    private String Country;
    private Date Create_Date;
    private String Created_By;
    private Date Last_Update;
    private String Last_Updated_By;
    
    /**
     * Constructor
     * @param id
     * @param name 
     */
    public Countries(int id, String name){
        this.Country_ID = id;
        this.Country = name;
    }

    /**
     *
     * @return
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return Country;
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
    public Date getLast_Update() {
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
     * @param Country_ID
     */
    public void setCountry_ID(int Country_ID) {
        this.Country_ID = Country_ID;
    }

    /**
     *
     * @param Country
     */
    public void setCountry(String Country) {
        this.Country = Country;
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
    public void setLast_Update(Date Last_Update) {
        this.Last_Update = Last_Update;
    }

    /**
     *
     * @param Last_Updated_By
     */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }
    
    
    
}
