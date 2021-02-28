/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schema;

import java.util.Date;

/**
 *
 * @author jonat
 */
public class Appointments{
    private int Appointment_ID; //PK
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private Date Start;
    private Date End;
    private Date Create_Date;
    private String Created_By;
    private Date Last_Update;
    private String Last_Updated_By;
    private int Customer_ID;//FK
    private int User_ID; //FK
    private int Contact_ID;//FK
    
}
