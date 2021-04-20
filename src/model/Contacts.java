/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jonat
 */
public class Contacts {
    private int Contact_ID; //PK
    private String Contact_Name;
    private String Email;
    
    public Contacts(int id, String name, String email){
        this.Contact_ID = id;
        this.Contact_Name = name;
        this.Email = email;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }

    public void setContact_Name(String Contact_Name) {
        this.Contact_Name = Contact_Name;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

 
    
    
    
}
