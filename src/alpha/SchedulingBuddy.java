/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alpha;

import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customers;
import model.schemaAdmin;
import java.sql.*;
//int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone
/**
 *
 * @author jonat
 */
public class SchedulingBuddy extends Application{
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        /*
        Customers test1 = new Customers(1, "Jon Makepeace", "USA Bitches", "98312", "3365969982");
        Customers test2 = new Customers(2, "Wyatt the fag", "USA Bitches", "98312", "some ohio number");
        Customers test3 = new Customers(3, "Mystic the Ginger", "Northern Forest of US(Canada)", "canadian zip", "some canadian number");
        Customers test4 = new Customers(4, "Awon the majestic", "North Africa", "African Zip", "1234567890");
        schemaAdmin.addCust(test1);
        schemaAdmin.addCust(test2);
        schemaAdmin.addCust(test3);
        schemaAdmin.addCust(test4);
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        */
        connectAndUpdate();
        System.out.println(Locale.getDefault());//code to get locale info
           
        
        
        
        launch(args);
    }
    
    /*
    We need to verify the user that is accessing the database and save their info
    
    */
    public static void connectAndUpdate() throws SQLException{
        String serverName = "//wgudb.ucertify.com:3306/WJ07jSy";
        String user = "U07jSy";
        String pass = "53689047995";
        String dbName = "WJ07jSy";
        String port = "3306";
        String url = "jdbc" + ":mysql:" + serverName;
        
        
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stmt = conn.createStatement();
        
        
        
        
        ResultSet rs = stmt.executeQuery("select * from customers");
        
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zip = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Date createDate = rs.getDate("Create_Date");
            String createBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");
            Customers cx = new Customers(id, name, address, zip, phone, createDate, createBy, lastUpdate, lastUpdateBy, divisionID);
            
            schemaAdmin.addCust(cx);
   
            
        }

    }
    /* Here is insert into example. Note Must have a value in every spot
    String sql = "INSERT INTO contacts " + "VALUES ('2','Jon','email@email.com')";
    stmt.executeUpdate(sql);
    */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/UserLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Scheduling Buddy");
        stage.show();

    }
    
}
