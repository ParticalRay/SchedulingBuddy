/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Locale;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

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
    @FXML
    private Text titleText;
    @FXML
    private Text idTextTrans;
    @FXML
    private Text nameTextTrans;
    @FXML
    private Text addressTextTrans;
    @FXML
    private Text postalTextTrans;
    @FXML
    private Text phoneTextTrans;

    /**
     * Initializes the controller class.Initial loading of information into the fxml.
     * @param url 
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String langInUse = Locale.getDefault().toString().split("_")[0];
        String region = Locale.getDefault().toString().split("_")[1];
        if (langInUse.equals("fr")){
            titleText.setText("modificateur de rendez-vous");
            idTextTrans.setText("identifiant de rendez-vous");
            nameTextTrans.setText("Titre");
            addressTextTrans.setText("adresse");
            postalTextTrans.setText("code postal");
            phoneTextTrans.setText("téléphoner");
            confirmButton.setText("confirmer");
            resetButton.setText("réinitialiser");
            cancelButton.setText("Annuler");
                    
        }
    }    

    /**
     * Confirm create will modify a  customer object based on the information 
     *  provided in the text fields.
     * @param event Confirm button pressed
     * @throws IOException Loading the main fxml after creation tasks
     */
    @FXML
    private void confirmCreate(ActionEvent event) throws IOException {
        try{
            Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = warning.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int id = Integer.parseInt(IDText.getText());
                String name = nameText.getText();
                String address = addressText.getText();
                String zip = zipText.getText();
                String phone = phoneText.getText();
                Customers newCust = new Customers(id,name,address,zip,phone);
                schemaAdmin.addCust(newCust);
                updateCustInDb(newCust);
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }catch(Exception e){
            
        }
    }

    /**
     * Reset page will return all fields to default values
     * @param event reset button clicked
     */
    @FXML
    private void resetPage(ActionEvent event) {
        try{
            Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = warning.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                this.cust = cust;
                IDText.setText(String.valueOf(cust.getID()));
                nameText.setText(String.valueOf(cust.getName()));
                addressText.setText(String.valueOf(cust.getAddress()));
                zipText.setText(String.valueOf(cust.getPostal()));
                phoneText.setText(String.valueOf(cust.getPhone()));
            }
        }catch(Exception e){
            
        }
    }

    /**
     * Cancel creation will cancel the tasks and go back to the main fxml 
     * @param event cancel button pressed
     * @throws IOException loading the main fxml
     */
    @FXML
    private void cancelCreation(ActionEvent event) throws IOException {
        schemaAdmin.addCust(cust);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /**
     * Update cust in db will take the customer object and update with the new
     *  values given in the text fields
     * @param c customer object
     */
    public void updateCustInDb(Customers c){
        PreparedStatement stmt;
        String update = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ? WHERE Customer_ID = ?";
        
        try {
            Connection conn = getStarted();
            String custID = c.getID() + "";
            stmt = conn.prepareStatement(update);
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getAddress());
            stmt.setString(3, c.getPostal());
            stmt.setString(4, c.getPhone());
            stmt.setString(5, custID);
            stmt.execute();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * set customer will take the customer object from the main fxml and make it
     *  preload the information into the text fields. 
     * @param cust customer object
     */
    public void setCustomer(Customers cust){
        this.cust = cust;
        IDText.setText(String.valueOf(cust.getID()));
        nameText.setText(String.valueOf(cust.getName()));
        addressText.setText(String.valueOf(cust.getAddress()));
        zipText.setText(String.valueOf(cust.getPostal()));
        phoneText.setText(String.valueOf(cust.getPhone()));
    }
    
    /**
     * get started will create the connection to the database and return 
     *  the connection object
     * @return connection object
     * @throws SQLException sql issue being thrown
     */
    public Connection getStarted() throws SQLException{
        String serverName = "//wgudb.ucertify.com:3306/WJ07jSy";
        String user = "U07jSy";
        String pass = "53689047995";
        String dbName = "WJ07jSy";
        String port = "3306";
        String url = "jdbc" + ":mysql:" + serverName;
        java.sql.Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
}
    
}
