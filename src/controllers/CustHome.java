/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import schema.Customers;

/**
 * FXML Controller class
 *
 * @author jonat
 */
public class CustHome implements Initializable {

    @FXML
    private TableView<Customers> CustTable;
    @FXML
    private TableColumn<Customers, Integer> IDCol;
    @FXML
    private TableColumn<Customers, String> NameCol;
    @FXML
    private TableColumn<Customers, String> AddressCol;
    @FXML
    private TableColumn<Customers, Integer> ZipCol;
    @FXML
    private TableColumn<Customers, Integer> PhoneCol;
    @FXML
    private Button createCustButton;
    @FXML
    private Button ModifyCustButton;
    @FXML
    private Button DeleteCustButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createCust(ActionEvent event) {
        //Send to create customer page to create object
    }

    @FXML
    private void modifyCust(ActionEvent event) {
        //Send to Modify Cust object to change info
    }

    @FXML
    private void deleteCust(ActionEvent event) {
    }
    
}
