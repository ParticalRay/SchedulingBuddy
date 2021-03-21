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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;
import model.schemaAdmin;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustTable.setItems(schemaAdmin.getObservableListOfCust());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        ZipCol.setCellValueFactory(new PropertyValueFactory<>("postal"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
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
        MCController.setCustomer(CustTable.getSelectionModel().getSelectedItem());
        schemaAdmin.getObservableListOfCust().remove(CustTable.getSelectionModel().getSelectedItem());


        stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void deleteCust(ActionEvent event) {
        
    }

    @FXML
    private void createAppt(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/AppointmentCreate.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
