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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jonat
 */
public class ModifyCustController implements Initializable {

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
    private void confirmCreate(ActionEvent event) {
    }

    @FXML
    private void resetPage(ActionEvent event) {
    }

    @FXML
    private void cancelCreation(ActionEvent event) {
    }
    
}
