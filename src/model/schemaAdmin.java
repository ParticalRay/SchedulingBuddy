/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonat
 */
public class schemaAdmin {
    /*
    Pre-Thoughts,
    schemaAdmin will contain all info related to all classes and
        organize it to be pushed onto the DB. schemaAdmin must
        also be able to pull from the DB for use. 
    Specifics include:
        GetList of users, and their unique name and ID
        
    for the time being there will be a list to contain users
    */
    
    private static List<Users> listOfUsers = new ArrayList<Users>();
    private static List<Customers> listOfCustomers = new ArrayList<Customers>();
    private Users currentUser;//The current User
    
}
