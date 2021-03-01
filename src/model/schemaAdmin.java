/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jonat
 */
public class schemaAdmin {

    public static ObservableList<Users> getObservableListOfUsers() {
        return ObservableListOfUsers;
    }

    /*
    Pre-Thoughts,
    schemaAdmin will contain all info related to all classes and
    organize it to be pushed onto the DB. schemaAdmin must
    also be able to pull from the DB for use. 
    Specifics include:
    GetList of users, and their unique name and ID
    for the time being there will be a list to contain users
     */
    public static ObservableList<Customers> getObservableListOfCust() {
        return ObservableListOfCust;
    }

    
    private static ObservableList<Users> ObservableListOfUsers = FXCollections.observableArrayList();
    private static ObservableList<Customers> ObservableListOfCust = FXCollections.observableArrayList();
    
    public static void addCust(Customers newCust){
        getObservableListOfCust().add(newCust);
    }
    
    public static void addUser(Users newUser){
        getObservableListOfUsers().add(newUser);
    }
    
}
