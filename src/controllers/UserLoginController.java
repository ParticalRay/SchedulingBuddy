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
public class UserLoginController implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private TextField userText;
    @FXML
    private TextField passText;
    @FXML
    private Button submitButton;
    @FXML
    private Button CancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submitLogin(ActionEvent event) throws IOException {
        String user = userText.getText();
        String pass = passText.getText();
        
        //Search database for valid info
        if ("admin".equals(user) && "admin".equals(pass)){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    private void cancelLogin(ActionEvent event) {
        System.exit(0);
    }
    
}
