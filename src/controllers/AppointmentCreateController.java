/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.schemaAdmin;
import static java.lang.Integer.parseInt;

/**
 * FXML Controller class
 *
 * @author jonat
 */
public class AppointmentCreateController implements Initializable {
    
    Stage stage;
    Parent scene;

    @FXML
    private TextField apptID;
    @FXML
    private TextField titleBox;
    @FXML
    private TextField DesBox;
    @FXML
    private TextField localBox;
    @FXML
    private TextField typeBox;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField custID;
    @FXML
    private TextField userID;
    @FXML
    private Button confirmButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button cancelButton;
    
    private int currentCustID;
    @FXML
    private ComboBox<String> startCombo;
    @FXML
    private ComboBox<String> endCombo;
    @FXML
    private ComboBox<String> contactsCombo;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        startCombo.getItems().addAll("8 Am","9 Am","10 Am","11 Am","12 Pm","1 Pm",
                    "2 Pm","3 Pm", "4 Pm");
        endCombo.getItems().addAll("9 Am","10 Am","11 Am","12 Pm","1 Pm",
                    "2 Pm","3 Pm", "4 Pm", "5 Pm");
        //Must add Contacts later 
    }   
    
    public void getCustID(int c){
        currentCustID = c;
    }

    @FXML
    private void confirmAppointment(ActionEvent event) {
        //'2021-04-18 01:34:42' year, month, day, then time
        System.out.println(startDate);
        String temp = (String)startDate.getValue().toString();
       

        int startTime = parseInt((String)startCombo.getValue().split(" ")[0]);
        int endTime = parseInt((String)endCombo.getValue().split(" ")[0]);
        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);
        String title = titleBox.getText();
        String desc = DesBox.getText();
        String loc = localBox.getText();
        String type = typeBox.getText();
        String startDateTime = null; //Must combine date and time in right format
        String endDateTime = null; //Combine date and time can split to get info
        String createdBy = schemaAdmin.getUser().getUser_Name();
        //currentCustID
        int userID = schemaAdmin.getUser().getUser_ID();
        
    }

    @FXML
    private void resetForm(ActionEvent event) {
    }

    @FXML
    private void cancelAppointment(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
