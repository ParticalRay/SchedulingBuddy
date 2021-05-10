
package model;

import java.util.Hashtable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jonat
 */
public class schemaAdmin {

    /**
     *
     * @return
     */
    public static ObservableList<Users> getObservableListOfUsers() {
        return ObservableListOfUsers;
    }
    
    /**
     *
     * @return
     */
    public static ObservableList<Appointments> getObservableListOfAppt(){
        return ObservableListOfAppts;
    }
    
    /**
     *
     * @return
     */
    public static ObservableList<Contacts> getObservableListOfContacts(){
        return ObservableListOfContacts;
    }
    
    private static Users currentUser;
    
    /**
     *
     * @return
     */
    public static Users getUser(){
        return currentUser;
    }
    
    /**
     *
     * @param u
     */
    public static void setUser(Users u){
        currentUser = u;
    }
    
    /**
     *
     * @return
     */
    public static ObservableList<Customers> getObservableListOfCust() {
        return ObservableListOfCust;
    }

    
    private static ObservableList<Users> ObservableListOfUsers = FXCollections.observableArrayList();
    private static ObservableList<Customers> ObservableListOfCust = FXCollections.observableArrayList();
    private static ObservableList<Appointments> ObservableListOfAppts = FXCollections.observableArrayList();    
    private static ObservableList<Contacts> ObservableListOfContacts = FXCollections.observableArrayList();
    
    private static Hashtable<String,Integer> typeCollection = new Hashtable<String,Integer>();

    public static Hashtable<String, Integer> getTypeCollection() {
        return typeCollection;
    }
    
    

    /**
     *
     * @param c
     */
    public static void addContacts(Contacts c){
        getObservableListOfContacts().add(c);
    }
    
    /**
     *
     * @param newCust
     */
    public static void addCust(Customers newCust){
        getObservableListOfCust().add(newCust);
    }
    
    /**
     *
     * @param newUser
     */
    public static void addUser(Users newUser){
        getObservableListOfUsers().add(newUser);
    }

    /**
     *
     * @param appt
     */
    public static void addAppts(Appointments appt){
        getObservableListOfAppt().add(appt);
    }
    
    
}
