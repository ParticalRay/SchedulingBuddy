/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 * Appointments is used to organize the customer object with the contacts object
 *  and create a list based on given criteria. 
 * @author jonat
 */
public class Appointments{
    private int Appointment_ID; //PK
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private String Start;
    private String End;
    private String Create_Date;
    private String Created_By;
    private String Last_Update;
    private String Last_Updated_By;
    private int Customer_ID;//FK
    private int User_ID; //FK
    private int Contact_ID;//FK

    /**
     * Temp constructor
     * @param Title
     * @param Location
     */
    public Appointments(String Title, String Location) {
        this.Title = Title;
        this.Location = Location;
    }

    /**
     * Full constructor
     * @param Title
     * @param Description
     * @param Location
     * @param Type
     * @param Start
     * @param End
     * @param Created_By
     * @param Customer_ID
     * @param User_ID
     * @param Contact_ID
     */
    public Appointments(String Title, String Description, String Location, String Type, String Start, String End, String Created_By, int Customer_ID, int User_ID, int Contact_ID) {
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Created_By = Created_By;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;
    }

    /**
     *
     * @param Appointment_ID
     * @param Title
     * @param Description
     * @param Location
     * @param Type
     * @param Start
     * @param End
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     * @param Customer_ID
     * @param User_ID
     * @param Contact_ID
     */
    public Appointments(int Appointment_ID, String Title, String Description, String Location, String Type, String Start, String End, String Create_Date, String Created_By, String Last_Update, String Last_Updated_By, int Customer_ID, int User_ID, int Contact_ID) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;
    }

    /**
     *
     * @param Appointment_ID
     */
    public void setAppointment_ID(int Appointment_ID) {
        this.Appointment_ID = Appointment_ID;
    }

    /**
     *
     * @param Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @param Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     *
     * @param Location
     */
    public void setLocation(String Location) {
        this.Location = Location;
    }

    /**
     *
     * @param Type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     *
     * @param Start
     */
    public void setStart(String Start) {
        this.Start = Start;
    }

    /**
     *
     * @param End
     */
    public void setEnd(String End) {
        this.End = End;
    }

    /**
     *
     * @param Create_Date
     */
    public void setCreate_Date(String Create_Date) {
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
    public void setLast_Update(String Last_Update) {
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
     * @param Customer_ID
     */
    public void setCustomer_ID(int Customer_ID) {
        this.Customer_ID = Customer_ID;
    }

    /**
     *
     * @param User_ID
     */
    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    /**
     *
     * @param Contact_ID
     */
    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }
    
    /**
     *
     * @return
     */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return Location;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return Type;
    }

    /**
     *
     * @return
     */
    public String getStart() {
        return Start;
    }

    /**
     *
     * @return
     */
    public String getEnd() {
        return End;
    }

    /**
     *
     * @return
     */
    public String getCreate_Date() {
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
    public String getLast_Update() {
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
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     *
     * @return
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     *
     * @return
     */
    public int getContact_ID() {
        return Contact_ID;
    }
    
    
    
    
    
    
    
}
