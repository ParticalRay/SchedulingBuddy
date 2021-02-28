/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schema;

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
    
    
    
}
