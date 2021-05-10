/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.FileWriter;
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
import javafx.scene.control.Alert;
import model.Users;
import model.schemaAdmin;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * FXML Controller class
 *
 * @author Jonathon Makepeace
 *
 * User Login controller allows the user to login to the application
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
    @FXML
    private Text userTextTrans;
    @FXML
    private Text passTextTrans;

    private int attempts = 0;
    
    private String langInUse;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        langInUse = Locale.getDefault().toString().split("_")[0];
        String region = Locale.getDefault().toString().split("_")[1];
        
        String timezone = ZonedDateTime.now(ZoneId.systemDefault()).toString();
        ZoneId z = ZoneId.systemDefault();
        System.out.println(z);
        zoneID.setText(z.toString());
        if (langInUse.equals("fr")) {
            userTextTrans.setText("Nom d'utilisateur");
            passTextTrans.setText("le mot de passe");
            submitButton.setText("nous faire parvenir");
            CancelButton.setText("Annuler");
        }
    }

    /**
     * Submit login is the action of pressing submit on the fxml. Once clicked
     *  it will take in the username and password in the text fields and match 
     *  whats in the database. If correct it will pass to the rest of the application
     * @param event submit button clicked
     * @throws IOException file system error
     * @throws SQLException Sql error relating to the database issues
     */
    @FXML
    private void submitLogin(ActionEvent event) throws IOException, SQLException {
        attempts += 1;
        FileWriter writer = new FileWriter("login_activity.txt", true);
        if (verifyLogin()) {
            writer.write("Login attempts: " + attempts + " Login Date and Time: "
                        + LocalDateTime.now() + " Passed \n");
            writer.close();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/views/CustHome.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            try {
                
                writer.write("Login attempts: " + attempts + " Login Date and Time: "
                        + LocalDateTime.now() + " Failed \n");
                writer.close();

            } catch (IOException e) {

            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if(langInUse.equals("fr")){
                alert.setContentText("Informations de connexion incorrectes. Veuillez réessayer.");
                alert.showAndWait();
            }else{
                alert.setContentText("Incorrect Login information. Please try again.");
                alert.showAndWait();
            }
        }
    }

    /**
     * Verify login will process the database and return true or false depending
     *  if the username and password matches.
     * @return true or false
     */
    public boolean verifyLogin() {
        try {
            String serverName = "//wgudb.ucertify.com:3306/WJ07jSy";
            String dbUser = "U07jSy";
            String dbPass = "53689047995";
            String dbName = "WJ07jSy";
            String port = "3306";
            String url = "jdbc" + ":mysql:" + serverName;

            Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");

            while (rs.next()) {
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                String user = userText.getText();
                String pass = passText.getText();
                if (userName.equals(user) && password.equals(pass)) {
                    System.out.println(userName);
                    System.out.println(password);
                    int id = rs.getInt("User_ID");
                    Date createdDate = rs.getDate("Create_Date");
                    String createBy = rs.getString("Created_By");
                    Date lastUpdate = rs.getDate("Last_Update");
                    String lastUpdateBy = rs.getString("Last_Updated_By");
                    Users temp = new Users(id, user, pass, createdDate, createBy, lastUpdate, lastUpdateBy);

                    schemaAdmin.setUser(temp);

                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Issue found on userLogin controller");
        }
        return false;
    }

    /**
     * Cancel login will close the application
     * @param event cancel button being pressed
     */
    @FXML
    private void cancelLogin(ActionEvent event) {
        System.exit(0);
    }

}
