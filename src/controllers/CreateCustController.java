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
import java.util.Hashtable;
import java.util.Locale;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import model.Contacts;
import model.Countries;


/**
 * FXML Controller class
 *  Controls the creation of new customers.
 * 
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
    private TextField stateField;
    @FXML
    private Text nameTextTrans;
    @FXML
    private Text addressTextTrans;
    @FXML
    private Text stateTextTrans;
    @FXML
    private Text postalTextTrans;
    @FXML
    private Text phoneTextTrans;
    @FXML
    private Text titleTextTrans;
    @FXML
    private ComboBox<String> countryCombo;
    @FXML
    private ComboBox<String> divisionCombo;
    private static ObservableList<Countries> ObservableListOfCountries = FXCollections.observableArrayList();
    

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
        getCountryFromDb();
        if (langInUse.equals("fr")){
            titleTextTrans.setText("modificateur de rendez-vous");
            
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
     * Confirm create will create a new customer object based on the information 
     *  provided in the text fields.
     * @param event Confirm button pressed
     * @throws IOException Loading the main fxml after creation tasks
     */
    @FXML
    private void confirmCreate(ActionEvent event) throws IOException {
        //Confirm creation of new customer object
       
        Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = warning.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
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
    }
    
    /**
     * Reset page will return all fields to default values
     * @param event reset button clicked
     */
    @FXML
    private void resetPage(ActionEvent event) {
        //Reset to default
        try{
            Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = warning.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                nameText.clear();
                addressText.clear();
                zipText.clear();
                phoneText.clear();
                stateField.clear();
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
        try{
            Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = warning.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }catch(Exception e){
            
        }
    }
    
    /**
     * create and update will take the newly created customer and add it into 
     *  the database.
     * @param c new customer object
     */
    public void createAndUpdate(Customers c){
       
        PreparedStatement stmt;
        String insert = "INSERT INTO customers (Customer_Name, Phone,Address,Postal_Code, Division_ID, Created_By) values(?,?,?,?,?,?)";
        
        try{
            
            stmt = getStarted().prepareStatement(insert);
            
            stmt.setString(1,c.getName());
            stmt.setString(2,c.getPhone());
            stmt.setString(3,c.getAddress());
            stmt.setString(4,c.getPostal());
            stmt.setInt(5, getDivisionFromDb());
            stmt.setString(6,schemaAdmin.getUser().getUser_Name());
            stmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        }
    
    /**
     * get division from db will obtain the division id from the database by 
     *  using the state that is provided in the text field
     * @param c Connection to the database
     * @return the integer of the id
     */
    private void loadDivisionFromDb(int countryID){
        String search = "select * from first_level_divisions where COUNTRY_ID = ?";
        PreparedStatement stmt = null;
        
        try{
            stmt = getStarted().prepareStatement(search);
            stmt.setInt(1, countryID);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                String name = results.getString("Division");
                divisionCombo.getItems().add(name);
            }
            
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
     private int getDivisionFromDb(){
        String search = "select Division_ID from first_level_divisions where division = ?";

        PreparedStatement stmt = null;
        String city = divisionCombo.getSelectionModel().getSelectedItem();
        
        try{
            stmt = getStarted().prepareStatement(search);
            stmt.setString(1, city);

            ResultSet results = stmt.executeQuery();
            while(results.next()){
                int val = Integer.parseInt(results.getObject(1).toString());
                return val;
            }
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;//Something went wrong 
    }
    
    private void getCountryFromDb(){
        String search = "select * from countries";
        PreparedStatement stmt = null;
        try{
            stmt = getStarted().prepareStatement(search);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                int id = results.getInt("Country_ID");
                String name = results.getString("Country");
                Countries c = new Countries(id,name);
                countryCombo.getItems().add(name);
                ObservableListOfCountries.add(c);
            }
        }catch(SQLException e){
            
        }
    }//Need fxml method to load the data after a countrie is selected
    
    
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

    @FXML
    private void changeDivisionInfo(ActionEvent event) {
        divisionCombo.getItems().clear();
        String name = countryCombo.getSelectionModel().getSelectedItem();
        int countryID = 0;
        for(Countries c: ObservableListOfCountries){
            if(c.getCountry().equals(name)){
                countryID = c.getCountry_ID();
            }
        }
        loadDivisionFromDb(countryID);
        
    }
    
    }
    

