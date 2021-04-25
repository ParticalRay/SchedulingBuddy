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
    
    /**
     *
     * @param id
     * @param name
     * @param email
     */
    public Contacts(int id, String name, String email){
        this.Contact_ID = id;
        this.Contact_Name = name;
        this.Email = email;
    }

    /**
     *
     * @return
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     *
     * @return
     */
    public String getContact_Name() {
        return Contact_Name;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return Email;
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
     * @param Contact_Name
     */
    public void setContact_Name(String Contact_Name) {
        this.Contact_Name = Contact_Name;
    }

    /**
     *
     * @param Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

 
    
    
    
}
