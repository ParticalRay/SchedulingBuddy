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
public class FirstLevelDivision {
    private int Division_ID;//PK
    private String Division;
    private Date Create_Date;
    private String Created_By;
    private Date Last_Update;
    private String Last_Updated_By;
    private int Country_ID;//FK

    
    
    
    public int getDivision_ID() {
        return Division_ID;
    }

    public String getDivision() {
        return Division;
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

    public int getCountry_ID() {
        return Country_ID;
    }

    public void setDivision_ID(int Division_ID) {
        this.Division_ID = Division_ID;
    }

    public void setDivision(String Division) {
        this.Division = Division;
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

    public void setCountry_ID(int Country_ID) {
        this.Country_ID = Country_ID;
    }
    
    
    
}
