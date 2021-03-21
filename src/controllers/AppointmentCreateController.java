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
    private TextField startDate;
    @FXML
    private TextField endDate;
    @FXML
    private TextField todaysDate;
    @FXML
    private TextField usersName;
    @FXML
    private TextField lastUpdateDate;
    @FXML
    private TextField lastUpdateBy;
    @FXML
    private TextField custID;
    @FXML
    private TextField userID;
    @FXML
    private TextField contactID;
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
    private void confirmAppointment(ActionEvent event) {
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
