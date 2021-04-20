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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;
import model.schemaAdmin;
import alpha.SchedulingBuddy;
import javafx.scene.control.Alert;
import model.Appointments;

/**
 * FXML Controller class
 *
 * @author jonat
 */
public class CustHome implements Initializable {

    Stage stage;
    Parent scene;
    
    @FXML
    private TableColumn<Customers, Integer> IDCol;
    @FXML
    private TableColumn<Customers, String> NameCol;
    @FXML
    private TableColumn<Customers, String> AddressCol;
    @FXML
    private TableColumn<Customers, String> ZipCol;
    @FXML
    private TableColumn<Customers, String> PhoneCol;
    
    @FXML
    private TableView<Customers> CustTable;
    
    @FXML
    private Button createCustButton;
    @FXML
    private Button ModifyCustButton;
    @FXML
    private Button DeleteCustButton;
    @FXML
    private Button createApptButton;
    @FXML
    private TableView<Appointments> apptTable;
    @FXML
    private Button modifyApptButton;
    @FXML
    private Button exitButton;
    @FXML
    private TableColumn<Appointments, Integer> apptIDCol;
    @FXML
    private TableColumn<Appointments, String> titleCol;
    @FXML
    private TableColumn<Appointments, String> descCol;
    @FXML
    private TableColumn<Appointments, String> locCol;
    @FXML
    private TableColumn<Appointments, Integer> contactCol;
    @FXML
    private TableColumn<Appointments, Integer> typeCol;
    @FXML
    private TableColumn<Appointments, String> startDateCol;
    @FXML
    private TableColumn<Appointments, String> endDateCol;
    @FXML
    private TableColumn<Appointments, Integer> custIDCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL ul, ResourceBundle rb) {
        schemaAdmin.getObservableListOfCust().clear();
        schemaAdmin.getObservableListOfAppt().clear();
        try{
            SchedulingBuddy.connectAndUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        CustTable.setItems(schemaAdmin.getObservableListOfCust());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        ZipCol.setCellValueFactory(new PropertyValueFactory<>("postal"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        apptTable.setItems(schemaAdmin.getObservableListOfAppt());
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
    }    

    @FXML
    private void createCust(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CreateCust.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    
    
    
    
    @FXML
    private void modifyCust(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/ModifyCust.fxml"));
        loader.load();

        ModifyCustController MCController = loader.getController();
        Customers cust = CustTable.getSelectionModel().getSelectedItem();
        MCController.setCustomer(cust);
        schemaAdmin.getObservableListOfCust().remove(cust);
        


        stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    
    public Connection getStarted() throws SQLException{
        String serverName = "//wgudb.ucertify.com:3306/WJ07jSy";
        String user = "U07jSy";
        String pass = "53689047995";
        String dbName = "WJ07jSy";
        String port = "3306";
        String url = "jdbc" + ":mysql:" + serverName;
        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
    
    @FXML
    private void deleteCust(ActionEvent event) {
        Customers cust = CustTable.getSelectionModel().getSelectedItem();
        deleteCustInDb(cust);
        
    }

    @FXML
    private void createAppt(ActionEvent event) throws IOException {
        /*
        Here we need to create an appt, then send to the db to receive the correct
            appt ID, then bring it back and auto fill the info with what we know.
            Step 1: Get customer ID the appt is attached to.
            Step 2: Get User Id attached to the creation
            Step 3: Get the contact the appt is going for. (Default 1)
            
        */
        
        Customers c = CustTable.getSelectionModel().getSelectedItem();
        System.out.println("Cust Home got: " + c);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AppointmentCreate.fxml"));
            loader.load();
            AppointmentCreateController apptCController = loader.getController();
            apptCController.getCustID(c);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
            
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a customer before creating an appointment");
                alert.showAndWait();
        }
        
        
    }
    
    
    private void deleteCustInDb(Customers c){
        Connection conn = null;
        PreparedStatement stmt = null;
        String deleteCust = "delete from customers where Customer_ID = ?";
        String deleteAppt = "delete from appointments where Customer_ID = ?";
        try{
            conn = getStarted();
            System.out.println(conn);
            stmt = conn.prepareStatement(deleteAppt);
            stmt.setInt(1, c.getID());
            stmt.execute();
            
            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            conn = getStarted();
            System.out.println(conn);
            stmt = conn.prepareStatement(deleteCust);
            stmt.setInt(1, c.getID());
            stmt.execute();
            schemaAdmin.getObservableListOfCust().remove(c);
            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void modifyAppt(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
    }
}
