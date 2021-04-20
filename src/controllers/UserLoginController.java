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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import model.Users;
import model.schemaAdmin;

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
    @FXML
    private Text zoneID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String langInUse = Locale.getDefault().toString().split("_")[0];
        String region = Locale.getDefault().toString().split("_")[1];
        zoneID.setText(region);
    }    

    @FXML
    private void submitLogin(ActionEvent event) throws IOException, SQLException {
        String serverName = "//wgudb.ucertify.com:3306/WJ07jSy";
        String dbUser = "U07jSy";
        String dbPass = "53689047995";
        String dbName = "WJ07jSy";
        String port = "3306";
        String url = "jdbc" + ":mysql:" + serverName;
        
        
        Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
        System.out.println(conn);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users");
        
        while (rs.next()){
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            
            String user = userText.getText();
            String pass = passText.getText();

            //Search database for valid info
            if (userName.equals(user) && password.equals(pass)){
                int id = rs.getInt("User_ID");
                Date createdDate = rs.getDate("Create_Date");
                String createBy = rs.getString("Created_By");
                Date lastUpdate = rs.getDate("Last_Update");
                String lastUpdateBy = rs.getString("Last_Updated_By");
                
                
                Users u = new Users(id,userName,password,createdDate,createBy,lastUpdate,lastUpdateBy);
                schemaAdmin.setUser(u);
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }else{
                System.out.println("Wrong info try again");
            }
            
        }
        
        
    }

    @FXML
    private void cancelLogin(ActionEvent event) {
        System.exit(0);
    }
    
}
