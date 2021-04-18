/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import java.sql.PreparedStatement;


/**
 * FXML Controller class
 *
 * @author jonat
 */
public class CreateCustController implements Initializable {
    Stage stage;
    Parent scene;
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
    @FXML
    private TextField stateField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    int savedID = 0;
    
    @FXML
    private void confirmCreate(ActionEvent event) throws IOException {
        //Confirm creation of new customer object
        String name = nameText.getText();
        String address = addressText.getText();
        String zip = zipText.getText();
        String phone = phoneText.getText();
        Customers newCust = new Customers(name,address,zip,phone);
        
        createAndUpdate(newCust);
        
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }
    
    
    

    @FXML
    private void resetPage(ActionEvent event) {
        //Reset to default
        nameText.clear();
        addressText.clear();
        zipText.clear();
        phoneText.clear();
        stateField.clear();
    }

    @FXML
    private void cancelCreation(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    
    //creating and updating to database
    public void createAndUpdate(Customers c){
        String serverName = "//wgudb.ucertify.com:3306/WJ07jSy";
        String user = "U07jSy";
        String pass = "53689047995";
        String dbName = "WJ07jSy";
        String port = "3306";
        String url = "jdbc" + ":mysql:" + serverName;
        Connection conn;
        PreparedStatement stmt;
        String insert = "INSERT INTO customers (Customer_Name, Phone,Address,Postal_Code, Division_ID, Created_By) values(?,?,?,?,42,?)";
        
        try{
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println(conn);
            stmt = conn.prepareStatement(insert);
            
            stmt.setString(1,c.getName());
            stmt.setString(2,c.getPhone());
            stmt.setString(3,c.getAddress());
            stmt.setString(4,c.getPostal());
            stmt.setString(5,schemaAdmin.getUser().getUser_Name());
            stmt.execute();
           
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        }
    
    
    private int getDivisionFromDb(Connection c){
        String search = "select Division_ID from first_level_divisions where division = ?";
        PreparedStatement stmt = null;
        String city = stateField.getText();
        try{
            stmt = c.prepareStatement(search);
            stmt.setString(1, city);
            ResultSet results = stmt.executeQuery();
            int val = Integer.parseInt(results.getObject(1).toString());
            return val;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;//Something went wrong 
    }
    
    }
    

