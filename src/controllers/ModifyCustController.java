/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customers;
import model.schemaAdmin;
import controllers.CreateCustController;

/**
 * FXML Controller class
 *
 * @author jonat
 */
public class ModifyCustController implements Initializable {
    Customers cust;
    Stage stage;
    Parent scene;
    @FXML
    private TextField IDText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField zipText;
    @FXML
    private TextField phoneText;
    @FXML
    private Button confirmButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmCreate(ActionEvent event) throws IOException {
        int id = Integer.parseInt(IDText.getText());
        String name = nameText.getText();
        String address = addressText.getText();
        String zip = zipText.getText();
        String phone = phoneText.getText();
        Customers newCust = new Customers(id,name,address,zip,phone);
        schemaAdmin.addCust(newCust);
        //CreateCustController.createAndUpdate(newCust);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void resetPage(ActionEvent event) {
        this.cust = cust;
        IDText.setText(String.valueOf(cust.getID()));
        nameText.setText(String.valueOf(cust.getName()));
        addressText.setText(String.valueOf(cust.getAddress()));
        zipText.setText(String.valueOf(cust.getPostal()));
        phoneText.setText(String.valueOf(cust.getPhone()));
    }

    @FXML
    private void cancelCreation(ActionEvent event) throws IOException {
        schemaAdmin.addCust(cust);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    public void setCustomer(Customers cust){
        this.cust = cust;
        IDText.setText(String.valueOf(cust.getID()));
        nameText.setText(String.valueOf(cust.getName()));
        addressText.setText(String.valueOf(cust.getAddress()));
        zipText.setText(String.valueOf(cust.getPostal()));
        phoneText.setText(String.valueOf(cust.getPhone()));
        
        
    }
    
}
